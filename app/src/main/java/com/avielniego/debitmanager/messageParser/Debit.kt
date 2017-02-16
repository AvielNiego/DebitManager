package com.avielniego.debitmanager.messageParser


data class Debit(val message: Message,
                 val sum: Double,
                 val businessName: String)