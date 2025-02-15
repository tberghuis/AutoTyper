package dev.tberghuis.btmacrokb.tmp6

import androidx.activity.compose.LocalActivity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun DeepLinkScreen(
  vm: DeepLinkScreenVm = viewModel()
) {
  val activity = LocalActivity.current
  // hack to only run once even if configuration change
  rememberSaveable {
    activity?.intent?.data?.let {
      vm.processDataUri(it)
    }
    ""
  }

  Scaffold() { padding ->
    Column(
      modifier = Modifier.padding(padding).fillMaxSize(),
      verticalArrangement = Arrangement.Center,
      horizontalAlignment = Alignment.CenterHorizontally,
    ) {
      Column {
        Text("deeplink: ")
        Text("device: ")
        Text("payload: ")
        Text("result: ")
      }
    }
  }
}