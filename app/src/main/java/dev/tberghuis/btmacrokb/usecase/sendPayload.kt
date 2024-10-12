package dev.tberghuis.btmacrokb.usecase

import android.app.Application
import androidx.compose.material3.SnackbarHostState
import dev.tberghuis.btmacrokb.util.logd
import dev.tberghuis.btmacrokb.service.provideMyBtService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

fun sendPayload(
  application: Application,
  scope: CoroutineScope,
  snackbarHostState: SnackbarHostState,
  payload: String
) {
  logd("autoType payload $payload")
  scope.launch {
    // should I wrap in try catch?
    val isConnected = application.provideMyBtService().myBtController.sendString(payload)
    if (isConnected) {
      snackbarHostState.showSnackbar("payload sent")
    } else {
      snackbarHostState.showSnackbar("error: Not connected")
    }
  }
}