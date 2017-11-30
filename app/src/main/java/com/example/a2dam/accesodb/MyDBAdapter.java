package com.example.a2dam.accesodb;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.Editable;
import android.util.Log;

/**
 * Created by 2dam on 27/11/2017.
 */

public class MyDBAdapter {

    // Definiciones y constantes
    private static final String DATABASE_NAME = "dbusuarios.db";
    private static final String DATABASE_TABLE_Est = "estudiantes";
    private static final String DATABASE_TABLE_Prof = "profesores";
    private static final int DATABASE_VERSION = 1;

    private static final String nombre = "nombre";
    private static final String edad = "edad";
    private static final String ciclo = "ciclo";
    private static final String curso = "curso";
    private static final String nota = "nota";

    private static final String nombreP = "nombreP";
    private static final String edadP = "edadP";
    private static final String cicloP = "cicloP";
    private static final String cursoP = "cursoP";
    private static final String despachoP = "despachoP";

    // Contexto de la aplicación que usa la base de datos
    private final Context context;
    // Clase SQLiteOpenHelper para crear/actualizar la base de datos
    private MyDbHelper dbHelper;
    // Instancia de la base de datos
    private SQLiteDatabase db;

    private static final String DATABASE_CREATE_Est = "CREATE TABLE "+DATABASE_TABLE_Est+" (_id integer primary key autoincrement, nombre text, edad text, ciclo text, curso text, nota text );";
    private static final String DATABASE_CREATE_Prof = "CREATE TABLE "+DATABASE_TABLE_Prof+" (_id integer primary key autoincrement, nombreP text, edadP text, cicloP text, cursoP text, despachoP text );";
    private static final String DATABASE_DROP_Est = "DROP TABLE IF EXISTS "+DATABASE_TABLE_Est+";";
    private static final String DATABASE_DROP_Prof = "DROP TABLE IF EXISTS "+DATABASE_TABLE_Prof+";";


    public MyDBAdapter (Context c){
        context = c;
        dbHelper = new MyDbHelper(context, DATABASE_NAME, null, DATABASE_VERSION);
        //OJO open();
    }

    public void open(){

        try{
            db = dbHelper.getWritableDatabase();
        }catch(SQLiteException e){
            db = dbHelper.getReadableDatabase();
        }

    }

    public void insertarProfesor(String n2,String e2,String c2,String cu2,String nt2){
        //Creamos un nuevo registro de valores a insertar
        ContentValues newValuesProf = new ContentValues();
        //Asignamos los valores de cada campo
        newValuesProf.put(nombreP,n2);
        newValuesProf.put(edadP,e2);
        newValuesProf.put(cicloP,c2);
        newValuesProf.put(cursoP,cu2);
        newValuesProf.put(despachoP,nt2);

        db.insert(DATABASE_TABLE_Prof,null,newValuesProf);
    }

    public void insertarEstudiante(String n,String e,String c,String cu,String nt){
        //Creamos un nuevo registro de valores a insertar
        ContentValues newValuesEst = new ContentValues();
        //Asignamos los valores de cada campo
        newValuesEst.put(nombre,n);
        newValuesEst.put(edad,e);
        newValuesEst.put(ciclo,c);
        newValuesEst.put(curso,cu);
        newValuesEst.put(nota,nt);

        db.insert(DATABASE_TABLE_Est,null,newValuesEst);
    }

    public int eliminarProfesor(int idP)
    {
        return db.delete(DATABASE_TABLE_Prof, "_id=" + idP, null);
    }

    public int eliminarEstudiante(int id)
    {
        return db.delete(DATABASE_TABLE_Est, "_id=" + id, null);
    }



    private static class MyDbHelper extends SQLiteOpenHelper {


        public MyDbHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(DATABASE_CREATE_Prof);
            db.execSQL(DATABASE_CREATE_Est);


        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL(DATABASE_DROP_Prof);
            db.execSQL(DATABASE_DROP_Est);

            onCreate(db);

        }
    }
}
