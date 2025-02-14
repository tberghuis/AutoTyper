package dev.tberghuis.btmacrokb.tmp4

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import dev.tberghuis.btmacrokb.tmp3.TmpScreen
import dev.tberghuis.btmacrokb.tmp6.Tmp6Screen
import dev.tberghuis.btmacrokb.ui.theme.BtMacroKbTheme

class TmpActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    setContent {
      BtMacroKbTheme {
        Tmp6Screen()
      }
    }
  }
}