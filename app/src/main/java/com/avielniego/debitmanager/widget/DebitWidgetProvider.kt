package com.avielniego.debitmanager.widget

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.widget.RemoteViews
import com.avielniego.debitmanager.R
import com.avielniego.debitmanager.databaseAccess.DebitDbStorage
import com.avielniego.debitmanager.ui.AddDebitActivity
import java.text.NumberFormat


class DebitWidgetProvider : AppWidgetProvider() {

    companion object {
        val TAG: String = DebitWidgetProvider::class.java.simpleName

        fun update(context: Context) {
            val intent = Intent(context, DebitWidgetProvider::class.java)
            intent.action = AppWidgetManager.ACTION_APPWIDGET_UPDATE
            val ids = AppWidgetManager.getInstance(context.applicationContext)
                    .getAppWidgetIds(ComponentName(context, DebitWidgetProvider::class.java))
            intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, ids)
            context.sendBroadcast(intent)
        }
    }

    override fun onUpdate(context: Context?, appWidgetManager: AppWidgetManager?, appWidgetIds: IntArray?) {
        if (context == null || appWidgetManager == null || appWidgetIds == null) return
        appWidgetIds.forEach { updateWidget(appWidgetManager, context, it) }
    }

    private fun updateWidget(appWidgetManager: AppWidgetManager, context: Context, widgetId: Int) {
        setSumToWidget(appWidgetManager, context, widgetId)
        setWidgetClickListener(appWidgetManager, context, widgetId)
    }

    private fun setWidgetClickListener(appWidgetManager: AppWidgetManager, context: Context, widgetId: Int) {
        val pendingIntent = PendingIntent.getActivity(context, 0, Intent(context, AddDebitActivity::class.java), 0)
        val views = RemoteViews(context.packageName, R.layout.current_sum_widget)
        views.setOnClickPendingIntent(R.id.widget_frame_layout, pendingIntent)
        appWidgetManager.updateAppWidget(widgetId, views)
    }

    private fun setSumToWidget(appWidgetManager: AppWidgetManager, context: Context, widgetId: Int) {
        val r = RemoteViews(context.packageName, R.layout.current_sum_widget)
        val sum = DebitDbStorage(context).getAll().map { it.sum }.sum()
        r.setTextViewText(R.id.total_sum, formatSumString(context, sum))
        appWidgetManager.updateAppWidget(widgetId, r)
    }

    private fun formatSumString(context: Context, sum: Number): String {
        return context.getString(R.string.money_sign, NumberFormat.getInstance().format(sum))
    }
}