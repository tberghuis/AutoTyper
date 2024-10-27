package dev.tberghuis.btmacrokb.tmp6

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import dev.tberghuis.btmacrokb.ui.theme.BtMacroKbTheme

@Composable
fun TmpClipboardScreen(
  vm: TmpClipboardVm = viewModel()
) {
  Column {
    Text("hello clipboard screen")
    Text(vm.willitblend)
  }
}

@Preview
@Composable
fun TmpClipboardScreenPreview() {
  BtMacroKbTheme {
    TmpClipboardScreen()
  }
}