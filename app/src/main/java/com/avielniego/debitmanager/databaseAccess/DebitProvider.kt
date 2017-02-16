package com.avielniego.debitmanager.databaseAccess

import android.annotation.TargetApi
import android.content.ContentProvider
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.net.Uri
import com.avielniego.debitmanager.databaseAccess.DebitContract.DebitEntry

class DebitProvider : ContentProvider() {

    lateinit var debitDbHelper: DebitDbHelper
    val uriMatcher = buildUriMatcher()

    companion object {
        val DEBIT = 100
    }

    override fun onCreate(): Boolean {
        debitDbHelper = DebitDbHelper(context)
        return true
    }

    private fun buildUriMatcher(): UriMatcher {
        val matcher = UriMatcher(UriMatcher.NO_MATCH)
        matcher.addURI(DebitContract.CONTENT_AUTHORITY, DebitContract.PATH_DEBIT, DEBIT)
        return matcher
    }

    override fun insert(uri: Uri?, values: ContentValues?): Uri {
        context.contentResolver.notifyChange(uri, null)
        return when (uriMatcher.match(uri)) {
            DEBIT -> DebitEntry.buildUri(debitDbHelper.writableDatabase.insert(DebitEntry.TABLE_NAME, null, values))
            else -> throw UnsupportedOperationException("Unknown uri: " + uri)
        }
    }

    override fun query(uri: Uri?, projection: Array<out String>?, selection: String?, selectionArgs: Array<out String>?, sortOrder: String?): Cursor {
        val cursor = getQueryCursor(projection, selection, selectionArgs, sortOrder, uri)
        cursor.setNotificationUri(context.contentResolver, uri)
        return cursor
    }

    private fun getQueryCursor(projection: Array<out String>?, selection: String?, selectionArgs: Array<out String>?, sortOrder: String?, uri: Uri?): Cursor {
        return when (uriMatcher.match(uri)) {
            DEBIT -> debitDbHelper.readableDatabase.query(DebitEntry.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder)
            else -> throw UnsupportedOperationException("Unknown uri: " + uri)
        }
    }

    override fun update(uri: Uri?, values: ContentValues?, selection: String?, selectionArgs: Array<out String>?): Int {
        context.contentResolver.notifyChange(uri, null)
        return when (uriMatcher.match(uri)) {
            DEBIT -> debitDbHelper.writableDatabase.update(DebitEntry.TABLE_NAME, values, selection, selectionArgs)
            else -> throw UnsupportedOperationException("Unknown uri: " + uri)
        }
    }

    override fun delete(uri: Uri?, selection: String?, selectionArgs: Array<out String>?): Int {
        context.contentResolver.notifyChange(uri, null)
        return when (uriMatcher.match(uri)) {
            DEBIT -> debitDbHelper.writableDatabase.delete(DebitEntry.TABLE_NAME, selection, selectionArgs)
            else -> throw UnsupportedOperationException("Unknown uri: " + uri)
        }
    }

    override fun getType(uri: Uri?): String {
        return when (uriMatcher.match(uri)) {
            DEBIT -> DebitEntry.CONTENT_TYPE
            else -> throw UnsupportedOperationException("Unknown uri: " + uri)
        }
    }

    @TargetApi(11)
    override fun shutdown() {
        debitDbHelper.close()
        super.shutdown()
    }
}