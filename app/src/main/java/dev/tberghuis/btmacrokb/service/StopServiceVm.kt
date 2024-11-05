package dev.tberghuis.btmacrokb.service

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.SavedStateHandle
import dev.tberghuis.btmacrokb.util.logd

class StopServiceVm(
  private val application: Application, private val state: SavedStateHandle
) : AndroidViewModel(application) {
  init {
    logd("StopServiceVm init")
  }

  override fun onCleared() {
    logd("StopServiceVm onCleared")
    // did not work
//    val intent = Intent(application, MyBtService::class.java).apply {
//      action = ACTION_APP_EXIT
//    }
//    application.startService(intent)
    super.onCleared()
  }
}