package dev.tberghuis.btmacrokb.service

import android.app.Application
import android.content.Intent
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.SavedStateHandle
import dev.tberghuis.btmacrokb.util.logd

class StopServiceVm(
  private val application: Application, private val state: SavedStateHandle
) : AndroidViewModel(application) {
  init {
    logd("StopServiceVm init")
  }

  // for some reason this is not called when a user closes freshly installed app on permission screen
  // but in this case service has not started so service.stopSelf does not need to be called
//  override fun onCleared() {
//    logd("StopServiceVm onCleared")
//    val intent = Intent(application, MyBtService::class.java).apply {
//      action = ACTION_APP_EXIT
//    }
//    application.startService(intent)
//    super.onCleared()
//  }
}