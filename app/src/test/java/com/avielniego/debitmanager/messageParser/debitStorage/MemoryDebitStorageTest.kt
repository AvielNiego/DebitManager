package com.avielniego.debitmanager.messageParser.debitStorage

import com.avielniego.debitmanager.messageParser.Debit
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class MemoryDebitStorageTest {

    var debitStorage: MemoryDebitStorage = MemoryDebitStorage()

    @Before
    fun setUp() {
        debitStorage = MemoryDebitStorage()
    }

    @Test
    fun getAll_noDebitStored_shouldReturnEmptyList() {
        assertTrue(debitStorage.getAll().isEmpty())
    }

    @Test
    fun store_newDebit_shouldReturnTheStoredDebitOnGetAllAtIndex0() {
        val debit = Debit("Company", "Message", 1.0, "Business")
        debitStorage.store(debit)
        assertEquals(debit, debitStorage.getAll()[0])
    }

    @Test
    fun getAll_storeMultipleDebits_shouldReturnAListContainsAllTheDebits() {
        val debit = Debit("Company", "Message", 1.0, "Business")
        val debit2 = Debit("Company2", "Message2", 2.0, "Business2")
        debitStorage.store(debit)
        debitStorage.store(debit2)
        assertEquals(listOf(debit, debit2), debitStorage.getAll())
    }
}