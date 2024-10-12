package dev.tberghuis.btmacrokb.screens

import android.app.Application
import android.bluetooth.BluetoothDevice
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import dev.tberghuis.btmacrokb.util.logd
import dev.tberghuis.btmacrokb.service.provideMyBtService
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class ConnectionVm(
  private val application: Application,
//  private val state: SavedStateHandle
) : AndroidViewModel(application) {
  val pairedDevices = mutableStateOf<List<BluetoothDevice>>(listOf())
  val connectedDevices = mutableStateOf<Set<BluetoothDevice>>(setOf())
  private var observeBtControllerJob: Job? = null

  init {
    observeBtController()
  }

  // button to run this manually if service is killed
  private fun observeBtController() {
    observeBtControllerJob?.cancel()
    observeBtControllerJob = viewModelScope.launch {
      getPairedDevices()
      application.provideMyBtService().myBtController.connectedDevices.collectLatest {
        logd("observeBtController connectedDevice.collectLatest $it")
        connectedDevices.value = it
      }
    }
  }

  fun getPairedDevices() {
    viewModelScope.launch {
      pairedDevices.value = application.provideMyBtService().myBtController.getPairedDevices()
    }
  }

  fun connect(device: BluetoothDevice) {
    viewModelScope.launch {
      application.provideMyBtService().myBtController.connect(device)
    }
  }

  fun disconnect() {
    viewModelScope.launch {
      application.provideMyBtService().myBtController.disconnectAll()
    }
  }
}