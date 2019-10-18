package com.example.controlpunetas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity{

    Button desgraciado;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        desgraciado = findViewById(R.id.d);
        final Comunicacion com = new Comunicacion();

        Thread t= new Thread(com);
        t.start();

        desgraciado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                com.enviar("a");

            }
        });


    }

}
