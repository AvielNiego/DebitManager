package com.avielniego.debitmanager.databaseAccess

import android.content.ContentResolver
import android.content.ContentUris
import android.net.Uri
import android.provider.BaseColumns

class DebitContract {

    companion object {
        val CONTENT_AUTHORITY = "com.avielniego.debitmanager"
        val BASE_CONTENT_URI: Uri = Uri.parse("content://" + CONTENT_AUTHORITY)
        val PATH_DEBIT = "debit"
    }

    class DebitEntry : BaseColumns {
        companion object {
            val TABLE_NAME = "debit"

            val COLUMN_ID = BaseColumns._ID
            val COLUMN_CREDIT_CARD_COMPANY_NAME = "credit_card_company_name"
            val COLUMN_MESSAGE_BODY = "message_body"
            val COLUMN_SUM = "sum"
            val COLUMN_BUSINESS_NAME = "business_name"

            val CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_DEBIT).build()

            val CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_DEBIT
            val CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_DEBIT
            fun buildUri(id: Long): Uri = ContentUris.withAppendedId(CONTENT_URI, id)
        }
    }
}