package com.avielniego.debitmanager.messageParser

import com.avielniego.debitmanager.messageParser.credirCardParser.MasterCardParser
import com.avielniego.debitmanager.messageParser.creditCardParser.MasterCardParserTest
import com.avielniego.debitmanager.messageParser.debitStorage.MemoryDebitStorage
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class MessageConsumerTest {

    companion object {
        var validMessage = Message(MasterCardParserTest.ISRACARD_COMPANY_NAME, MasterCardParserTest.MESSAGE_TEXT)
        var validMessage2 = Message(MasterCardParserTest.ISRACARD_COMPANY_NAME, MasterCardParserTest.MESSAGE_TEXT_NO_BUSINESS_NAME)
        var invalidMessage = Message("DUMMY_COMPANY_NAME", MasterCardParserTest.MESSAGE_TEXT)
    }

    var debitStorage = MemoryDebitStorage()

    @Before
    fun setUp() {
        debitStorage = MemoryDebitStorage()
    }

    @Test
    fun consume_consumeAValidMasterCardMessage_ShouldAddTheMessageToTheStorage() {
        MessageConsumer(debitStorage).consume(validMessage)
        assertEquals(MasterCardParser().parse(validMessage), debitStorage.getAll().first())
    }

    @Test
    fun consume_invalidCreditCardCompany_ShouldNotBePresentInStorage() {
        MessageConsumer(debitStorage).consume(invalidMessage)
        assertTrue(debitStorage.getAll().isEmpty())
    }

    @Test
    fun consume_twoValidMessages_storageShouldHoldThemBoth() {
        val consumer = MessageConsumer(debitStorage)
        consumer.consume(validMessage)
        consumer.consume(validMessage2)
        assertEquals(parseMessagesWithMasterCardParser(validMessage, validMessage2),debitStorage.getAll())
    }

    private fun parseMessagesWithMasterCardParser(vararg messages: Message): List<Debit?> {
        val parser = MasterCardParser()
        return messages.map { parser.parse(it) }
    }
}

