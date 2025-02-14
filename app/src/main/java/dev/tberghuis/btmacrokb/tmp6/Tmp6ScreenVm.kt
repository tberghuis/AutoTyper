package dev.tberghuis.btmacrokb.tmp6

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import dev.tberghuis.btmacrokb.util.logd

class Tmp6ScreenVm(
  private val application: Application,
) : AndroidViewModel(application) {


  fun runOnce() {
    logd("run once")
  }

}