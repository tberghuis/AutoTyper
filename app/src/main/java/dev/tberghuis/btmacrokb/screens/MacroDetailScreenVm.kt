package dev.tberghuis.btmacrokb.screens

import android.app.Application
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import dev.tberghuis.btmacrokb.data.Macro
import dev.tberghuis.btmacrokb.data.appDatabase
import dev.tberghuis.btmacrokb.util.logd
import dev.tberghuis.btmacrokb.nav.Route
import dev.tberghuis.btmacrokb.usecase.sendPayload
import kotlinx.coroutines.launch

class MacroDetailScreenVm(
  private val application: Application, private val state: SavedStateHandle
) : AndroidViewModel(application) {
  private val macroDetail = state.toRoute<Route.MacroDetail>()
  private var newMacroId: Long = 0

  var title by mutableStateOf("")
  var payload by mutableStateOf("")

  init {
    logd("MacroDetailScreenVm init macroDetail $macroDetail")
    loadData()
  }

  private fun loadData() {
    viewModelScope.launch {
      application.appDatabase.macroDao().getById(macroDetail.id)?.let {
        title = it.title
        payload = it.payload
      }
    }
  }

  fun autoType(snackbarHostState: SnackbarHostState) = sendPayload(
    application,
    viewModelScope,
    snackbarHostState,
    payload
  )

  fun save(snackbarHostState: SnackbarHostState) {
    viewModelScope.launch {
      if (title.isEmpty()) {
        snackbarHostState.showSnackbar("error: Title is empty")
        return@launch
      }
      if (payload.isEmpty()) {
        snackbarHostState.showSnackbar("error: Payload is empty")
        return@launch
      }
      try {
        if (macroDetail.id == 0L) {
          // new macro
          val m = Macro(
            id = newMacroId,
            title = title,
            payload = payload
          )
          newMacroId = application.appDatabase.macroDao().insert(m)
        } else {
          // update
          val m = Macro(
            id = macroDetail.id,
            title = title,
            payload = payload
          )
          application.appDatabase.macroDao().update(m)
        }
        snackbarHostState.showSnackbar("Saved")
      } catch (e: Exception) {
        // todo
      }
    }
  }
}