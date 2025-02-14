package dev.tberghuis.btmacrokb.tmp6

import android.app.Application
import android.net.Uri
import androidx.lifecycle.AndroidViewModel
import dev.tberghuis.btmacrokb.util.logd

class Tmp6ScreenVm(
  private val application: Application,
) : AndroidViewModel(application) {


  // call from rememberSaveable to only run once
  fun processDataUri(data: Uri) {
    logd("processDataUri $data")
    val deviceString = data.getQueryParameter("device") ?: return
    val payloadString = data.getQueryParameter("payload") ?: return

    logd("deviceString $deviceString payloadString $payloadString")
  }

}