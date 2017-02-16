package com.avielniego.debitmanager.ui.debitList

import android.content.Context
import android.database.Cursor
import android.os.Bundle
import android.support.v4.app.LoaderManager
import android.support.v4.content.CursorLoader
import android.support.v4.content.Loader
import com.avielniego.debitmanager.databaseAccess.DebitContract.DebitEntry
import com.avielniego.debitmanager.databaseAccess.DebitCursorParser
import com.avielniego.debitmanager.messageParser.Debit

class DebitsLoader(val context: Context, val onDebitsLoaded: (List<Debit>) -> Unit): LoaderManager.LoaderCallbacks<Cursor>{

    companion object {
        val DEBITS_LOADER_ID = 0
    }

    override fun onCreateLoader(id: Int, args: Bundle?): Loader<Cursor> {
        return when (id) {
            DEBITS_LOADER_ID -> CursorLoader(context, DebitEntry.CONTENT_URI, null, null, null, null)
            else -> throw UnsupportedOperationException("Unknown loader id: $id")
        }
    }

    override fun onLoadFinished(loader: Loader<Cursor>?, data: Cursor?) {
        when (loader!!.id) {
            DEBITS_LOADER_ID -> onDebitLoadFinished(data)
        }
    }

    private fun onDebitLoadFinished(data: Cursor?) {
        if(data == null) return

        onDebitsLoaded(DebitCursorParser().parse(data))
    }

    override fun onLoaderReset(loader: Loader<Cursor>?) {
        onDebitsLoaded(emptyList())
    }
}

