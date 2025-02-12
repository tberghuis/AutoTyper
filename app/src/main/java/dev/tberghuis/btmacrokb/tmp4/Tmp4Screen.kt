package dev.tberghuis.btmacrokb.tmp4

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext


var controller: Tmp4BtController? = null

@Composable
fun Tmp4Screen() {
  val context = LocalContext.current
  controller = remember {
    Tmp4BtController(context.applicationContext)
  }

  Column {
    Text("hello tmp4")
    Button(onClick = {}) {
      Text("button")
    }
  }
}