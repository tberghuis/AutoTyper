package dev.tberghuis.btmacrokb.service

import android.app.Application
import android.content.Intent
import androidx.lifecycle.AndroidViewModel
import dev.tberghuis.btmacrokb.MyApplication
import dev.tberghuis.btmacrokb.util.logd

class StopServiceVm(
  private val application: Application
) : AndroidViewModel(application) {

  init {
    logd("StopServiceVm init")
  }

  override fun onCleared() {
    logd("StopServiceVm onCleared")
    if ((application as MyApplication).isBtConnected == false) {
      val intent = Intent(application, MyBtService::class.java)
      application.stopService(intent)
    }
    super.onCleared()
  }
}