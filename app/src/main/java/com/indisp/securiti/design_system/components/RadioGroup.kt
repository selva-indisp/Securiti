package com.indisp.securiti.design_system.components

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun <T> RadioGroup(
    selectedOption: T?,
    options: List<RadioItem<T>>,
    onOptionSelected: (T) -> Unit
) {
    LaunchedEffect(key1 = selectedOption) {
        Log.d("PROD", "RadioGroup: Selected option -> $selectedOption")
    }
    Row(
        modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Start
    ) {
        options.forEach {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(selected = selectedOption == it.item, onClick = { onOptionSelected(it.item) })
                Text(text = it.displayText, modifier = Modifier.padding(horizontal = 8.dp))
            }
            Spacer(modifier = Modifier.width(16.dp))
        }
    }
}

data class RadioItem<T>(val displayText: String, val item: T)