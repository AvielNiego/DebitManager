package com.avielniego.debitmanager.databaseAccess

import android.database.Cursor
import com.avielniego.debitmanager.messageParser.Debit
import com.avielniego.debitmanager.messageParser.Message

class DebitCursorParser {

    fun parse(c: Cursor): List<Debit> {
        val debits: MutableList<Debit> = mutableListOf()
        if (!c.moveToFirst()) return emptyList()
        do{
            debits.add(createDebitFromCurrentCursorPosition(c))
        } while (c.moveToNext())
        return debits
    }

    private fun createDebitFromCurrentCursorPosition(c: Cursor): Debit {
        val creditCardName = c.getString(c.getColumnIndex(DebitContract.DebitEntry.COLUMN_CREDIT_CARD_COMPANY_NAME))
        val messageBody = c.getString(c.getColumnIndex(DebitContract.DebitEntry.COLUMN_MESSAGE_BODY))
        val sum = c.getDouble(c.getColumnIndex(DebitContract.DebitEntry.COLUMN_SUM))
        val businessName = c.getString(c.getColumnIndex(DebitContract.DebitEntry.COLUMN_BUSINESS_NAME))
        val debit = Debit(Message(creditCardName, messageBody), sum, businessName)
        return debit
    }
}