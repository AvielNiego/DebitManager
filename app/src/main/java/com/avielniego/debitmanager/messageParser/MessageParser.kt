package com.avielniego.debitmanager.messageParser

interface MessageParser {
    fun parse(message: Message): Debit?

    class CouldNotParseMessage: RuntimeException()
    class CouldNotParseSumInMessage: RuntimeException()
}