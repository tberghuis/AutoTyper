package dev.tberghuis.btmacrokb.tmp3

import android.app.Application
import android.bluetooth.BluetoothDevice
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import dev.tberghuis.btmacrokb.screens.ConnectionVm
import dev.tberghuis.btmacrokb.service.provideMyBtService
import dev.tberghuis.btmacrokb.usecase.sendPayload
import dev.tberghuis.btmacrokb.util.logd
import kotlinx.coroutines.launch

class TmpScreenVm(
  private val application: Application,
) : AndroidViewModel(application) {

  val connectionVm = ConnectionVm(application)

  var b450Device: BluetoothDevice? = null

  fun initController() {
    connectionVm.observeBtController()
  }

  fun findB450() {
    b450Device = connectionVm.pairedDevices.value.find { it.address == "28:7F:CF:BD:00:B9" }
    logd("b450Device $b450Device")
  }

  fun connectB450() {
    connectionVm.connect(b450Device!!)
  }

  fun typeHello() {
    viewModelScope.launch {
      application.provideMyBtService().myBtController.sendString("hello\n")
    }
  }
}