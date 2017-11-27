package com.example.a2dam.accesodb;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Profesores extends AppCompatActivity implements View.OnClickListener{

    Button a,g;
    EditText nombre,edad,ciclo,curso,nota;
    private MyDBAdapter dbAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profesores);

        dbAdapter = new MyDBAdapter(this);
        dbAdapter.open();

        a = (Button) findViewById(R.id.atras);
        g = (Button) findViewById(R.id.guardar);
        nombre = (EditText) findViewById(R.id.nombre);
        edad = (EditText) findViewById(R.id.edad);
        ciclo = (EditText) findViewById(R.id.ciclo);
        curso = (EditText) findViewById(R.id.curso);
        nota = (EditText) findViewById(R.id.nota);

        a.setOnClickListener(this);
        g.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.atras){
            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
        }

        if(v.getId()==R.id.guardar){

            dbAdapter.insertarProfesor(nombre.getText().toString(),edad.getText().toString(),
                    ciclo.getText().toString(),curso.getText().toString(),nota.getText().toString());

            Toast.makeText(this, "Profesor guardado", Toast.LENGTH_SHORT).show();

        }
    }
}
