package com.example.e009_persistencia;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

// 21/03/2023

public class AdministradorBBDD extends SQLiteOpenHelper {

    // Sentencia SQL para crear la tabla
    String sqlCreate = "create table articulos(codigo int primary key, descripcion text, precio real)";

    public AdministradorBBDD(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sqlCreate);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // en upgrade hay que poner algo
        db.execSQL(sqlCreate);
    }
}
