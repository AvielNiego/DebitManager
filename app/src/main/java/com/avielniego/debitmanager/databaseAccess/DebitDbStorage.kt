package com.avielniego.debitmanager.databaseAccess

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import com.avielniego.debitmanager.databaseAccess.DebitContract.DebitEntry
import com.avielniego.debitmanager.messageParser.Debit
import com.avielniego.debitmanager.messageParser.DebitStorage
import com.avielniego.debitmanager.messageParser.Message

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
        val d = mapCursorToDebits(c)
        c.close()
        return d
    }

    private fun mapCursorToDebits(c: Cursor): List<Debit> {
        val debits: MutableList<Debit> = mutableListOf()
        if (!c.moveToFirst()) return emptyList()
        do{
            debits.add(createDebitFromCurrentCursorPosition(c))
        } while (c.moveToNext())
        return debits
    }

    private fun createDebitFromCurrentCursorPosition(c: Cursor): Debit {
        val creditCardName = c.getString(c.getColumnIndex(DebitEntry.COLUMN_CREDIT_CARD_COMPANY_NAME))
        val messageBody = c.getString(c.getColumnIndex(DebitEntry.COLUMN_MESSAGE_BODY))
        val sum = c.getDouble(c.getColumnIndex(DebitEntry.COLUMN_SUM))
        val businessName = c.getString(c.getColumnIndex(DebitEntry.COLUMN_BUSINESS_NAME))
        val debit = Debit(Message(creditCardName, messageBody), sum, businessName)
        return debit
    }
}