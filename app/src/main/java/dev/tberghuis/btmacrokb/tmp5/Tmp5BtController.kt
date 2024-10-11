package dev.tberghuis.btmacrokb.tmp5

import android.annotation.SuppressLint
import android.app.Application
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothHidDevice
import android.bluetooth.BluetoothHidDeviceAppSdpSettings
import android.bluetooth.BluetoothManager
import android.bluetooth.BluetoothProfile
import android.content.Context
import dev.tberghuis.btmacrokb.asciiCharToReportByteArray
import dev.tberghuis.btmacrokb.kbDescriptor
import dev.tberghuis.btmacrokb.logd

class Tmp5BtController(val application: Application) {
  var btAdapter: BluetoothAdapter? = null
  var device: BluetoothDevice? = null
  var hidDevice: BluetoothHidDevice? = null

  val hidDeviceCallback = object : BluetoothHidDevice.Callback() {
    @SuppressLint("MissingPermission")
    override fun onConnectionStateChanged(device: BluetoothDevice, state: Int) {
      super.onConnectionStateChanged(device, state)
      logd("hidDeviceCallback onConnectionStateChanged device=${device.name} state=$state")
    }

    override fun onAppStatusChanged(pluggedDevice: BluetoothDevice?, registered: Boolean) {
      super.onAppStatusChanged(pluggedDevice, registered)
      logd("onAppStatusChanged pluggedDevice $pluggedDevice registered $registered")
    }
  }

  fun getAdapter() {
    logd("getAdapter")
    val bluetoothManager =
      application.getSystemService(Context.BLUETOOTH_SERVICE) as BluetoothManager
    btAdapter = bluetoothManager.adapter
    logd("getBtAdapter $btAdapter")
  }

  @SuppressLint("MissingPermission")
  fun getDevice(name: String) {
    logd("getPairedDevices")
    val bondedDevices = btAdapter?.bondedDevices
    logd("getPairedDevices bondedDevices $bondedDevices")

    bondedDevices?.toList()?.forEach {
//      logd("getDevice name ${it.name} address ${it.address}")
      if (it.name == name) {
        device = it
        logd("getDevice name ${it.name} address ${it.address}")
      }
    }
  }

  fun getProfileProxy() {
    logd("GetProfileProxy")
    val serviceListener = object : BluetoothProfile.ServiceListener {
      override fun onServiceConnected(profile: Int, proxy: BluetoothProfile?) {
        logd("GetProfileProxy onServiceConnected profile $profile")
        logd("GetProfileProxy onServiceConnected proxy $proxy")
        hidDevice = proxy as BluetoothHidDevice
      }

      override fun onServiceDisconnected(profile: Int) {
        logd("GetProfileProxy onServiceDisconnected profile $profile")
        hidDevice = null
      }
    }

    btAdapter?.getProfileProxy(
      application,
      serviceListener,
      BluetoothProfile.HID_DEVICE
    )
  }

  @SuppressLint("MissingPermission")
  fun registerApp() {
    logd("registerApp")
    val sdpRecord = BluetoothHidDeviceAppSdpSettings(
      "Bluetooth HID Keyboard",
      "Bluetooth HID Keyboard",
      "Fixed Point",
      BluetoothHidDevice.SUBCLASS1_COMBO,
      kbDescriptor // ID = 8
    )
    hidDevice?.registerApp(sdpRecord, null, null, Runnable::run, hidDeviceCallback)
  }

  @SuppressLint("MissingPermission")
  fun connect() {
    logd("connect")
    hidDevice?.connect(device)
  }

  @SuppressLint("MissingPermission")
  fun sendHelloWorld() {
    logd("sendHelloWorld")
  }
}