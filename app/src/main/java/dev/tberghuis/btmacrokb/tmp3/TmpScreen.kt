package dev.tberghuis.btmacrokb.tmp3

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel


@Composable
fun TmpScreen(
  vm: TmpScreenVm = viewModel()
) {
  Column {

    Button(onClick = {
      vm.findB450()
    }) {
      Text("find b450")
    }



    Button(onClick = {
      vm.connectB450()
    }) {
      Text("connect b450")
    }
  }
}

@Preview
@Composable
fun TmpScreenPreview() {
  TmpScreen()
}