package dev.tberghuis.btmacrokb.screens

import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Link
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.tberghuis.btmacrokb.composables.BottomBar
import dev.tberghuis.btmacrokb.composables.LocalNavController
import dev.tberghuis.btmacrokb.nav.LocalSnackbarHostState
import dev.tberghuis.btmacrokb.composables.DeepLinkDialog

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MacroDetailScreen(
  vm: MacroDetailScreenVm = viewModel()
) {
  val nav = LocalNavController.current
  Scaffold(
    // https://stackoverflow.com/questions/71610150/show-keyboard-over-scaffolds-bottombar-in-jetpack-compose-and-apply-proper-inse
    // https://stackoverflow.com/questions/73894748/compose-how-to-have-ime-padding-and-scaffold-padding-with-edge-to-edge-and-wind
    modifier = Modifier.systemBarsPadding(),
    topBar = {
      TopAppBar(
        title = { Text("Auto Typer") },
        modifier = Modifier,
        navigationIcon = {
          IconButton(onClick = {
            nav.popBackStack()
          }) {
            Icon(Icons.AutoMirrored.Filled.ArrowBack, "back")
          }
        },
      )
    },
    bottomBar = {
      BottomBar()
    },
    snackbarHost = {
      SnackbarHost(
        hostState = LocalSnackbarHostState.current,
        // todo fix padding too large when IME showing
        modifier = Modifier.imePadding(),
      )
    },
    contentWindowInsets = WindowInsets(0, 0, 0, 0),
  ) { padding ->
    MacroDetailScreenContent(padding)
  }


  if (vm.deepLinkVmc.showDialog) {
    DeepLinkDialog(onDismissRequest = {
      vm.deepLinkVmc.showDialog = false
    })
  }


}

@Composable
fun MacroDetailScreenContent(
  padding: PaddingValues, vm: MacroDetailScreenVm = viewModel()
) {
  val nav = LocalNavController.current
  val scrollState = rememberScrollState()
  val snackbarHostState = LocalSnackbarHostState.current
  Column(
    modifier = Modifier
      .padding(padding)
      .consumeWindowInsets(padding)
//      .systemBarsPadding()
      .imePadding()
      .padding(horizontal = 10.dp)
      .fillMaxSize()
      .verticalScroll(scrollState),
    verticalArrangement = Arrangement.spacedBy(10.dp),
    horizontalAlignment = Alignment.CenterHorizontally,
  ) {
    TextField(
      value = vm.title,
      onValueChange = {
        vm.title = it
      },
      modifier = Modifier.fillMaxWidth(),
      label = { Text(text = "Title") },
    )

//    for (i in 1..20) {
//      Text("line $i")
//    }

    TextField(
      value = vm.payload,
      onValueChange = {
        vm.payload = it
      },
      modifier = Modifier.fillMaxWidth(),
      label = { Text(text = "Payload") },
      minLines = 5,
    )
    Row(
      horizontalArrangement = Arrangement.spacedBy(10.dp),
    ) {
      Button(onClick = {
        // todo if no macroId, snackbar, please save
        vm.getMacroId() ?: return@Button
        vm.deepLinkVmc.showDialog = true
      }) {
        Icon(Icons.Filled.Link, "link")
      }
      Button(onClick = { vm.save(snackbarHostState) }) {
        Icon(Icons.Filled.Save, "save")
      }
      Button(onClick = { vm.autoType(snackbarHostState) }) {
        Text("Auto Type")
      }
    }
  }
}