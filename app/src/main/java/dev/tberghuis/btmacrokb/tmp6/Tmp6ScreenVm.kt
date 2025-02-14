package dev.tberghuis.btmacrokb.tmp6

import android.app.Application
import android.net.Uri
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import dev.tberghuis.btmacrokb.util.logd
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class Tmp6ScreenVm(
  private val application: Application,
) : AndroidViewModel(application) {

  val controller = SingleUseBtController2(application)

  // call from rememberSaveable to only run once
  fun processDataUri(data: Uri) {
    logd("processDataUri $data")
    val deviceString = data.getQueryParameter("device") ?: return
    var payloadString = data.getQueryParameter("payload") ?: return

    val encrypted = data.queryParameterNames.contains("encrypted")
    if (encrypted) {
      // todo get password datastore
//      if no password return
//      test wrong password
      payloadString = SimpleAES.decrypt(payloadString, "1234")
    }

    logd("deviceString $deviceString payloadString $payloadString")
    viewModelScope.launch(IO) {
      controller.sendPayload(deviceString, payloadString)
    }
  }
}