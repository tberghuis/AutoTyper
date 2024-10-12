package dev.tberghuis.btmacrokb.service

import android.annotation.SuppressLint
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothHidDevice
import android.bluetooth.BluetoothHidDeviceAppSdpSettings
import android.bluetooth.BluetoothManager
import android.bluetooth.BluetoothProfile
import android.content.Context
import android.util.Log
import dev.tberghuis.btmacrokb.KEYBOARD_ID
import dev.tberghuis.btmacrokb.asciiCharToReportByteArray
import dev.tberghuis.btmacrokb.kbDescriptor
import dev.tberghuis.btmacrokb.logd
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class BtController(service: MyBtService) {
  private val scope = service.scope
  private val application = service.application

  private var btAdapter: BluetoothAdapter? = null
  private val hidDevice = MutableStateFlow<BluetoothHidDevice?>(null)
  private val isRegisteredForHid = MutableStateFlow(false)
  val connectedDevices = MutableStateFlow<Set<BluetoothDevice>>(setOf())

  private val hidDeviceCallback = object : BluetoothHidDevice.Callback() {
    @SuppressLint("MissingPermission")
    override fun onConnectionStateChanged(device: BluetoothDevice, state: Int) {
      super.onConnectionStateChanged(device, state)
      logd("hidDeviceCallback onConnectionStateChanged device=${device.name} state=$state")
      when (state) {
        BluetoothProfile.STATE_CONNECTED -> {
          connectedDevices.update {
            it + device
          }
        }

        else -> {
          connectedDevices.update {
            it - device
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
    getAdapter()
    getProfileProxy()
    registerApp()
  }

  @SuppressLint("MissingPermission")
  fun connect(device: BluetoothDevice) {
    scope.launch {
      logd("connect")
//      disconnectAll()
      isRegisteredForHid.filter { it }.first()
      logd("connect isRegisteredForHid")
      // doitwrong
      hidDevice.filterNotNull().first().connect(device)
    }
  }

  @SuppressLint("MissingPermission")
  suspend fun disconnectAll() {
    logd("disconnect all")
    val hid = hidDevice.filterNotNull().first()
    connectedDevices.value.forEach {
      hid.disconnect(it)
    }
  }

//  @SuppressLint("MissingPermission")
//  fun disconnect() {
//    logd("disconnect")
//    scope.launch {
//      hidDevice.filterNotNull().first().disconnect(connectedDevice.value)
//    }
//  }

  private fun getAdapter() {
    logd("getAdapter")
    val bluetoothManager =
      application.getSystemService(Context.BLUETOOTH_SERVICE) as BluetoothManager
    btAdapter = bluetoothManager.adapter
    logd("getBtAdapter $btAdapter")
  }

  @SuppressLint("MissingPermission")
  fun getPairedDevices(): List<BluetoothDevice> {
    logd("getPairedDevices")
    logd("getPairedDevices btAdapter $btAdapter")
    val bondedDevices = btAdapter?.bondedDevices
    logd("getPairedDevices bondedDevices $bondedDevices")
    return btAdapter?.bondedDevices?.toList() ?: listOf()
  }

  private fun getProfileProxy() {
    logd("GetProfileProxy")
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

    btAdapter?.getProfileProxy(
      application,
      serviceListener,
      BluetoothProfile.HID_DEVICE
    )
  }

  @SuppressLint("MissingPermission")
  private fun registerApp() {
    logd("registerApp")
    // https://github.com/jusss/BluetoothConnect
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

  fun sendString(s: String): Boolean {
    if (connectedDevices.value.isEmpty()) {
      return false
    }
    // UI restricts user to only connect to one device at a time
    // I think HID profile restricts to only one connected device as well
    connectedDevices.value.forEach {
      // launch in IO dispatcher
      scope.launch {
        sendString(s, it)
      }
    }
    return true
  }

  @SuppressLint("MissingPermission")
  private suspend fun sendString(s: String, device: BluetoothDevice) {
    logd("sendString")
    val hid = hidDevice.filterNotNull().first()
    s.toCharArray().forEach { char ->
      // for now just skip non US ascii characters
      // I should probably show snackbar
      hid.sendReport(
        device,
        KEYBOARD_ID,
        asciiCharToReportByteArray[char] ?: return@forEach
      )
      // delay period came from trial and error
      // to prevent skipped and ghost key presses
      delay(5)
      hid.sendReport(device, KEYBOARD_ID, ByteArray(8) { 0 })
//      delay(1)
//      hid.sendReport(device, KEYBOARD_ID, ByteArray(8) { 0 })
      delay(20)
    }
  }

  @SuppressLint("MissingPermission")
  fun hidDeviceUnregisterApp() {
    try {
      // todo do I need to disconnectAll ???
      hidDevice.value?.unregisterApp()
    } catch (e: Exception) {
      Log.e("BtController", e.message.toString())
    }
  }
}