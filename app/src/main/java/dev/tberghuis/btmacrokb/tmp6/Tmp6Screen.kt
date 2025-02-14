package dev.tberghuis.btmacrokb.tmp6

import androidx.activity.compose.LocalActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.lifecycle.viewmodel.compose.viewModel
import dev.tberghuis.btmacrokb.util.logd

@Composable
fun Tmp6Screen(
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


    Button(onClick = {
      logd("encrypt hello")
      val encrypted = SimpleAES.encrypt("hello\n", "1234")
      logd("encrypted $encrypted")
      val decrypted = SimpleAES.decrypt(encrypted, "1234")
      logd("decrypted $decrypted")
    }) {
      Text("encrypt hello")
    }

  }
}