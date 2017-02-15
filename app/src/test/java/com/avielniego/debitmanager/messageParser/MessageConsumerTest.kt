package com.avielniego.debitmanager.messageParser

import com.avielniego.debitmanager.messageParser.creditCardParser.MasterCardParserTest
import org.junit.Test

class MessageConsumerTest {
    @Test
    fun createNew() {
        val message = Message(MasterCardParserTest.ISRACARD_COMPANY_NAME, MasterCardParserTest.MESSAGE_TEXT)

    }
}

