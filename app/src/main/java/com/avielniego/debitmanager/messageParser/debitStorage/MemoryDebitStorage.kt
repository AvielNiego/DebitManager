package com.avielniego.debitmanager.messageParser.debitStorage

import com.avielniego.debitmanager.messageParser.Debit
import com.avielniego.debitmanager.messageParser.DebitStorage

class MemoryDebitStorage: DebitStorage {

    val debits: MutableList<Debit> = mutableListOf()

    override fun getAll(): List<Debit> {
        return debits
    }

    override fun store(debit: Debit) {
        debits.add(debit)
    }
}