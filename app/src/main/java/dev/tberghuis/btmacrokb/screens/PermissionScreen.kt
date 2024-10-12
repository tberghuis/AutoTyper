package dev.tberghuis.btmacrokb.screens

import android.Manifest
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.provider.Settings
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavOptions
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionStatus
import com.google.accompanist.permissions.rememberPermissionState
import dev.tberghuis.btmacrokb.util.logd
import dev.tberghuis.btmacrokb.composables.LocalNavController
import dev.tberghuis.btmacrokb.nav.Route

@OptIn(ExperimentalPermissionsApi::class, ExperimentalMaterial3Api::class)
@Composable
fun PermissionScreen() {
  // 31 == android 12
  val btPermissionState = rememberPermissionState(
    if (Build.VERSION.SDK_INT >= 31)
      Manifest.permission.BLUETOOTH_CONNECT
    else
      Manifest.permission.ACCESS_FINE_LOCATION,
  )

  val nav = LocalNavController.current
  val context = LocalContext.current

  if (btPermissionState.status == PermissionStatus.Granted) {
    LaunchedEffect(Unit) {
      logd("bt permission granted")
      nav.navigate(
        Route.Connection,
        NavOptions.Builder().setPopUpTo(Route.Permission, true).build()
      )
    }
    return
  }

  val verticalScrollState = rememberScrollState()

  Scaffold(
    modifier = Modifier,
    topBar = {
      TopAppBar(
        title = { Text("Auto Typer") },
        modifier = Modifier,
      )
    },
  ) { padding ->
    Column(
      modifier = Modifier
        .padding(padding)
        .padding(15.dp)
        .fillMaxSize()
        .verticalScroll(verticalScrollState),
      verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterVertically),
      horizontalAlignment = Alignment.CenterHorizontally,
    ) {
      // doitwrong
      if (Build.VERSION.SDK_INT >= 31) {
        Text("For Auto Typer to function, please allow permission \"Bluetooth Connect\"")
      } else {
        Text("For Auto Typer to function, please allow permission \"Access Fine Location\"")
      }
      Button(onClick = {
        logd("button launchPermissionRequest()")
        btPermissionState.launchPermissionRequest()
      }) {
        Text("Launch Permission Request")
      }
      Text("If above button not working, please grant the permission in system settings manually.")
      if (Build.VERSION.SDK_INT >= 31) {
        Text("Settings > Apps > Manage Apps > Auto Typer > App Permissions > Nearby Devices")
      } else {
        Text("Settings > Apps > Auto Typer > Permissions > Location")
      }
      Button(onClick = {
        openAppSettings(context)
      }) {
        Text("Open App Settings")
      }
    }
  }
}

fun openAppSettings(context: Context) {
  val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
  val uri = Uri.fromParts("package", context.packageName, null)
  intent.setData(uri)
  context.startActivity(intent)
}