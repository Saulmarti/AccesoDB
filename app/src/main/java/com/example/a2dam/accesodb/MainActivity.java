package com.example.a2dam.accesodb;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button p,e;
    int Request_Value=0, Request_ValueProf=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        p = (Button) findViewById(R.id.profesores);
        e = (Button) findViewById(R.id.estudiantes);

        p.setOnClickListener(this);
        e.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {

        if(v.getId()==R.id.profesores){
            Intent i2 = new Intent(this, Profesores.class);
            startActivityForResult(i2, Request_ValueProf);
        }

        if (v.getId()==R.id.estudiantes){
            Intent i = new Intent(this, Estudiantes.class);
            startActivityForResult(i,Request_Value);

        }
    }
}
