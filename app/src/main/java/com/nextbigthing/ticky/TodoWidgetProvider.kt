package com.nextbigthing.ticky

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.widget.RemoteViews
import androidx.room.Room
import com.nextbigthing.ticky.room.AppDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TodoWidgetProvider : AppWidgetProvider() {

    override fun onUpdate(context: Context, appWidgetManager: AppWidgetManager, appWidgetIds: IntArray) {
        CoroutineScope(Dispatchers.IO).launch {
            val db = Room.databaseBuilder(
                context,
                AppDatabase::class.java, "database-name"
            ).build()
            val tasks = db.appDao().getAll()
            val checked = tasks.count { it.isChecked == true }
            val total = tasks.size
            val progress = if (total > 0) (checked * 100) / total else 0

            appWidgetIds.forEach { widgetId ->
                val views = RemoteViews(context.packageName, R.layout.widget_todo)
                views.setTextViewText(R.id.progressText, "Completed: $progress%")
                views.setProgressBar(R.id.progressBar, 100, progress, false)

                appWidgetManager.updateAppWidget(widgetId, views)
            }
        }
    }
}