package dev.tberghuis.btmacrokb.tmp8

import android.app.Application
import android.bluetooth.BluetoothDevice
import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.ClipboardManager
import androidx.compose.ui.text.AnnotatedString
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import dev.tberghuis.btmacrokb.data.PreferencesRepository
import dev.tberghuis.btmacrokb.service.provideMyBtService
import dev.tberghuis.btmacrokb.tmp6.SimpleAES
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class Tmp8Vm(
  private val application: Application,
) : AndroidViewModel(application) {
  val deepLinkVmc = DeepLinkVmc(application, viewModelScope)
}


class DeepLinkVmc(application: Application, private val scope: CoroutineScope) {
  private val prefs = PreferencesRepository.getInstance(application)

  var showDialog by mutableStateOf(false)
  var encryptedChecked by mutableStateOf(false)

  val pairedDevices = mutableStateOf<List<BluetoothDevice>>(listOf())

  //  var selectedDeviceIndex by mutableStateOf<Int?>(null)
  var selectedDevice by mutableStateOf<BluetoothDevice?>(null)

  init {
    scope.launch {
      pairedDevices.value = application.provideMyBtService().myBtController.getPairedDevices()
    }
  }

  fun deepLinkToClipboard(
    clipboardManager: ClipboardManager,
    address: String,
    payload: String,
    encrypted: Boolean
  ) {
    scope.launch {
      val deepLinkUri = Uri.Builder().apply {
        scheme("autotyper")
        authority("autotyper")
        appendQueryParameter("device", address)
        if (encrypted) {
          appendQueryParameter("encrypted", "1")
          SimpleAES.encrypt(payload, prefs.encryptionPasswordFlow.first())
        } else {
          payload
        }.let {
          appendQueryParameter("payload", it)
        }
        build()
      }.toString()
      clipboardManager.setText(AnnotatedString(deepLinkUri))
    }
  }
}