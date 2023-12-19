package com.app.mntassignment.util

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import com.app.mntassignment.R

object NotificationHelper {

    fun showNotification(
        context: Context,
        title: String,
        message: String,
        reqCode: Int = 0
    ) {
        createNotificationChannel(context, "notification_channel")

        val notificationBuilder: NotificationCompat.Builder =
            NotificationCompat.Builder(context, "notification_channel")
                .setContentTitle(title)
                .setContentText(message)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setAutoCancel(true)

        val notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(reqCode, notificationBuilder.build())
    }

    private fun createNotificationChannel(context: Context, title: String) {
        val notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val mChannel =
                NotificationChannel(title, title, NotificationManager.IMPORTANCE_HIGH)
            notificationManager.createNotificationChannel(mChannel)
        }
    }

}