package com.avielniego.debitmanager.databaseAccess

import android.content.ContentValues
import android.content.Context
import com.avielniego.debitmanager.databaseAccess.DebitContract.DebitEntry
import com.avielniego.debitmanager.messageParser.Debit
import com.avielniego.debitmanager.messageParser.DebitStorage

class DebitDbStorage(private val context: Context) : DebitStorage {
    override fun store(debit: Debit) {
        context.contentResolver.insert(DebitEntry.CONTENT_URI, mapToContentValue(debit))
    }

    private fun mapToContentValue(debit: Debit): ContentValues {
        val v = ContentValues()
        v.put(DebitEntry.COLUMN_CREDIT_CARD_COMPANY_NAME, debit.message.from)
        v.put(DebitEntry.COLUMN_MESSAGE_BODY, debit.message.body)
        v.put(DebitEntry.COLUMN_SUM, debit.sum)
        v.put(DebitEntry.COLUMN_BUSINESS_NAME, debit.businessName)
        return v
    }

    override fun getAll(): List<Debit> {
        val c = context.contentResolver.query(DebitEntry.CONTENT_URI, null, null, null, null)
        val d = DebitCursorParser().parse(c)
        c.close()
        return d
    }
}