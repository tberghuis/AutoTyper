package dev.tberghuis.btmacrokb.tmp3

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import dev.tberghuis.btmacrokb.screens.ConnectionVm

class TmpScreenVm(
  private val application: Application,
) : AndroidViewModel(application) {

  val connectionVm = ConnectionVm(application)

  init {
    connectionVm.observeBtController()
  }


  fun connectB450() {

  }

}