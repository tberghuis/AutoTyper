package dev.tberghuis.btmacrokb.tmp6

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun Tmp6Screen(
    vm: Tmp6ScreenVm = viewModel()
) {
  Column {
    Text("hello tmp6")
  }
}