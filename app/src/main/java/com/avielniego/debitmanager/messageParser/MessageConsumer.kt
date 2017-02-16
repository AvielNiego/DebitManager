package com.avielniego.debitmanager.messageParser

import com.avielniego.debitmanager.messageParser.credirCardParser.MasterCardParser

class MessageConsumer(private val debitStorage: DebitStorage) {

    private val parsers: List<MessageParser> = listOf(MasterCardParser())

    fun consume(message: Message) {
        val debit = getDebitFromMessage(message)
        if(debit != null)
            debitStorage.store(debit)
    }

    private fun getDebitFromMessage(message: Message): Debit? {
        return parsers.map { it.parse(message) }
                .filterNotNull()
                .firstOrNull()
    }

}