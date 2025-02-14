package dev.tberghuis.btmacrokb.tmp6

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun Tmp6Screen(
    vm: Tmp6ScreenVm = viewModel()
) {

  // hack to only run once even if configuration change
  rememberSaveable {
    vm.runOnce()
    ""
  }



  Column {
    Text("hello tmp6")
  }
}