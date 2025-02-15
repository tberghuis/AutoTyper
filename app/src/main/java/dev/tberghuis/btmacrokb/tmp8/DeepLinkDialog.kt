package dev.tberghuis.btmacrokb.tmp8

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
          verticalAlignment = Alignment.CenterVertically,
        ) {
          Text(
            "Encrypted"
          )
          Checkbox(
            checked = vm.deepLinkVmc.encryptedChecked,
            onCheckedChange = { vm.deepLinkVmc.encryptedChecked = it }
          )
        }


        PairedDeviceDropdown()


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
              vm.deepLinkVmc.deepLinkToClipboard(
                clipboardManager = clipboardManager,
                address = "28:7F:CF:BD:00:B9",
                payload = "hello\n",
                encrypted = vm.deepLinkVmc.encryptedChecked
              )
              onDismissRequest()
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PairedDeviceDropdown(
  vm: Tmp8Vm = viewModel()
) {
  var expanded by remember { mutableStateOf(false) }

  ExposedDropdownMenuBox(
    expanded = expanded,
    onExpandedChange = { expanded = !expanded },
    modifier = Modifier,
  ) {
    TextField(
      value = deviceDisplayText(vm.deepLinkVmc.selectedDeviceIndex),
      onValueChange = {},
      readOnly = true,
    )
  }
}

fun deviceDisplayText(index: Int?): String {
  return if (index == null) {
    "choose device"
  } else {
    "index $index"
  }
}