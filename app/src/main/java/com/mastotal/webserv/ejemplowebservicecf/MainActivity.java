package com.mastotal.webserv.ejemplowebservicecf;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.mastotal.webserv.ejemplowebservicecf.POJO.Usuario;
import com.mastotal.webserv.ejemplowebservicecf.Parsers.UsuarioJSONParser;
import com.mastotal.webserv.ejemplowebservicecf.Parsers.UsuarioXMLParser;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button boton;
    TextView textView;
    ProgressBar progressBar;
    List<MyYTask> taskList;

    // crear la lista de objetos Usuario
    List<Usuario> usuarioList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        boton = (Button) findViewById(R.id.boton);
        textView = (TextView) findViewById(R.id.textView);
        progressBar = (ProgressBar) findViewById(R.id.progressbar);
        progressBar.setVisibility(View.INVISIBLE);

        textView.setMovementMethod(new ScrollingMovementMethod());// esta linea hace que el scroll funcione

        taskList = new ArrayList<>();


        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /*for (int i = 0; i<= 100; i++){
                    cargarDatos("Numero: " + i);
                }*/

                if (isOnLine()){
                    Toast.makeText(getApplicationContext(), "Conectado a internet", Toast.LENGTH_SHORT).show();
                    //pedirDatos("http://maloschistes.com/maloschistes.com/jose/usuarios.xml");
                    // Pedir datos con XML
                    //pedirDatos("http://chat.mastotal.com/usuarios.xml");
                    //Pedir datos con JSON
                    //pedirDatos("http://maloschistes.com/maloschistes.com/jose/webservice.php");
                    //pedir datos sin seguridad
                    //pedirDatos("http://mastotal.com/webservice/webservice.php");
                    //pedir datos con seguridad
                    pedirDatos("http://maloschistes.com/maloschistes.com/jose/s/webservice.php");


                }else{
                    Toast.makeText(getApplicationContext(), "Sin conexión", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    public void cargarDatos(){

        //textView.append(datos + "\n");
        if (usuarioList != null){
            //forech
            for (Usuario usuario: usuarioList) {
                textView.append(usuario.getUsuarioId() + "\n");
                textView.append(usuario.getNombre() + "\n");
                textView.append(usuario.getTwitter() + "\n");

            }

        }
    }

    //verificar conectidad a internet
    public boolean isOnLine(){
        ConnectivityManager connectivityManager = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnectedOrConnecting()){
            return true;
        }else{
            return false;
        }
    }// Fin metodo verificar online

    // metodo pedir datos
    public void pedirDatos(String uri){

        MyYTask task = new MyYTask();
        task.execute(uri);
        //task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

    }


    private class MyYTask extends AsyncTask<String, String, String>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //cargarDatos("Inicio de carga");

            if(taskList.size() == 0) {
                progressBar.setVisibility(View.VISIBLE);
            }
            // agregar hilos a la list que tenemos
            taskList.add(this);
        }

        @Override
        protected String doInBackground(String... params) {

            /*for (int i = 0; i<= 10; i++){
                    publishProgress("Numero: " + i);
                try {
                    Thread.sleep(1000);// eN MILISEGUNDOS
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }*/

            String content = HttpManager.getData(params[0]);

            //return "Terminamos";
            return content;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            //remover el hilo
            /*taskList.remove(this);
            if (taskList.size() == 0){
                progressBar.setVisibility(View.GONE);
            }*/

            //llamado a parsear los datos XML
            //usuarioList = UsuarioXMLParser.parser(result);

            //llamado a parsear los datos JSON


            if (result == null){
                Toast.makeText(MainActivity.this, "No se pudo conectar", Toast.LENGTH_SHORT).show();
                progressBar.setVisibility(View.GONE);
                return; // para no llegar al final para que no salga error
            }

            usuarioList = UsuarioJSONParser.parse(result);

            cargarDatos();
            progressBar.setVisibility(View.GONE);





        }

        @Override
        protected void onProgressUpdate(String... values) {
//            super.onProgressUpdate(values);
           // cargarDatos(values[0]);
        }
    }
}
