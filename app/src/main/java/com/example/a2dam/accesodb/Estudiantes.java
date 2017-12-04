package com.example.a2dam.accesodb;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Estudiantes extends AppCompatActivity implements View.OnClickListener{

    private Button bt,g,b;
    private EditText nombre,edad,ciclo,curso,nota,idE;
    private MyDBAdapter dbAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estudiantes);

        dbAdapter = new MyDBAdapter(this);
        dbAdapter.open();

        b = (Button) findViewById(R.id.borrar);
        bt = (Button) findViewById(R.id.borrarTabla);
        g = (Button) findViewById(R.id.guardar);

        nombre = (EditText) findViewById(R.id.nombre);
        edad = (EditText) findViewById(R.id.edad);
        ciclo = (EditText) findViewById(R.id.ciclo);
        curso = (EditText) findViewById(R.id.curso);
        nota = (EditText) findViewById(R.id.nota);
        idE = (EditText) findViewById(R.id.idE);

        bt.setOnClickListener(this);
        g.setOnClickListener(this);
        b.setOnClickListener(this);



    }

    @Override
    public void onClick(View v) {

        if(v.getId()==R.id.borrarTabla){
            dbAdapter.eliminarTabla("estudiantes");
            Toast.makeText(this, "Tabla de estudiantes borrada", Toast.LENGTH_SHORT).show();
        }

        if(v.getId()==R.id.guardar){

            if(nombre.getText().toString().isEmpty() || edad.getText().toString().isEmpty() || ciclo.getText().toString().isEmpty() || curso.getText().toString().isEmpty() || nota.getText().toString().isEmpty()){
                Toast.makeText(this, "Rellena todos los datos correctamente", Toast.LENGTH_SHORT).show();

            }else {


                dbAdapter.insertarEstudiante(nombre.getText().toString(), Integer.valueOf(edad.getText().toString()),
                        ciclo.getText().toString(), Integer.valueOf(curso.getText().toString()), Integer.valueOf(nota.getText().toString()));
                Toast.makeText(this, "Estudiante guardado", Toast.LENGTH_SHORT).show();


                nombre.setText("");
                edad.setText("");
                ciclo.setText("");
                curso.setText("");
                nota.setText("");
            }


        }


        if (v.getId()==R.id.borrar){

            dbAdapter.eliminarEstudiante(Integer.valueOf(String.valueOf(idE.getText())));
            Toast.makeText(this, "Estudiante "+idE.getText()+" borrado", Toast.LENGTH_SHORT).show();
            idE.setText("");

        }
    }
}
