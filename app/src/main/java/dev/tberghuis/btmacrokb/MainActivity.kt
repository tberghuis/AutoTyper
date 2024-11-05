package dev.tberghuis.btmacrokb

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import dev.tberghuis.btmacrokb.nav.MyApp
import dev.tberghuis.btmacrokb.service.MyBtService
import dev.tberghuis.btmacrokb.service.MyBtService.Companion.ACTION_ACTIVITY_DESTROY
import dev.tberghuis.btmacrokb.ui.theme.BtMacroKbTheme
import dev.tberghuis.btmacrokb.util.logd

class MainActivity : ComponentActivity() {
  // todo foreground service persistent notification, 'stop' action
  var hasBluetoothConnectionPermissions = false

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    setContent {
      BtMacroKbTheme {
        MyApp()
      }
    }
  }

  override fun onDestroy() {
    logd("MainActivity onDestroy")
    // app will crash if user manually revokes permissions whilst running
    if (hasBluetoothConnectionPermissions) {
      val intent = Intent(application, MyBtService::class.java).apply {
        action = ACTION_ACTIVITY_DESTROY
      }
      application.startService(intent)
    }
    super.onDestroy()
  }
}