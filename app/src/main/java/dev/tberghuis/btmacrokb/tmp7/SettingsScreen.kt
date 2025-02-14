package dev.tberghuis.btmacrokb.tmp7

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import dev.tberghuis.btmacrokb.composables.LocalNavController
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp

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
  var passwordVisible by remember { mutableStateOf(false) }

  Column(
    modifier = modifier.fillMaxSize(),
    verticalArrangement = Arrangement.Top,
    horizontalAlignment = Alignment.CenterHorizontally,
  ) {
    Spacer(Modifier.height(50.dp))
    Text("Password for encrypted deep links:")
    OutlinedTextField(
      value = "random",
      onValueChange = {},
      modifier = Modifier,
      label = { Text("Password") },
      trailingIcon = {
        val image = if (passwordVisible)
          Icons.Filled.Visibility
        else Icons.Filled.VisibilityOff

        val description = if (passwordVisible) "Hide password" else "Show password"

        IconButton(onClick = { passwordVisible = !passwordVisible }) {
          Icon(imageVector = image, description)
        }
      },
      visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
      keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
      singleLine = true,
    )
  }
}


@Composable
fun PasswordTextFieldExample() {
  var password by remember { mutableStateOf("") }
  var passwordVisible by remember { mutableStateOf(false) }

  Column(modifier = Modifier.padding(16.dp)) {
    OutlinedTextField(
      value = password,
      onValueChange = { password = it },
      label = { Text("Password") },
      singleLine = true,
      visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
      keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
      trailingIcon = {
        val image = if (passwordVisible)
          Icons.Filled.Visibility
        else Icons.Filled.VisibilityOff

        val description = if (passwordVisible) "Hide password" else "Show password"

        IconButton(onClick = { passwordVisible = !passwordVisible }) {
          Icon(imageVector = image, description)
        }
      },
      modifier = Modifier.padding(8.dp)
    )
  }
}

