package dev.tberghuis.btmacrokb

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.viewmodel.compose.viewModel
import dev.tberghuis.btmacrokb.nav.MyApp
import dev.tberghuis.btmacrokb.service.StopServiceVm
import dev.tberghuis.btmacrokb.ui.theme.BtMacroKbTheme

class MainActivity : ComponentActivity() {
  // todo foreground service persistent notification, 'stop' action

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    setContent {
      // stop service in vm oncleared if not connected bt
      val vm: StopServiceVm = viewModel()
      BtMacroKbTheme {
        MyApp()
      }
    }
  }
}