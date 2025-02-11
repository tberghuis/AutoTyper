package dev.tberghuis.btmacrokb.tmp3

import android.app.Application
import android.bluetooth.BluetoothDevice
import androidx.lifecycle.AndroidViewModel
import dev.tberghuis.btmacrokb.screens.ConnectionVm
import dev.tberghuis.btmacrokb.util.logd

class TmpScreenVm(
  private val application: Application,
) : AndroidViewModel(application) {

  val connectionVm = ConnectionVm(application)

  var b450Device: BluetoothDevice? = null

  init {
    connectionVm.observeBtController()
  }

  fun findB450() {
    b450Device = connectionVm.pairedDevices.value.find { it.address == "28:7F:CF:BD:00:B9" }
    logd("b450Device $b450Device")
  }


  fun connectB450() {
    connectionVm.connect(b450Device!!)
  }

}