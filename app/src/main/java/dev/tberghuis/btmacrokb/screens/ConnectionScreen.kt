package dev.tberghuis.btmacrokb.screens

import android.annotation.SuppressLint
import android.bluetooth.BluetoothClass
import android.bluetooth.BluetoothDevice
import android.content.Intent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bluetooth
import androidx.compose.material.icons.filled.Computer
import androidx.compose.material.icons.filled.Headphones
import androidx.compose.material.icons.filled.Keyboard
import androidx.compose.material.icons.filled.Smartphone
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LifecycleEventEffect
import androidx.lifecycle.viewmodel.compose.viewModel
import dev.tberghuis.btmacrokb.composables.BottomBar
import dev.tberghuis.btmacrokb.nav.LocalSnackbarHostState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConnectionScreen(
  vm: ConnectionVm = viewModel()
) {
  LifecycleEventEffect(Lifecycle.Event.ON_RESUME) {
    vm.getPairedDevices()
  }

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
  ) { padding ->
    PairedDeviceList(padding)
  }
}

@Composable
fun PairedDeviceList(
  padding: PaddingValues,
  vm: ConnectionVm = viewModel()
) {
  val context = LocalContext.current
  LazyColumn(
    modifier = Modifier
      .consumeWindowInsets(padding)
      .padding(padding)
      .imePadding()
      .fillMaxSize(),
    contentPadding = PaddingValues(10.dp),
    verticalArrangement = Arrangement.spacedBy(15.dp),
    horizontalAlignment = Alignment.CenterHorizontally,
  ) {
    item {

      Text("Do not connect in Bluetooth settings, only pair device.")
      Button(
        onClick = {
          context.startActivity(Intent(android.provider.Settings.ACTION_BLUETOOTH_SETTINGS))
        },
        modifier = Modifier.padding(top = 15.dp)
      ) {
        Text("Open Bluetooth Settings")
      }
    }
    item {
      if (vm.pairedDevices.value.isNotEmpty()) {
        Text("Paired Devices", fontWeight = FontWeight.Bold)
      } else {
        Text("No paired devices", fontWeight = FontWeight.Bold)
      }
    }
    items(vm.pairedDevices.value) { device ->
      PairedDeviceCard(device)
    }
  }
}

@SuppressLint("MissingPermission")
@Composable
fun PairedDeviceCard(
  device: BluetoothDevice, vm: ConnectionVm = viewModel()
) {
  val connected = vm.connectedDevices.value.contains(device)
  ElevatedCard(
    modifier = Modifier,
  ) {
    Row(
      modifier = Modifier.fillMaxWidth(),
      horizontalArrangement = Arrangement.SpaceBetween,
      verticalAlignment = Alignment.CenterVertically,
    ) {
      Box(Modifier.size(40.dp), contentAlignment = Alignment.Center) {
        Icon(
          when (device.bluetoothClass?.majorDeviceClass) {
            BluetoothClass.Device.Major.PHONE -> Icons.Default.Smartphone
            BluetoothClass.Device.Major.AUDIO_VIDEO -> Icons.Default.Headphones
            BluetoothClass.Device.Major.COMPUTER -> Icons.Default.Computer
            BluetoothClass.Device.Major.PERIPHERAL -> Icons.Default.Keyboard
            else -> Icons.Default.Bluetooth
          },
          "Type"
        )
      }
      Column {
        Text(device.name ?: "null")
        Text(device.address ?: "null")
      }
      if (vm.connectedDevices.value.isEmpty()) {
        Button(onClick = {
          vm.connect(device)
        }) {
          Text("connect")
        }
      } else if (connected) {
        Button(
          onClick = {
            vm.disconnect()
          },
          modifier = Modifier,
        ) {
          Text("disconnect")
        }
      } else {
        // todo figure out alignment of device text when connected
        Spacer(Modifier.width(50.dp))
      }
    }
  }
}