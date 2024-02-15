package com.indisp.securiti.design_system.components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <T> DropDownField(
    selectedValue: String,
    options: List<DropDownItem<T>>,
    onOptionSelected: (T) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Column {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .height(IntrinsicSize.Min)
                .border(1.dp, Color.Gray)
        ) {
            TextField(
                value = selectedValue,
                onValueChange = {},
                modifier = Modifier
                    .weight(1f)
                    .clickable { expanded = !expanded },
                readOnly = true,
                enabled = false
            )

            Icon(
                imageVector = Icons.Default.ArrowDropDown,
                contentDescription = "",
                tint = Color.White,
                modifier = Modifier
                    .width(46.dp)
                    .clickable { expanded = !expanded }
            )
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp)
        ) {
            options.forEach { it ->
                DropdownMenuItem(
                    text = { Text(text = it.displayText) },
                    onClick = {
                        onOptionSelected(it.item)
                        expanded = false
                    }
                )
            }
        }
    }
}

data class DropDownItem<T>(val displayText: String, val item: T)