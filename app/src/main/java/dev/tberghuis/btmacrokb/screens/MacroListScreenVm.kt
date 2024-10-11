package dev.tberghuis.btmacrokb.screens

import android.app.Application
import androidx.compose.material3.SnackbarHostState
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import dev.tberghuis.btmacrokb.data.Macro
import dev.tberghuis.btmacrokb.data.appDatabase
import dev.tberghuis.btmacrokb.usecase.sendPayload
import kotlinx.coroutines.launch

class MacroListScreenVm(
  private val application: Application,
//  private val state: SavedStateHandle
) : AndroidViewModel(application) {
  private val macroDao = application.appDatabase.macroDao()
  val macroListFlow = macroDao.getAll()

  fun autoType(macro: Macro, snackbarHostState: SnackbarHostState) = sendPayload(
    application,
    viewModelScope,
    snackbarHostState,
    macro.payload
  )

  fun delete(macro: Macro) {
    viewModelScope.launch {
      macroDao.delete(macro)
    }
  }
}