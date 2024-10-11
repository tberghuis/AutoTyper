package dev.tberghuis.btmacrokb.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import dev.tberghuis.btmacrokb.composables.BottomBar
import dev.tberghuis.btmacrokb.composables.LocalNavController
import dev.tberghuis.btmacrokb.nav.Route
import dev.tberghuis.btmacrokb.nav.LocalSnackbarHostState
import dev.tberghuis.btmacrokb.data.Macro

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MacroListScreen() {
  val nav = LocalNavController.current
  Scaffold(
    modifier = Modifier.systemBarsPadding(),
    topBar = {
      TopAppBar(
        title = { Text("Auto Typer") },
        modifier = Modifier,
      )
    },
    bottomBar = {
      BottomBar()
    },
    snackbarHost = {
      SnackbarHost(hostState = LocalSnackbarHostState.current)
    },
    floatingActionButton = {
      FloatingActionButton(
        onClick = {
          // id = 0 is for new macro
          nav.navigate(Route.MacroDetail(0))
        },
      ) {
        Icon(Icons.Filled.Add, contentDescription = "Add")
      }
    },
  ) { padding ->
    MacroListScreenContent(padding)
  }
}

@Composable
fun MacroListScreenContent(
  padding: PaddingValues,
  vm: MacroListScreenVm = viewModel()
) {
  val nav = LocalNavController.current
  val macroList by vm.macroListFlow.collectAsStateWithLifecycle(listOf())

  LazyColumn(
    Modifier
      .consumeWindowInsets(padding)
      .padding(padding)
      .imePadding(),
    contentPadding = PaddingValues(10.dp),
    verticalArrangement = Arrangement.spacedBy(10.dp)
  ) {
    item {
      if (macroList.isEmpty()) {
        Text("No saved scripts. To add scripts, use the '+' button (bottom right)")
      }
    }
    items(macroList, key = {
      it.id
    }) { macro ->
      MacroListCard(macro)
    }
    item {
      // clear scroll FAB
      // height = 154px / 2.75 density
      Spacer(Modifier.height(56.dp))
    }
  }
}

@Composable
fun MacroListCard(macro: Macro, vm: MacroListScreenVm = viewModel()) {
  val nav = LocalNavController.current
  val snackbarHostState = LocalSnackbarHostState.current
  ElevatedCard() {
    Row(
      modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 10.dp),
      horizontalArrangement = Arrangement.SpaceBetween,
      verticalAlignment = Alignment.CenterVertically,
    ) {
      Text(
        macro.title,
        modifier = Modifier.weight(1f),
        overflow = TextOverflow.Ellipsis,
        maxLines = 1,
      )
      Row {
        IconButton(onClick = {
          vm.delete(macro)
        }) {
          Icon(Icons.Filled.Delete, contentDescription = "delete")
        }
        IconButton(onClick = {
          nav.navigate(Route.MacroDetail(macro.id))
        }) {
          Icon(Icons.Filled.Edit, contentDescription = "edit")
        }
        Button(onClick = {
          vm.autoType(macro, snackbarHostState)
        }) {
          Text("Type")
        }
      }
    }
  }
}