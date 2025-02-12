package dev.tberghuis.btmacrokb.tmp6

import android.annotation.SuppressLint
import android.app.Application
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothHidDevice
import android.bluetooth.BluetoothHidDeviceAppSdpSettings
import android.bluetooth.BluetoothManager
import android.bluetooth.BluetoothProfile
import android.content.Context
import dev.tberghuis.btmacrokb.KEYBOARD_ID
import dev.tberghuis.btmacrokb.asciiCharToReportByteArray
import dev.tberghuis.btmacrokb.kbDescriptor
import dev.tberghuis.btmacrokb.util.logd
import kotlin.coroutines.coroutineContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class SingleUseBtController2(
  private val application: Application
) {
  private val btAdapter = initAdapter()
  private val hidDevice = getProfileProxy()
  private val connected = MutableStateFlow(false)
  private val isRegisteredForHid = MutableStateFlow(false)
  private val hidDeviceCallback = initHidDeviceCallback()

  @SuppressLint("MissingPermission")
  private fun getDevice(address: String): BluetoothDevice? {
    return btAdapter.bondedDevices?.find {
      it.address.equals(address, true)
    }
  }

  // todo testing with missing permission
  @SuppressLint("MissingPermission")
  private suspend fun registerApp() {
    logd("registerApp")
    val sdpRecord = BluetoothHidDeviceAppSdpSettings(
      "Bluetooth HID Keyboard",
      "Bluetooth HID Keyboard",
      "Fixed Point",
      BluetoothHidDevice.SUBCLASS1_COMBO,
      kbDescriptor
    )
    CoroutineScope(coroutineContext).launch {
      hidDevice.filterNotNull().first()
        .registerApp(sdpRecord, null, null, Runnable::run, hidDeviceCallback)
    }
  }

  @SuppressLint("MissingPermission")
  private suspend fun connect(device: BluetoothDevice): BluetoothHidDevice {
    registerApp()
    CoroutineScope(coroutineContext).launch {
      isRegisteredForHid.filter { it }.first()
      val hid = hidDevice.filterNotNull().first()
      hid.connect(device)
    }
    connected.filter { it }.first()
    delay(2000)
    return hidDevice.filterNotNull().first()
  }

  @SuppressLint("MissingPermission")
  suspend fun sendPayload(address: String, payload: String) {
    val device = getDevice(address)
    val hid = connect(device!!)
    payload.toCharArray().forEach { char ->
      hid.sendReport(
        device,
        KEYBOARD_ID,
        asciiCharToReportByteArray[char] ?: return@forEach
      )
      delay(5)
      hid.sendReport(device, KEYBOARD_ID, ByteArray(8) { 0 })
      delay(20)
    }

    // todo disconnect
    // unregister app

  }

  ////////// initializer functions

  private fun initAdapter(): BluetoothAdapter {
    val bluetoothManager =
      application.getSystemService(Context.BLUETOOTH_SERVICE) as BluetoothManager
    return bluetoothManager.adapter
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

  private fun initHidDeviceCallback() = object : BluetoothHidDevice.Callback() {
    @SuppressLint("MissingPermission")
    override fun onConnectionStateChanged(device: BluetoothDevice, state: Int) {
      super.onConnectionStateChanged(device, state)
      logd("hidDeviceCallback onConnectionStateChanged device=${device.name} state=$state")
      when (state) {
        BluetoothProfile.STATE_CONNECTED -> {
          connected.value = true
        }

        else -> {
          connected.value = false
        }
      }
    }

    override fun onAppStatusChanged(pluggedDevice: BluetoothDevice?, registered: Boolean) {
      super.onAppStatusChanged(pluggedDevice, registered)
      isRegisteredForHid.value = registered
    }
  }
}
