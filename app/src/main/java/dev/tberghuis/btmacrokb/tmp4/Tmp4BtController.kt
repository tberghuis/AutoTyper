package dev.tberghuis.btmacrokb.tmp4

import android.annotation.SuppressLint
import android.app.Application
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothManager
import android.content.Context
import dev.tberghuis.btmacrokb.util.logd

class Tmp4BtController(
  application: Application
) {
  private val btAdapter: BluetoothAdapter by lazy {
    val bluetoothManager =
      application.getSystemService(Context.BLUETOOTH_SERVICE) as BluetoothManager
    bluetoothManager.adapter
  }

  var pairedDevices: List<BluetoothDevice>? = null
  var b450Device: BluetoothDevice? = null

  @SuppressLint("MissingPermission")
  fun getPairedDevices() {
    val bondedDevices = btAdapter.bondedDevices
    logd("getPairedDevices bondedDevices $bondedDevices")
    pairedDevices = btAdapter.bondedDevices?.toList() ?: listOf()
  }

  fun findB450(){
    b450Device = pairedDevices?.find {
      it.address.equals("28:7F:CF:BD:00:B9",true)
    }
    logd("findB450 $b450Device")
  }

}