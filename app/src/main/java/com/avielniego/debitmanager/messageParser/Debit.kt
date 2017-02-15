package com.avielniego.debitmanager.messageParser


data class Debit(val creditCardCompany: String,
                 val message: String,
                 val sum: Double,
                 val businessName: String)