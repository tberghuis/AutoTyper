package dev.tberghuis.btmacrokb.tmp5

import android.app.Application
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

class Tmp5ScreenVm(
  private val application: Application,
) : AndroidViewModel(application) {
  val controller = Tmp5BtController(application)
}

@Composable
fun Tmp5Screen(
  vm: Tmp5ScreenVm = viewModel()
) {
  Column(Modifier.safeContentPadding()) {
    Text("wear mouse hid settings")
    Button(onClick = {
      vm.controller.getAdapter()
    }) {
      Text("get adapter")
    }
    Button(onClick = {
//      vm.controller.getDevice("b550-mint")
      vm.controller.getDevice("Galaxy A20")
//      vm.controller.getDevice("hotspot TV 2")
    }) {
      Text("getDevice")
    }
    Button(onClick = {
      vm.controller.getProfileProxy()
    }) {
      Text("getProfileProxy")
    }
    Button(onClick = {
      vm.controller.registerApp()
    }) {
      Text("registerApp")
    }
    Button(onClick = {
      vm.controller.connect()
    }) {
      Text("connect")
    }
    Button(onClick = {
      vm.controller.sendHelloWorld()
    }) {
      Text("sendHelloWorld")
    }
  }
}