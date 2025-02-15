package dev.tberghuis.btmacrokb.tmp6

import android.app.Application
import android.net.Uri
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import dev.tberghuis.btmacrokb.data.PreferencesRepository
import dev.tberghuis.btmacrokb.data.appDatabase
import dev.tberghuis.btmacrokb.util.logd
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class DeepLinkScreenVm(
  private val application: Application,
) : AndroidViewModel(application) {
  private val prefs = PreferencesRepository.getInstance(application)

  val controller = SingleUseBtController2(application)

  // call from rememberSaveable to only run once
  fun processDataUri(data: Uri) {
    logd("processDataUri $data")

    // todo display errors
    // early returns

    viewModelScope.launch(IO) {

      val address = data.getQueryParameter("device") ?: return@launch
      val payload = data.getQueryParameter("payload") ?: run {
        val macroId = data.getQueryParameter("macro_id")?.toLong() ?: return@launch
        application.appDatabase.macroDao().getById(macroId)?.payload
      } ?: return@launch

//    val encrypted = data.queryParameterNames.contains("encrypted")
//      if (encrypted) {
//        val password = prefs.encryptionPasswordFlow.first()
//        // todo try catch wrong password
//        payloadString = SimpleAES.decrypt(payloadString, password)
//      }

      logd("address $address payload $payload")
      controller.sendPayload(address, payload)
    }
  }
}