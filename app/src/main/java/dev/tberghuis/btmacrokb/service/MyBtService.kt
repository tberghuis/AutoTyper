package dev.tberghuis.btmacrokb.service

import android.annotation.SuppressLint
import android.app.Notification
import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.content.pm.ServiceInfo
import android.os.Binder
import android.os.Build
import android.os.IBinder
import androidx.core.app.NotificationChannelCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import dev.tberghuis.btmacrokb.MainActivity
import dev.tberghuis.btmacrokb.util.logd
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob

class MyBtService : Service() {
  private val job = SupervisorJob()
  val scope = CoroutineScope(Dispatchers.IO + job)

  lateinit var myBtController: BtController

  private val binder = LocalBinder()

  inner class LocalBinder : Binder(), ServiceBinder<MyBtService> {
    override fun getService(): MyBtService = this@MyBtService
  }

  init {
    logd("MyBtService init")
  }

  override fun onBind(intent: Intent?): IBinder {
    logd("MyBtService onbind")
    return binder
  }

  override fun onCreate() {
    super.onCreate()
    logd("MyBtService onCreate")
    startInForeground()
    myBtController = BtController(this)
  }

  override fun onDestroy() {
    logd("MyBtService onDestroy")
    myBtController.hidDeviceUnregisterApp()
    super.onDestroy()
  }

  override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
    // fix crash play console ForegroundServiceStartNotAllowedException
    // https://issuetracker.google.com/issues/307329994?pli=1
    return START_NOT_STICKY
  }

  @SuppressLint("ObsoleteSdkInt")
  private fun startInForeground() {
    createNotificationChannel()
    val notification = createNotification()
    // Q = 29
    if (Build.VERSION.SDK_INT >= 29) {
      startForeground(
        FOREGROUND_SERVICE_NOTIFICATION_ID,
        notification,
        ServiceInfo.FOREGROUND_SERVICE_TYPE_CONNECTED_DEVICE,
      )
    } else {
      startForeground(FOREGROUND_SERVICE_NOTIFICATION_ID, notification)
    }
  }

  private fun createNotification(): Notification {
    val intent = Intent(this, MainActivity::class.java).apply {
      // are these the correct flags?
      flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
    }
    val pendingIntent: PendingIntent =
      PendingIntent.getActivity(this, REQUEST_CODE_OPEN_APP, intent, PendingIntent.FLAG_IMMUTABLE)

    val notification = NotificationCompat.Builder(this, NOTIFICATION_CHANNEL)
      .setSmallIcon(applicationInfo.icon)
      .setContentTitle("Auto Typer")
      .setContentText("Foreground service is running...")
      .setContentIntent(pendingIntent)
      .build()
    return notification
  }

  private fun createNotificationChannel() {
    val channel = NotificationChannelCompat.Builder(
      NOTIFICATION_CHANNEL,
      NotificationManagerCompat.IMPORTANCE_HIGH,
    )
      .setName("bt connect channel")
      .build()
    NotificationManagerCompat.from(this).createNotificationChannel(channel)
  }

  companion object {
    private const val NOTIFICATION_CHANNEL = "bt_connect_channel"
    const val FOREGROUND_SERVICE_NOTIFICATION_ID = 100
    const val REQUEST_CODE_OPEN_APP = 1
  }
}
