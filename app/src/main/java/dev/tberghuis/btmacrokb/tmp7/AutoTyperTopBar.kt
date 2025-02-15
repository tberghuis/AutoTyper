package dev.tberghuis.btmacrokb.tmp7

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import dev.tberghuis.btmacrokb.composables.LocalNavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AutoTyperTopBar() {
  val nav = LocalNavController.current
  TopAppBar(
    title = { Text("Auto Typer") },
    modifier = Modifier,
    actions = {
//      IconButton(
//        onClick = {
//          nav.navigate(Route.Settings)
//        },
//        modifier = Modifier
//      ) {
//        Icon(Icons.Filled.Settings, "settings")
//      }
    },
  )
}
