package dev.tberghuis.btmacrokb.tmp7

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import dev.tberghuis.btmacrokb.composables.LocalNavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen() {
  val nav = LocalNavController.current


  Scaffold(
    modifier = Modifier,
    topBar = {
      TopAppBar(
        title = { Text("Settings") },
        modifier = Modifier,
        navigationIcon = {
          IconButton(onClick = {
            nav.navigateUp()
          }) {
            Icon(Icons.AutoMirrored.Filled.ArrowBack, "back")
          }
        },
      )
    },
  ) { padding ->
    SettingsScreenContent(Modifier.padding(padding))
  }
}

@Composable
fun SettingsScreenContent(modifier: Modifier) {
  Column(
    modifier = modifier,
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally,
  ) {
    Text("Password for encrypted deep links:")
//    TextField()
  }
}