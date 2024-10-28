package dev.tberghuis.btmacrokb.service

import android.app.Application
import android.content.Intent
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import dev.tberghuis.btmacrokb.util.logd
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class StopServiceVm(
  private val application: Application, private val state: SavedStateHandle
) : AndroidViewModel(application) {
  var isConnected = false

  init {
    logd("StopServiceVm init")
    viewModelScope.launch(IO) {
      application.provideMyBtService().myBtController.connectedDevices.collectLatest {
        isConnected = it.isNotEmpty()
      }
    }
  }

  override fun onCleared() {
    logd("StopServiceVm onCleared")
    if (!isConnected) {
      val intent = Intent(application, MyBtService::class.java)
      application.stopService(intent)
    }
    super.onCleared()
  }
}