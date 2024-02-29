package com.codecamp.newsx.data.fcm

//import android.content.Intent
//import android.util.Log
//import com.google.firebase.messaging.FirebaseMessagingService
//import com.google.firebase.messaging.RemoteMessage

//class PushNotificationService : FirebaseMessagingService() {
//
//    override fun onMessageReceived(message: RemoteMessage) {
//        super.onMessageReceived(message)
//        if (message.data.isNotEmpty()) {
//            Log.d("GoogleIO", "message received: ${message.data.getValue("article_key")}")
//        }
//
//        message.notification?.let {
//
//        }
//    }
//
//    override fun onNewToken(token: String) {
//        super.onNewToken(token)
//        Log.d("GoogleIo", "token: $token")
//    }
//}