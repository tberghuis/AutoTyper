package dev.tberghuis.btmacrokb.tmp8

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel

class Tmp8Vm(
  private val application: Application,
) : AndroidViewModel(application) {
  val deepLinkVmc = DeepLinkVmc(application)
}


class DeepLinkVmc(application: Application) {
  var showDialog by mutableStateOf(false)
}