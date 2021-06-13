package com.example.notification


import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.NotificationCompat
import com.example.notification.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    var CHANNEL_ID = "1"
    var id=1
    lateinit var activityMainBinding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)



        activityMainBinding.button.setOnClickListener {
            var intent = Intent(this,MainActivity::class.java)
            val pandingIntent  = PendingIntent.getActivities(
                this,
                0,
                arrayOf(intent),
                PendingIntent.FLAG_UPDATE_CURRENT
            )

            val contentText = NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle("Bu titile")
                .setContentText("Bu content text")
                .setContentIntent(pandingIntent)
                .setAutoCancel(true)


            val notification = contentText.build()
            val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                val name = "Channel Name"
                val descriptionText = "Description Text"
                val importance = NotificationManager.IMPORTANCE_DEFAULT
                val mChannel = NotificationChannel(CHANNEL_ID.toString(), name, importance)
                mChannel.description = descriptionText
                notificationManager.createNotificationChannel(mChannel )
            }
            notificationManager.notify(id++,notification)
        }

        activityMainBinding.clouseBtn.setOnClickListener {
            val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.cancelAll()
        }
    }
}