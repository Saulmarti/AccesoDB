package com.example.a2dam.accesodb;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Profesores extends AppCompatActivity implements View.OnClickListener{

    private Button a,g;
    private EditText nombreP,edadP,cicloP,cursoP,despachoP;
    private MyDBAdapter dbAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profesores);

        dbAdapter = new MyDBAdapter(this);
        dbAdapter.open();

        a = (Button) findViewById(R.id.atrasP);
        g = (Button) findViewById(R.id.guardarP);
        nombreP = (EditText) findViewById(R.id.nombreP);
        edadP = (EditText) findViewById(R.id.edadP);
        cicloP = (EditText) findViewById(R.id.cicloP);
        cursoP = (EditText) findViewById(R.id.cursoP);
        despachoP = (EditText) findViewById(R.id.despachoP);

        a.setOnClickListener(this);
        g.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.atrasP){
            finish();
        }

        if(v.getId()==R.id.guardarP){

            dbAdapter.insertarProfesor(nombreP.getText().toString(),edadP.getText().toString(),
                    cicloP.getText().toString(),cursoP.getText().toString(),despachoP.getText().toString());
            Toast.makeText(this, "Profesor guardado", Toast.LENGTH_SHORT).show();

            nombreP.setText("");
            edadP.setText("");
            cicloP.setText("");
            cursoP.setText("");
            despachoP.setText("");


        }
    }
}
