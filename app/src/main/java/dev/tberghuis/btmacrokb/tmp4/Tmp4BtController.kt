package dev.tberghuis.btmacrokb.tmp4

import android.annotation.SuppressLint
import android.app.Application
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothHidDevice
import android.bluetooth.BluetoothManager
import android.bluetooth.BluetoothProfile
import android.content.Context
import dev.tberghuis.btmacrokb.util.logd
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class Tmp4BtController(
  private val application: Application
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

  fun findB450() {
    b450Device = pairedDevices?.find {
      it.address.equals("28:7F:CF:BD:00:B9", true)
    }
    logd("findB450 $b450Device")
  }

  fun getProfileProxy(): StateFlow<BluetoothHidDevice?> {
    val hidDevice = MutableStateFlow<BluetoothHidDevice?>(null)
    val serviceListener = object : BluetoothProfile.ServiceListener {
      override fun onServiceConnected(profile: Int, proxy: BluetoothProfile?) {
        logd("GetProfileProxy onServiceConnected profile $profile")
        logd("GetProfileProxy onServiceConnected proxy $proxy")
        hidDevice.value = proxy as BluetoothHidDevice
      }
      override fun onServiceDisconnected(profile: Int) {
        logd("GetProfileProxy onServiceDisconnected profile $profile")
        hidDevice.value = null
      }
    }
    btAdapter.getProfileProxy(
      application,
      serviceListener,
      BluetoothProfile.HID_DEVICE
    )
    return hidDevice
  }

  fun registerApp() {

  }

  fun connectB450() {

  }
}