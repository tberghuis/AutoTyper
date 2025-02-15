package dev.tberghuis.btmacrokb.tmp8

import android.app.Application
import android.bluetooth.BluetoothDevice
import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.ClipboardManager
import androidx.compose.ui.text.AnnotatedString
import dev.tberghuis.btmacrokb.service.provideMyBtService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class DeepLinkVmc(application: Application, private val scope: CoroutineScope) {
  var showDialog by mutableStateOf(false)
  val pairedDevices = mutableStateOf<List<BluetoothDevice>>(listOf())
  var selectedDevice by mutableStateOf<BluetoothDevice?>(null)

  init {
    scope.launch {
      pairedDevices.value = application.provideMyBtService().myBtController.getPairedDevices()
    }
  }

  fun deepLinkToClipboard(
    clipboardManager: ClipboardManager,
    macroId: Long,
  ) {
    // todo if selectedDevice null, snackbar or error dialog
    if (selectedDevice == null) {
      return
    }
    scope.launch {
      val deepLinkUri = Uri.Builder().apply {
        scheme("autotyper")
        authority("autotyper")
        appendQueryParameter("device", selectedDevice?.address)
        appendQueryParameter("macro_id", macroId.toString())
        build()
      }.toString()
      clipboardManager.setText(AnnotatedString(deepLinkUri))
    }
  }
}