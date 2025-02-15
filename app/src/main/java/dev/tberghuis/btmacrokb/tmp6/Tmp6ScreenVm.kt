package dev.tberghuis.btmacrokb.tmp6

import android.app.Application
import android.net.Uri
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import dev.tberghuis.btmacrokb.data.PreferencesRepository
import dev.tberghuis.btmacrokb.util.logd
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class Tmp6ScreenVm(
  private val application: Application,
) : AndroidViewModel(application) {
  private val prefs = PreferencesRepository.getInstance(application)

  val controller = SingleUseBtController2(application)

  // call from rememberSaveable to only run once
  fun processDataUri(data: Uri) {
    logd("processDataUri $data")
    val deviceString = data.getQueryParameter("device") ?: return
    var payloadString = data.getQueryParameter("payload") ?: return

    val encrypted = data.queryParameterNames.contains("encrypted")


    viewModelScope.launch(IO) {

      if (encrypted) {
        val password = prefs.encryptionPasswordFlow.first()
        // todo try catch wrong password
        payloadString = SimpleAES.decrypt(payloadString, password)
      }

      logd("deviceString $deviceString payloadString $payloadString")
      controller.sendPayload(deviceString, payloadString)
    }
  }
}