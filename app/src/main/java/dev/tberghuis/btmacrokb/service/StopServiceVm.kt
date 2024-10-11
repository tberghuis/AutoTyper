package dev.tberghuis.btmacrokb.service

import android.app.Application
import android.content.Intent
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.SavedStateHandle
import dev.tberghuis.btmacrokb.logd

class StopServiceVm(
  private val application: Application, private val state: SavedStateHandle
) : AndroidViewModel(application) {

  init {
    logd("StopServiceVm init")
  }

  override fun onCleared() {
    logd("StopServiceVm onCleared")
    val intent = Intent(application, MyBtService::class.java)
    application.stopService(intent)
    super.onCleared()
  }
}