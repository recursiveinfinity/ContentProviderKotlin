package com.example.contentproviderdemokotlin

import android.app.Person
import android.content.ContentProvider
import android.content.ContentUris
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.net.Uri
import java.lang.UnsupportedOperationException

class PeopleContentProvider : ContentProvider() {

    val ALL_PEOPLE = 0

    lateinit var peopleDBHelper:PeopleDBHelper

    private val uriMatcher: UriMatcher by lazy {
        UriMatcher(UriMatcher.NO_MATCH)
    }


    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        val database = peopleDBHelper.writableDatabase
        val rowId = database.insert(PersonEntry.TABLE_NAME, null, values)
        if (rowId > 0) {
            return ContentUris.withAppendedId(PersonEntry.PEOPLE_TABLE_URI, rowId)
        }
        return null
    }

    override fun query(
        uri: Uri,
        projection: Array<String>?,
        selection: String?,
        selectionArgs: Array<String>?,
        sortOrder: String?
    ): Cursor? {
        val database = peopleDBHelper.readableDatabase
        var cursor: Cursor? = null

        when(uriMatcher.match(uri)) {
            ALL_PEOPLE -> cursor = database.query(PersonEntry.TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                sortOrder)
        }
        return cursor
    }

    override fun onCreate(): Boolean {
       peopleDBHelper = PeopleDBHelper(context)
         uriMatcher.addURI(CONTENT_PROVIDER_AUTHORITY,
               PATH_PERSON_TABLE,
               ALL_PEOPLE)
       return true
    }

    override fun update(uri: Uri, values: ContentValues?, selection: String?, selectionArgs: Array<String>?): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<String>?): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getType(uri: Uri): String? {
         return when (uriMatcher.match(uri)) {
            ALL_PEOPLE -> PersonEntry.CONTENT_TYPE
            else -> throw UnsupportedOperationException("This operation is not supported")
        }
    }

}