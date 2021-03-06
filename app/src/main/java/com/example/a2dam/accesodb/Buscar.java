package com.example.a2dam.accesodb;

import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;

import static android.R.attr.text;
import static android.R.attr.value;

public class Buscar extends AppCompatActivity implements View.OnClickListener {

    Button prof, est, ciclo,curso,cicloycurso,todos,pya;
    MyDBAdapter dbAdapter;
    TextView cont;
    EditText txtCiclo,txtCurso;
    ListView listview;
    int opcion=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar);

        listview = (ListView) findViewById(R.id.list);

        dbAdapter = new MyDBAdapter(this);
        dbAdapter.open();

        prof = (Button) findViewById(R.id.profesor);
        est = (Button) findViewById(R.id.estudiante);
        ciclo = (Button) findViewById(R.id.ciclo);
        curso = (Button) findViewById(R.id.curso);
        cicloycurso = (Button) findViewById(R.id.cicloycurso);
        todos = (Button) findViewById(R.id.todos);
        cont = (TextView) findViewById(R.id.contenedor);
        pya = (Button) findViewById(R.id.profalum);
        txtCiclo = (EditText) findViewById(R.id.textCiclo);
        txtCurso = (EditText) findViewById(R.id.textCurso);

        curso.setOnClickListener(this);
        ciclo.setOnClickListener(this);
        cicloycurso.setOnClickListener(this);
        todos.setOnClickListener(this);
        prof.setOnClickListener(this);
        est.setOnClickListener(this);
        pya.setOnClickListener(this);


        curso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                pulsado(curso);
                dejarDePulsar(ciclo);
                dejarDePulsar(cicloycurso);
                dejarDePulsar(todos);
                String textCurso = txtCurso.getText().toString();

                if(textCurso!=null) {
                switch (opcion) {

                    case 1:


                    ArrayList<String> cursos = dbAdapter.recuperarCurso("profesores",textCurso);
                    ArrayAdapter adapter = new ArrayAdapter<String>(Buscar.this,
                            R.layout.support_simple_spinner_dropdown_item , cursos);
                    listview.setAdapter(adapter);
                        break;

                    case 2:
                        ArrayList<String> cursos2 = dbAdapter.recuperarCurso("estudiantes",textCurso);
                        ArrayAdapter adapter2 = new ArrayAdapter<String>(Buscar.this,
                                R.layout.support_simple_spinner_dropdown_item, cursos2);
                        listview.setAdapter(adapter2);

                }

            }else{
                    Toast.makeText(Buscar.this, "Error", Toast.LENGTH_SHORT).show();
                }}
        });


        ciclo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pulsado(ciclo);
                dejarDePulsar(curso);
                dejarDePulsar(cicloycurso);
                dejarDePulsar(todos);
                String textCiclo = txtCiclo.getText().toString();
                if(textCiclo!=null) {
                    switch (opcion) {

                        case 1:
                            ArrayList<String> ciclos = dbAdapter.recuperarCiclo("profesores",textCiclo);
                            ArrayAdapter adapter = new ArrayAdapter<String>(Buscar.this,
                                    android.R.layout.simple_list_item_1, ciclos);
                            listview.setAdapter(adapter);

                            break;

                        case 2:

                            ArrayList<String> ciclos2 = dbAdapter.recuperarCiclo("estudiantes",textCiclo);
                            ArrayAdapter adapter2 = new ArrayAdapter<String>(Buscar.this,
                                    android.R.layout.simple_list_item_1, ciclos2);
                            listview.setAdapter(adapter2);

                            break;
                    }

                }else {
                    Toast.makeText(Buscar.this, "Error", Toast.LENGTH_SHORT).show();
                }}
        });


        cicloycurso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pulsado(cicloycurso);
                dejarDePulsar(ciclo);
                dejarDePulsar(curso);
                dejarDePulsar(todos);
                String textCiclo = txtCiclo.getText().toString();
                String textCurso = txtCurso.getText().toString();

                if(textCiclo!=null && textCurso!=null){


                    switch (opcion) {

                        case 1:

                            ArrayList<String> cicloycursos = dbAdapter.recuperarCicloycurso("profesores", textCiclo, textCurso);
                            ArrayAdapter adapter = new ArrayAdapter<String>(Buscar.this,
                                    android.R.layout.simple_list_item_1, cicloycursos);
                            listview.setAdapter(adapter);
                            break;

                        case 2:
                            ArrayList<String> cicloycursos2 = dbAdapter.recuperarCicloycurso("estudiantes", textCiclo, textCurso);
                            ArrayAdapter adapter2 = new ArrayAdapter<String>(Buscar.this,
                                    android.R.layout.simple_list_item_1, cicloycursos2);
                            listview.setAdapter(adapter2);

                            break;
                    }
                }else{
                    Toast.makeText(Buscar.this, "Error", Toast.LENGTH_SHORT).show();
                }

            }
        });

        todos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pulsado(todos);
                dejarDePulsar(ciclo);
                dejarDePulsar(cicloycurso);
                dejarDePulsar(curso);

                switch (opcion) {

                    case 1:

                    ArrayList<String> todos = dbAdapter.recuperarTodo("profesores");
                    ArrayAdapter adapter = new ArrayAdapter<String>(Buscar.this,
                            android.R.layout.simple_list_item_1, todos);
                    listview.setAdapter(adapter);
                        break;

                    case 2:
                        ArrayList<String> todos2 = dbAdapter.recuperarTodo("estudiantes");
                        ArrayAdapter adapter2 = new ArrayAdapter<String>(Buscar.this,
                                android.R.layout.simple_list_item_1, todos2);
                        listview.setAdapter(adapter2);

                        break;
                }
            }
        });


    }

    @Override
    public void onClick(View v) {

        if(v.getId()==R.id.profesor) {
            opcion=1;
            pulsado(prof);
            dejarDePulsar(est);
            dejarDePulsar(pya);

        }

        if(v.getId()==R.id.estudiante) {
            opcion=2;
            pulsado(est);
            dejarDePulsar(prof);
            dejarDePulsar(pya);

        }

        if(v.getId()==R.id.profalum){

            pulsado(pya);
            dejarDePulsar(est);
            dejarDePulsar(prof);
            dejarDePulsar(ciclo);
            dejarDePulsar(curso);
            dejarDePulsar(cicloycurso);
            dejarDePulsar(todos);

            ArrayList<String> todos = dbAdapter.recuperarAlyProf();
            ArrayAdapter adapter = new ArrayAdapter<String>(Buscar.this,
                    R.layout.support_simple_spinner_dropdown_item, todos);
            listview.setAdapter(adapter);

        }

    }

    public void pulsado(Button boton){
        boton.setBackgroundColor(Color.DKGRAY);
        boton.setTextColor(Color.WHITE);
    }

    public void dejarDePulsar(Button boton){

        boton.setBackgroundColor(Color.WHITE);
        boton.setTextColor(Color.BLACK);


    }


}
