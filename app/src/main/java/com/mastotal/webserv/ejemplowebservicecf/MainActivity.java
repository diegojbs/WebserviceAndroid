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

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button boton;
    TextView textView;
    ProgressBar progressBar;
    List<MyYTask> taskList;

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
                MyYTask task = new MyYTask();

                //task.execute();
                //Cambiar a forma paralela
                task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

            }
        });
    }

    public void cargarDatos(String datos){
        textView.append(datos + "\n");
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
    }


    private class MyYTask extends AsyncTask<String, String, String>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            cargarDatos("Inicio de carga");

            if(taskList.size() == 0) {
                progressBar.setVisibility(View.VISIBLE);
            }
            // agregar hilos a la list que tenemos
            taskList.add(this);
        }

        @Override
        protected String doInBackground(String... params) {

            for (int i = 0; i<= 10; i++){
                    publishProgress("Numero: " + i);
                try {
                    Thread.sleep(1000);// eN MILISEGUNDOS
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            return "Terminamos";
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            cargarDatos(s);

            //remover el hilo
            taskList.remove(this);
            if (taskList.size() == 0){
                progressBar.setVisibility(View.GONE);
            }



        }

        @Override
        protected void onProgressUpdate(String... values) {
//            super.onProgressUpdate(values);
            cargarDatos(values[0]);
        }
    }
}
