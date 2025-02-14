package dev.tberghuis.btmacrokb.tmp7

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun SettingsScreen() {
  Scaffold() { padding ->
    Column(modifier = Modifier.padding(padding)) {
      Text("hello settings")
    }
  }
}