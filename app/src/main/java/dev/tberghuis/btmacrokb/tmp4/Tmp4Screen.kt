package dev.tberghuis.btmacrokb.tmp4

import android.app.Application
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import dev.tberghuis.btmacrokb.tmp6.SingleUseBtController2
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

var controller: SingleUseBtController2? = null

@Composable
fun Tmp4Screen() {
  val context = LocalContext.current
  controller = remember {
    SingleUseBtController2(context.applicationContext as Application)
  }

  val scope = rememberCoroutineScope()

  Column {
    Text("hello tmp4")
    Button(onClick = {
      scope.launch(IO) {
        controller?.sendPayload("28:7F:CF:BD:00:B9", "hello\n")
      }
    }) {
      Text("send hello")
    }
  }
}