package com.avielniego.debitmanager.smsListener

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.provider.Telephony
import android.telephony.SmsMessage
import com.avielniego.debitmanager.databaseAccess.DebitDbStorage
import com.avielniego.debitmanager.messageParser.Message
import com.avielniego.debitmanager.messageParser.MessageConsumer
import com.avielniego.debitmanager.widget.DebitWidgetProvider


class SmsListener : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action != Telephony.Sms.Intents.SMS_RECEIVED_ACTION)
            return
        parseMessages(context, Telephony.Sms.Intents.getMessagesFromIntent(intent))
    }

    private fun parseMessages(context: Context, smsMessages: Array<SmsMessage>) {
        val consumer = MessageConsumer(DebitDbStorage(context))
        smsMessages.map { Message(it.originatingAddress, it.messageBody) }
                .forEach { consumer.consume(it) }
        DebitWidgetProvider.update(context)
    }
}
