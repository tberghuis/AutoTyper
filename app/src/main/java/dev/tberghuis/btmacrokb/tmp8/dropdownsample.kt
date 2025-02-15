package dev.tberghuis.btmacrokb.tmp8


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun ExposedDropdownMenuBoxSample() {
  val options = listOf("Option 1", "Option 2", "Option 3", "Option 4", "Option 5")
  var expanded by remember { mutableStateOf(false) }
  var selectedOptionText by remember { mutableStateOf(options[0]) }

  Column {
    ExposedDropdownMenuBox(
      expanded = expanded,
      onExpandedChange = { expanded = !expanded },
      modifier = Modifier.fillMaxWidth()
    ) {
      TextField(
        modifier = Modifier.menuAnchor(),
        readOnly = true,
        value = selectedOptionText,
        onValueChange = { },
        label = { Text("Label") },
        trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
        colors = ExposedDropdownMenuDefaults.textFieldColors()
      )
      ExposedDropdownMenu(
        expanded = expanded,
        onDismissRequest = { expanded = false },
        modifier = Modifier.exposedDropdownSize()
      ) {
        options.forEach { selectionOption ->
          DropdownMenuItem(
            text = { Text(selectionOption) },
            onClick = {
              selectedOptionText = selectionOption
              expanded = false
            }
          )
        }
      }
    }
  }
}