package com.cookandroid.invasion.fcm

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.provider.Settings
import android.util.Log
import androidx.core.app.NotificationCompat
import com.cookandroid.invasion.MainActivity
import com.cookandroid.invasion.R
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyFirebaseMessagingService : FirebaseMessagingService() {

    private val TAG = "FirebaseService"
    private lateinit var database: FirebaseDatabase
    private lateinit var databaseReference: DatabaseReference

    // 파이어베이스 서비스의 토큰을 가져온다
    override fun onNewToken(token: String?) {
        Log.d(TAG, "new Token: $token")

        //파베 연결해서 토큰 날려줘야함. 단 한번만 날린다.
        database = FirebaseDatabase.getInstance()
        databaseReference = database.getReference("Token")
        val idByANDROID_ID: String = Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID)
        databaseReference.child(idByANDROID_ID).setValue(token);
    }
    
    // 새로운 FCM 메시지가 있을 때 메세지를 받는다
    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        Log.d(TAG, "From: " + remoteMessage.from)
        // 앱이 포어그라운드 상태에서 Notificiation을 받는 경우
        if(remoteMessage.notification != null) {
            Log.d(TAG, "Notification Message Body: ${remoteMessage.notification?.body}")
            sendNotification(remoteMessage.notification?.body)
        }
        else {
            sendNotification(remoteMessage.notification?.body)
        }
    }

    // FCM 메시지를 보내는 메시지
    private fun sendNotification(body: String?) {
        val intent = Intent(this, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            putExtra("Notification", body)
        }

        var pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT)
        val notificationSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)

        var notificationBuilder = NotificationCompat.Builder(this,"Notification")
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentTitle("Push Notification FCM")
            .setContentText(body)
            .setAutoCancel(true)
            .setSound(notificationSound)
            .setContentIntent(pendingIntent)

        var notificationManager: NotificationManager = this.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(0, notificationBuilder.build())
    }

}

