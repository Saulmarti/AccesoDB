package com.example.a2dam.accesodb;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by 2dam on 27/11/2017.
 */

public class MyDBAdapter {

    // Definiciones y constantes
    private static final String DATABASE_NAME = "dbdiscos.db";
    private static final String DATABASE_TABLE = "estudiantes";
    private static final String DATABASE_TABLE2 = "profesores";
    private static final int DATABASE_VERSION = 1;

    private static final String nombre = "nombre";
    private static final String edad = "edad";
    private static final String ciclo = "ciclo";
    private static final String curso = "curso";
    private static final String nota = "nota";

    // Contexto de la aplicación que usa la base de datos
    private final Context context;
    // Clase SQLiteOpenHelper para crear/actualizar la base de datos
    private MyDbHelper dbHelper;
    // Instancia de la base de datos
    private SQLiteDatabase db;

    private static final String DATABASE_CREATE = "CREATE TABLE "+DATABASE_TABLE+" (_id integer primary key autoincrement, title text, year integer);";
    private static final String DATABASE_DROP = "DROP TABLE IF EXISTS "+DATABASE_TABLE+";";


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

    public void insertarEstudiante(String n,String e,String c,String cu,String nt){
        //Creamos un nuevo registro de valores a insertar
        ContentValues newValues = new ContentValues();
        //Asignamos los valores de cada campo
        newValues.put(nombre,n);
        newValues.put(edad,e);
        newValues.put(ciclo,c);
        newValues.put(curso,cu);
        newValues.put(nota,nt);

        db.insert(DATABASE_TABLE,null,newValues);
    }

    public void insertarProfesor(String n,String e,String c,String cu,String nt){
        //Creamos un nuevo registro de valores a insertar
        ContentValues newValues = new ContentValues();
        //Asignamos los valores de cada campo
        newValues.put(nombre,n);
        newValues.put(edad,e);
        newValues.put(ciclo,c);
        newValues.put(curso,cu);
        newValues.put(nota,nt);

        db.insert(DATABASE_TABLE2,null,newValues);
    }

    private static class MyDbHelper extends SQLiteOpenHelper {


        public MyDbHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(DATABASE_CREATE);

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL(DATABASE_DROP);
            onCreate(db);

        }
    }
}
