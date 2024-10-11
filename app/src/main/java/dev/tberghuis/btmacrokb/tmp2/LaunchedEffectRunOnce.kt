package dev.tberghuis.btmacrokb.tmp2

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.launch

class LaunchedEffectRunOnceVm(private val state: SavedStateHandle) : ViewModel() {
  fun runOnce(lambda: suspend () -> Unit) {
    // do I need synchronized here??? not really
    val hasBeenRun = state["hasBeenRun"] ?: false
    if (hasBeenRun) {
      return
    }
    state["hasBeenRun"] = true
    viewModelScope.launch {
      lambda()
    }
  }
}

@Composable
fun LaunchedEffectRunOnce(lambda: suspend () -> Unit) {
  val vm: LaunchedEffectRunOnceVm = viewModel()
  LaunchedEffect(Unit) {
    vm.runOnce(lambda)
  }
}