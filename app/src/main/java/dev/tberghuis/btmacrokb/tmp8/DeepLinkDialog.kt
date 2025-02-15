package dev.tberghuis.btmacrokb.tmp8

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.AlertDialogDefaults
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun Tmp6Screen(
) {
  Column {
    Text("hello tmp 6")
  }
}

@Preview
@Composable
fun Tmp6ScreenPreview() {
  Tmp6Screen()
}


@Composable
fun DeepLinkDialog() {
//  BasicAlertDialog()
}


@Preview
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BasicAlertDialogSample() {
  val openDialog = remember { mutableStateOf(true) }

  if (openDialog.value) {
    BasicAlertDialog(
      onDismissRequest = {
        openDialog.value = false
      }
    ) {
      Surface(
        modifier = Modifier
          .wrapContentWidth()
          .wrapContentHeight(),
        shape = MaterialTheme.shapes.large,
        tonalElevation = AlertDialogDefaults.TonalElevation
      ) {
        Column(modifier = Modifier.padding(16.dp)) {
          Text(
            text =
            "This area typically contains the supportive text " +
                "which presents the details regarding the Dialog's purpose.",
          )
          Spacer(modifier = Modifier.height(24.dp))
          TextButton(
            onClick = { openDialog.value = false },
            modifier = Modifier.align(Alignment.End)
          ) {
            Text("Confirm")
          }
        }
      }
    }
  }
}



