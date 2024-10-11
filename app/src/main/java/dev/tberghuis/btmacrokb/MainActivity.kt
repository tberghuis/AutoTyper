package dev.tberghuis.btmacrokb

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import dev.tberghuis.btmacrokb.nav.MyApp
import dev.tberghuis.btmacrokb.service.StopServiceVm
import dev.tberghuis.btmacrokb.ui.theme.BtMacroKbTheme

class MainActivity : ComponentActivity() {
  // oncleared will stop service
  private val stopServiceViewModel: StopServiceVm by viewModels()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    // viewModels() is lazy, so this will create it
    stopServiceViewModel
    enableEdgeToEdge()
    setContent {
      BtMacroKbTheme {
        MyApp()
//        Tmp5Screen()
      }
    }
  }
}