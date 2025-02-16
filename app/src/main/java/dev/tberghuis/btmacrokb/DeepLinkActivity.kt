package dev.tberghuis.btmacrokb

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import dev.tberghuis.btmacrokb.screens.DeepLinkScreen
import dev.tberghuis.btmacrokb.ui.theme.BtMacroKbTheme

class DeepLinkActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    setContent {
      BtMacroKbTheme {
        DeepLinkScreen()
      }
    }
  }
}