package com.example.a2dam.accesodb;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button p,e,b,bd;
    int Request_Value=0, Request_ValueProf=1, Request_ValueBuscar=2;
    private MyDBAdapter dbAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbAdapter = new MyDBAdapter(this);
        dbAdapter.open();

        p = (Button) findViewById(R.id.profesores);
        e = (Button) findViewById(R.id.estudiantes);
        b = (Button) findViewById(R.id.buscar);
        bd = (Button) findViewById(R.id.borrarDB);

        p.setOnClickListener(this);
        e.setOnClickListener(this);
        b.setOnClickListener(this);
        bd.setOnClickListener(this);
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

        if (v.getId()==R.id.buscar){
            Intent i = new Intent(this, Buscar.class);
            startActivityForResult(i,Request_ValueBuscar);

        }

        if(v.getId()==R.id.borrarDB){
            dbAdapter.eliminarBaseDeDAtos();
            Toast.makeText(this, "Base de datos borrada", Toast.LENGTH_SHORT).show();
        }
    }
}
