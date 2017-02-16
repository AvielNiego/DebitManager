package com.avielniego.debitmanager.databaseAccess

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.avielniego.debitmanager.databaseAccess.DebitContract.DebitEntry

class DebitDbHelper(context: Context): SQLiteOpenHelper(context, DATABASE_NAME, null, VERSION) {

    companion object {
        val DATABASE_NAME = "debit.db"
        val VERSION = 1
    }

    override fun onCreate(database: SQLiteDatabase?) {
        if (database == null) return
        val debitTableCreationQuery =
                "CREATE TABLE ${DebitEntry.TABLE_NAME} (" +
                        "${DebitEntry.COLUMN_ID} INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "${DebitEntry.COLUMN_CREDIT_CARD_COMPANY_NAME} TEXT NOT NULL," +
                        "${DebitEntry.COLUMN_MESSAGE_BODY} TEXT NOT NULL," +
                        "${DebitEntry.COLUMN_SUM} REAL NOT NULL," +
                        "${DebitEntry.COLUMN_BUSINESS_NAME} TEXT NOT NULL" +
                        ")"
        database.execSQL(debitTableCreationQuery)
    }

    override fun onUpgrade(database: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        if (database == null) return
        database.execSQL("DROP TABLE IF EXISTS " + DebitEntry.TABLE_NAME)
        onCreate(database)
    }

}