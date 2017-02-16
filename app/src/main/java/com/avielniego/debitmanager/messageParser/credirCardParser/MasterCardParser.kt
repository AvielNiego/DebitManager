package com.avielniego.debitmanager.messageParser.credirCardParser

import com.avielniego.debitmanager.messageParser.Debit
import com.avielniego.debitmanager.messageParser.Message
import com.avielniego.debitmanager.messageParser.MessageParser

class MasterCardParser : MessageParser {

    val CREDIT_CARD_COMPANY_NAME = "Isracard"

    override fun parse(message: Message): Debit? {
        if (message.from != CREDIT_CARD_COMPANY_NAME)
            return null
        return createDebitFromMessage(message)
    }

    private fun createDebitFromMessage(message: Message): Debit {
        val r = matchResult(message) ?: throw MessageParser.CouldNotParseMessage()
        val businessName: String = r.groups[4]?.value ?: ""
        return Debit(message, getSumFromRegexResult(r), businessName)
    }

    private fun matchResult(message: Message) = Regex("בכרטיסך (.*?) אושרה עסקה ב-(.*?) בסך (.*?) ש\"ח(?: )?(?:ב)?(.*?). מידע").find(message.body)

    private fun getSumFromRegexResult(r: MatchResult): Double {
        try {
            return r.groups[3]?.value?.toDouble() ?: throw MessageParser.CouldNotParseSumInMessage()
        } catch (e: NumberFormatException) {
            throw MessageParser.CouldNotParseSumInMessage()
        }
    }
}

