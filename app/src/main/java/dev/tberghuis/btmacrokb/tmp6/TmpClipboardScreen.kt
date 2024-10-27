package dev.tberghuis.btmacrokb.tmp6

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import dev.tberghuis.btmacrokb.ui.theme.BtMacroKbTheme

@Composable
fun TmpClipboardScreen() {
  Column {
    Text("hello clipboard screen")
  }
}

@Preview
@Composable
fun TmpClipboardScreenPreview() {
  BtMacroKbTheme {
    TmpClipboardScreen()
  }
}