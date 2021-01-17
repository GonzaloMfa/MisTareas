package com.example.mistareas.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class ControladorDB extends SQLiteOpenHelper {
    public ControladorDB(@Nullable Context context) {
        super(context,"com.example.mistareas.db", null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE TAREAS(ID INTEGER PRIMARY KEY AUTOINCREMENT, NOMBRE TEXT NOT NULL);");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    // metodo para insertar en la tabla
        //1 -> abrir base de datos
        //2 -> ejecutar la accion
        //3 -> cerrar la base de datos
    public void addTarea(String nombre){
        ContentValues registro = new ContentValues();
        /**
         * si tuviesemos más campos que quisiesemos insertar en la base de datos
         * deberíamos hacer esto en todos
         */
        registro.put("NOMBRE", nombre);
        // abrimos -> lectura escritura
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert("TAREAS", null, registro);
        // db.execSQL("INSERT INTO TAREAS VALUES (null, ' + nombre + ');");
        db.close();
    }
    public String [] obtenerTareas(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM TAREAS", null);
        String [] tareas;
        int regs = cursor.getCount();
        if(regs == 0){
            db.close();
            return null;
        } else{
             tareas = new String[regs];
            cursor.moveToFirst();
            for(int i=0; i< regs; i++){
                tareas[i]=cursor.getString(1);
                cursor.moveToNext();
            }
        }
        db.close();
        return tareas;
    }
    public int numeroRegistros(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM TAREAS", null);
        return cursor.getCount();
    }
    public void borrarTareas(String tarea){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("TAREAS", "NOMBRE=?", new String []{tarea});
        db.close();
    }
    public void updateTarea(String tarea){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        db.update("TAREAS", cv, "NOMBRE=?", new String[]{tarea});
        db.close();
    }
}
