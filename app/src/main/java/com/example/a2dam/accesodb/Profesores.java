package com.example.a2dam.accesodb;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Profesores extends AppCompatActivity implements View.OnClickListener{

    private Button bt,g,b;
    private EditText nombreP,edadP,cicloP,cursoP,despachoP,idP;
    private MyDBAdapter dbAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profesores);

        dbAdapter = new MyDBAdapter(this);
        dbAdapter.open();

        b = (Button) findViewById(R.id.borrarP);
        bt = (Button) findViewById(R.id.borrarTablaP);
        g = (Button) findViewById(R.id.guardarP);

        nombreP = (EditText) findViewById(R.id.nombreP);
        edadP = (EditText) findViewById(R.id.edadP);
        cicloP = (EditText) findViewById(R.id.cicloP);
        cursoP = (EditText) findViewById(R.id.cursoP);
        despachoP = (EditText) findViewById(R.id.despachoP);
        idP = (EditText) findViewById(R.id.idP);

        bt.setOnClickListener(this);
        g.setOnClickListener(this);
        b.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        if(v.getId()==R.id.borrarTablaP){
            dbAdapter.eliminarTabla("profesores");
            Toast.makeText(this, "Tabla de profesores borrada", Toast.LENGTH_SHORT).show();
        }

        if(v.getId()==R.id.guardarP){

            if(nombreP.getText().toString().isEmpty() || edadP.getText().toString().isEmpty() || cicloP.getText().toString().isEmpty() || cursoP.getText().toString().isEmpty() || despachoP.getText().toString().isEmpty()) {

                Toast.makeText(this, "Rellena todos los datos correctamente", Toast.LENGTH_SHORT).show();

            }else {
                dbAdapter.insertarProfesor(nombreP.getText().toString(), Integer.valueOf(edadP.getText().toString()),
                        cicloP.getText().toString(), Integer.valueOf(cursoP.getText().toString()), despachoP.getText().toString());
                Toast.makeText(this, "Profesor guardado", Toast.LENGTH_SHORT).show();

                nombreP.setText("");
                edadP.setText("");
                cicloP.setText("");
                cursoP.setText("");
                despachoP.setText("");
            }
        }

        if (v.getId()==R.id.borrarP){

            dbAdapter.eliminarProfesor(Integer.valueOf(String.valueOf(idP.getText())));
            Toast.makeText(this, "Profesor "+idP.getText()+" borrado", Toast.LENGTH_SHORT).show();
            idP.setText("");
        }

    }
}
