package com.avielniego.debitmanager.databaseAccess

import android.app.IntentService
import android.content.Context
import android.content.Intent
import com.avielniego.debitmanager.messageParser.Debit
import com.avielniego.debitmanager.messageParser.Message
import com.avielniego.debitmanager.widget.DebitWidgetProvider

class ManualAddDebitService : IntentService(ManualAddDebitService::class.java.simpleName) {

    companion object {
        val SUM_KEY = "MANUAL_ADD_SUM_KEY"
        val DESCRIPTION_KEY = "MANUAL_ADD_DESCRIPTION_KEY"

        fun startService(context: Context, sum: Double, description: String) {
            val intent = Intent(context, ManualAddDebitService::class.java)
            intent.putExtra(SUM_KEY, sum)
            intent.putExtra(DESCRIPTION_KEY, description)
            context.startService(intent)
        }
    }
    
    override fun onHandleIntent(intent: Intent?) {
        if(intent == null) return
        val manualMessage = Message("Manual", "Manual")
        val debit = Debit(manualMessage, intent.getDoubleExtra(SUM_KEY, 1.0), intent.getStringExtra(DESCRIPTION_KEY))
        DebitDbStorage(this).store(debit)
        DebitWidgetProvider.update(this)
    }
}