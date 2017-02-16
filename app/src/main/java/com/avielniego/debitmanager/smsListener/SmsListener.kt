package com.avielniego.debitmanager.smsListener

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.provider.Telephony
import android.telephony.SmsMessage
import com.avielniego.debitmanager.messageParser.Message
import com.avielniego.debitmanager.messageParser.MessageConsumer
import com.avielniego.debitmanager.messageParser.debitStorage.MemoryDebitStorage


class SmsListener : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action != Telephony.Sms.Intents.SMS_RECEIVED_ACTION)
            return
        parseMessages(Telephony.Sms.Intents.getMessagesFromIntent(intent))
    }

    private fun parseMessages(smsMessages: Array<SmsMessage>) {
        val consumer = MessageConsumer(MemoryDebitStorage())
        smsMessages.forEach { consumer.consume(Message(it.originatingAddress, it.messageBody)) }
    }
}
