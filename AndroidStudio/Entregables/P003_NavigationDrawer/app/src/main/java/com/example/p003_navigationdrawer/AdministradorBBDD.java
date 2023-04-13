package com.example.p003_navigationdrawer;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

// 21/03/2023

public class AdministradorBBDD extends SQLiteOpenHelper {

    // Sentencia SQL para crear la tabla
    String sqlCreate = "create table if not exists eventos(codigo INTEGER primary key AUTOINCREMENT, fecha text, tipo text, modulo text, estado text)";
    //String sqlDestroy = "drop table if exists eventos";

    public AdministradorBBDD(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // NO HACER EL DROP PORQUE MUERE EL PROYECTO!
        // https://stackoverflow.com/questions/37819888/android-sqlite-can%c2%b4t-create-table-after-having-it-dropped-once
        //db.execSQL(sqlDestroy);
        db.execSQL(sqlCreate);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // en upgrade hay que poner algo
        db.execSQL(sqlCreate);
    }
}