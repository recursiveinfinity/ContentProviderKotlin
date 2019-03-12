package com.example.contentproviderdemokotlin

import android.net.Uri
import android.provider.BaseColumns
import android.provider.BaseColumns._ID

const val CONTENT_PROVIDER_AUTHORITY = "com.example.contentproviderdemokotlin"
val BASE_CONTENT_PROVIDER_URI = Uri.parse("content://$CONTENT_PROVIDER_AUTHORITY")
const val PATH_PERSON_TABLE = "people"
const val DATABASE_NAME = "peopleDB"
const val DATABASE_VERSION = 1

class PersonEntry : BaseColumns {
    companion object {
        val PEOPLE_TABLE_URI = BASE_CONTENT_PROVIDER_URI.buildUpon()
            .appendPath(PATH_PERSON_TABLE).build()
        val CONTENT_TYPE = "vnd.android.cursor.dir/$PEOPLE_TABLE_URI/$PATH_PERSON_TABLE"
        const val ID = _ID
        const val TABLE_NAME = "peopleTable"
        const val COLUMN_NAME = "personName"
        const val COLUMN_AGE = "personAge"
        const val COLUMN_EMAIL = "personEmail"

    }
}