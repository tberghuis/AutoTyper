package dev.tberghuis.btmacrokb.tmp7

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import dev.tberghuis.btmacrokb.data.PreferencesRepository
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class SettingsScreenVm(
  private val application: Application,
) : AndroidViewModel(application) {
  private val prefs = PreferencesRepository.getInstance(application)

  var password by mutableStateOf("")

  init {
    prefs.encryptionPasswordFlow.onEach { password = it }.launchIn(viewModelScope)
  }

  fun save() {
    viewModelScope.launch {
      prefs.setEncryptionPassword(password)
    }
  }
}