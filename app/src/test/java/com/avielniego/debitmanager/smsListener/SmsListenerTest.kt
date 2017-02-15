package com.avielniego.debitmanager.smsListener

import android.content.Intent
import android.provider.Telephony
import android.test.mock.MockContext
import org.junit.Before
import org.junit.Test

class SmsListenerTest {

    var smsListener: SmsListener = SmsListener()

    @Before
    fun setUp() {
        smsListener = SmsListener()
    }

    @Test
    fun onReceive() {
        val context = MockContext()
        val intent = Intent().setAction(Telephony.Sms.Intents.SMS_RECEIVED_ACTION)
        smsListener.onReceive(context, intent)
    }

}