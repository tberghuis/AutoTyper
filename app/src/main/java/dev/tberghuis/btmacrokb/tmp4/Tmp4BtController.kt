package dev.tberghuis.btmacrokb.tmp4

import android.annotation.SuppressLint
import android.app.Application
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothHidDevice
import android.bluetooth.BluetoothHidDeviceAppSdpSettings
import android.bluetooth.BluetoothManager
import android.bluetooth.BluetoothProfile
import android.content.Context
import dev.tberghuis.btmacrokb.kbDescriptor
import dev.tberghuis.btmacrokb.util.logd
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class Tmp4BtController(
  private val application: Application
) {
  private val job = SupervisorJob()
  private val scope = CoroutineScope(Dispatchers.IO + job)

  private val btAdapter: BluetoothAdapter by lazy {
    val bluetoothManager =
      application.getSystemService(Context.BLUETOOTH_SERVICE) as BluetoothManager
    bluetoothManager.adapter
  }
  private val hidDevice: StateFlow<BluetoothHidDevice?>
  private val connectedDevice = MutableStateFlow<BluetoothDevice?>(null)
  private val isRegisteredForHid = MutableStateFlow(false)

  private val hidDeviceCallback = object : BluetoothHidDevice.Callback() {
    @SuppressLint("MissingPermission")
    override fun onConnectionStateChanged(device: BluetoothDevice, state: Int) {
      super.onConnectionStateChanged(device, state)
      logd("hidDeviceCallback onConnectionStateChanged device=${device.name} state=$state")
      when (state) {
        BluetoothProfile.STATE_CONNECTED -> {
          connectedDevice.update {
            device
          }
        }

        else -> {
          connectedDevice.update {
            null
          }
        }
      }
    }

    override fun onAppStatusChanged(pluggedDevice: BluetoothDevice?, registered: Boolean) {
      super.onAppStatusChanged(pluggedDevice, registered)
      isRegisteredForHid.value = registered
    }
  }

  init {
    hidDevice = getProfileProxy()
    registerApp()
  }

  private fun getProfileProxy(): StateFlow<BluetoothHidDevice?> {
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

  // todo testing with missing permission
  @SuppressLint("MissingPermission")
  private fun registerApp() {
    logd("registerApp")
    val sdpRecord = BluetoothHidDeviceAppSdpSettings(
      "Bluetooth HID Keyboard",
      "Bluetooth HID Keyboard",
      "Fixed Point",
      BluetoothHidDevice.SUBCLASS1_COMBO,
      kbDescriptor
    )
    scope.launch {
      hidDevice.filterNotNull().first()
        .registerApp(sdpRecord, null, null, Runnable::run, hidDeviceCallback)
    }
  }

  @SuppressLint("MissingPermission")
  fun connectB450() {
    val b450 = findB450(btAdapter)
    scope.launch {
      logd("connect")
      isRegisteredForHid.filter { it }.first()
      logd("connect isRegisteredForHid")
      hidDevice.filterNotNull().first().connect(b450)
    }
  }

}

@SuppressLint("MissingPermission")
fun findB450(btAdapter: BluetoothAdapter): BluetoothDevice? {
  return btAdapter.bondedDevices?.find {
    it.address.equals("28:7F:CF:BD:00:B9", true)
  }
}