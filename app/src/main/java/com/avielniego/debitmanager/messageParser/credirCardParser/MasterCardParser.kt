package com.avielniego.debitmanager.messageParser.credirCardParser

import com.avielniego.debitmanager.messageParser.Debit
import com.avielniego.debitmanager.messageParser.Message
import com.avielniego.debitmanager.messageParser.MessageParser

class MasterCardParser(private val message: Message): MessageParser {

    val CREDIT_CARD_COMPANY_NAME = "Isracard"

    override fun parse(): Debit? {
        if (message.from != CREDIT_CARD_COMPANY_NAME)
            return null
        return createDebitFromMessage()
    }

    private fun createDebitFromMessage(): Debit {
        val r = Regex("בכרטיסך (.*?) אושרה עסקה ב-(.*?) בסך (.*?) ש\"ח ב(.*?). מידע").find(message.body) ?: throw MessageParser.CouldNotParseMessage()
        val businessName: String = r.groups[4]?.value ?: ""
        return Debit(CREDIT_CARD_COMPANY_NAME, message.body, getSumFromRegexResult(r), businessName)
    }

    private fun getSumFromRegexResult(r: MatchResult): Double {
        return try {
            r.groups[3]?.value?.toDouble() ?: throw MessageParser.CouldNotParseSumInMessage()
        } catch (e: NumberFormatException) {
            throw MessageParser.CouldNotParseSumInMessage()
        }
    }
}

