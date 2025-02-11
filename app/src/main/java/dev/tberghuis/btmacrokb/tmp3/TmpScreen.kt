package dev.tberghuis.btmacrokb.tmp3

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview


@Composable
fun TmpScreen() {
  Column {
    Button(onClick = {}) {
      Text("connect b450")
    }
  }
}

@Preview
@Composable
fun TmpScreenPreview() {
  TmpScreen()
}