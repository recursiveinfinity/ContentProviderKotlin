package com.example.contentproviderdemokotlin

import android.app.Activity
import android.content.ContentValues
import android.database.Cursor
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.LoaderManager
import android.support.v4.app.Person
import android.support.v4.content.CursorLoader
import android.support.v4.content.Loader
import android.view.inputmethod.InputMethodManager
import android.widget.SimpleCursorAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), LoaderManager.LoaderCallbacks<Cursor> {

    private lateinit var simpleCursorAdapter: SimpleCursorAdapter



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fromColumns = arrayOf(PersonEntry.COLUMN_NAME,
            PersonEntry.COLUMN_EMAIL,
            PersonEntry.COLUMN_AGE)

        val toViews = intArrayOf(R.id.tvName, R.id.tvEmail, R.id.tvAge)

        simpleCursorAdapter = SimpleCursorAdapter(this,
            R.layout.item_person,
            null,
            fromColumns,
            toViews,
            0)

        lvResults.adapter = simpleCursorAdapter

        supportLoaderManager.initLoader(100, Bundle(), this)


        btnSave.setOnClickListener {
            saveData()
            hideKeyboard()
        }
    }

    private fun hideKeyboard() {
        val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(currentFocus.windowToken, 0)
    }

    private fun saveData() {
        val contentValues = ContentValues()
        contentValues.put(PersonEntry.COLUMN_NAME, etName.text.toString())
        contentValues.put(PersonEntry.COLUMN_EMAIL, etEmail.text.toString())
        contentValues.put(PersonEntry.COLUMN_AGE, etAge.text.toString())

        contentResolver.insert(PersonEntry.PEOPLE_TABLE_URI,
            contentValues)



        supportLoaderManager.restartLoader(100, Bundle(), this)
    }

    override fun onCreateLoader(p0: Int, p1: Bundle?): Loader<Cursor> {
        val projection = arrayOf(PersonEntry.COLUMN_NAME,
            PersonEntry.COLUMN_EMAIL,
            PersonEntry.COLUMN_AGE,
            PersonEntry.ID)
        return CursorLoader(this,
            PersonEntry.PEOPLE_TABLE_URI,
            projection,
            null,
            null,
            null)
    }

    override fun onLoadFinished(p0: Loader<Cursor>, cursor: Cursor?) {
        simpleCursorAdapter.swapCursor(cursor)
    }

    override fun onLoaderReset(p0: Loader<Cursor>) {
        simpleCursorAdapter.swapCursor(null)
    }
}
