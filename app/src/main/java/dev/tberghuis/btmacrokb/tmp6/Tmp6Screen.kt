package dev.tberghuis.btmacrokb.tmp6

import androidx.activity.compose.LocalActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun DeepLinkScreen(
  vm: Tmp6ScreenVm = viewModel()
) {
  val activity = LocalActivity.current

  // hack to only run once even if configuration change
  rememberSaveable {
    activity?.intent?.data?.let {
      vm.processDataUri(it)
    }
    ""
  }

  Column {
    Text("hello tmp6")
  }
}