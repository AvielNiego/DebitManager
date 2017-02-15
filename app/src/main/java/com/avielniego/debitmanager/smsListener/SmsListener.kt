package com.avielniego.debitmanager.smsListener

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.provider.Telephony
import android.telephony.SmsMessage
import android.widget.Toast
import com.avielniego.debitmanager.messageParser.Message
import com.avielniego.debitmanager.messageParser.credirCardParser.MasterCardParser


class SmsListener : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action != Telephony.Sms.Intents.SMS_RECEIVED_ACTION)
            return
        parseMessages(context, Telephony.Sms.Intents.getMessagesFromIntent(intent))
    }

    private fun parseMessages(context: Context, smsMessages: Array<SmsMessage>) {
        smsMessages
                .map { MasterCardParser(Message(it.originatingAddress, it.messageBody)).parse() }
                .forEach { Toast.makeText(context, it.toString(), Toast.LENGTH_LONG).show() }
    }
}
