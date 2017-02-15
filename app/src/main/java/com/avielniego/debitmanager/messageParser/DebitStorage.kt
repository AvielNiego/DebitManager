package com.avielniego.debitmanager.messageParser

interface DebitStorage {
    fun store(debit: Debit)
    fun getAll(): List<Debit>
}