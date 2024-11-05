package dev.tberghuis.btmacrokb.tmp7

import android.content.Context
import android.content.Intent
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import dev.tberghuis.btmacrokb.service.MyBtService
import dev.tberghuis.btmacrokb.service.MyBtService.Companion.ACTION_APP_EXIT
import dev.tberghuis.btmacrokb.ui.theme.BtMacroKbTheme
import dev.tberghuis.btmacrokb.util.logd

@Composable
fun TmpAppClose() {
  val context = LocalContext.current
  Column {
    Text("hello app close screen")
    Button(onClick = {
      appClose(context)
    }) {
      Text("app close")
    }
  }
}

@Preview
@Composable
fun TmpClipboardScreenPreview() {
  BtMacroKbTheme {
    TmpAppClose()
  }
}

fun appClose(context: Context) {
  logd("appClose")

  val intent = Intent(context, MyBtService::class.java).apply {
    action = ACTION_APP_EXIT
  }
  context.startService(intent)

}
