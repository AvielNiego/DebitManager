package com.avielniego.debitmanager.messageParser

import com.avielniego.debitmanager.messageParser.creditCardParser.MasterCardParserTest
import org.junit.Test
import org.mockito.Mockito

class MessageConsumerTest {

    @Test
    fun createNew() {
        val message = Message(MasterCardParserTest.ISRACARD_COMPANY_NAME, MasterCardParserTest.MESSAGE_TEXT)
        val debitStorage = Mockito.mock(DebitStorage::class.java)
        Mockito.`when`(debitStorage.store(Debit("Company", "Message", 1.0, "Business"))).then { it }
        MessageConsumer(message, debitStorage)
    }
}

