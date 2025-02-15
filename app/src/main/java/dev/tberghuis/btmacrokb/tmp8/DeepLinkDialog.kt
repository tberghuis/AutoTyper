package dev.tberghuis.btmacrokb.tmp8

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.AlertDialogDefaults
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel


@Composable
fun Tmp6Screen(
  vm: Tmp8Vm = viewModel()
) {
  Column {
    Text("hello tmp 6")
    Button(onClick = { vm.deepLinkVmc.showDialog = true }) {
      Text("deep link")
    }
  }
  if (vm.deepLinkVmc.showDialog) {
    DeepLinkDialog(onDismissRequest = {
      vm.deepLinkVmc.showDialog = false
    })
  }
}

@Preview
@Composable
fun Tmp6ScreenPreview() {
  Tmp6Screen()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DeepLinkDialog(
  onDismissRequest: () -> Unit,
  vm: Tmp8Vm = viewModel()
) {
  val clipboardManager = LocalClipboardManager.current

  BasicAlertDialog(
    onDismissRequest = onDismissRequest,
    modifier = Modifier,
  ) {
    Surface() {

      Column(modifier = Modifier.padding(16.dp)) {
        Text("Deep Link")
        Text(
          text = "Click OK to copy link to clipboard.",
        )
        Row(
          modifier = Modifier.align(Alignment.End),
        ) {
          TextButton(
            onClick = { },
            modifier = Modifier
          ) {
            Text("Cancel")
          }
          TextButton(
            onClick = {
              vm.deepLinkVmc.deepLinkToClipboard(clipboardManager)
            },
            modifier = Modifier
          ) {
            Text("OK")
          }
        }
      }
    }
  }
}