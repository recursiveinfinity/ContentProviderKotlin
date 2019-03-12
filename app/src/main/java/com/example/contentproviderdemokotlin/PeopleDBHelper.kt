package com.example.contentproviderdemokotlin

import android.app.Person
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class PeopleDBHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("CREATE TABLE " + PersonEntry.TABLE_NAME + " (" +
        PersonEntry.ID + " INTEGER PRIMARY KEY, " +
        PersonEntry.COLUMN_NAME + " TEXT NOT NULL, " +
        PersonEntry.COLUMN_AGE + " TEXT NOT NULL, " +
        PersonEntry.COLUMN_EMAIL + " TEXT NOT NULL);")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }

}