package com.avielniego.debitmanager.widget

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.widget.RemoteViews
import com.avielniego.debitmanager.R
import java.text.NumberFormat

class DebitWidgetProvider : AppWidgetProvider() {
    override fun onUpdate(context: Context?, appWidgetManager: AppWidgetManager?, appWidgetIds: IntArray?) {
        if (context == null || appWidgetManager == null || appWidgetIds == null) return
        appWidgetIds.forEach { setSumToWidget(appWidgetManager, context, it) }
    }

    private fun setSumToWidget(appWidgetManager: AppWidgetManager, context: Context, widgetId: Int) {
        val r = RemoteViews(context.packageName, R.layout.widget)
        r.setTextViewText(R.id.total_sum, formatSumString(context, 5000))
        appWidgetManager.updateAppWidget(widgetId, r)
    }

    private fun formatSumString(context: Context, sum: Number): String {
        return context.getString(R.string.money_sign, NumberFormat.getInstance().format(sum))
    }
}