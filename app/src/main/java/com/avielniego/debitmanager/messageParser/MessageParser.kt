package com.avielniego.debitmanager.messageParser

interface MessageParser {
    fun parse(): Debit?

    class CouldNotParseMessage: RuntimeException()
    class CouldNotParseSumInMessage: RuntimeException()
}