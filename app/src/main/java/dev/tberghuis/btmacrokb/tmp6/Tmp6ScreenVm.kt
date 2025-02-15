package dev.tberghuis.btmacrokb.tmp6

import android.app.Application
import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
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
  private val controller = SingleUseBtController2(application)

  var uiDeeplink by mutableStateOf("")
  var uiDevice by mutableStateOf("")
  var uiPayload by mutableStateOf("")
  var uiResult by mutableStateOf("")

  // call from rememberSaveable to only run once
  fun processDataUri(data: Uri) {
    logd("processDataUri $data")
    uiDeeplink = data.toString()
    viewModelScope.launch(IO) {
      val address = data.getQueryParameter("device")
      val payload = data.getQueryParameter("payload") ?: run {
        val macroId = data.getQueryParameter("macro_id")?.toLong() ?: return@run null
        application.appDatabase.macroDao().getById(macroId)?.payload
      }
      if(address==null || payload == null){
        uiResult = "error"
        return@launch
      }
      uiDevice = address
      uiPayload = payload
      controller.sendPayload(address, payload)
      uiResult = "sent"
    }
  }
}