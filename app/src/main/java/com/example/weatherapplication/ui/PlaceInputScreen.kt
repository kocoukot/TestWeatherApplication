package com.example.weatherapplication.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.weatherapplication.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputScreen(input: String, isTown: Boolean, onGetForecast: (String, Boolean) -> Unit) {
    var value by rememberSaveable { mutableStateOf(input) }
    var isChecked by rememberSaveable { mutableStateOf(isTown) }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        TextField(

            value = value,
            onValueChange = {
                value = it
            },
            placeholder = {
                Text(stringResource(if (isChecked) R.string.town_name_or_zip_code_text else R.string.zip_code_text))
            },
            keyboardOptions = KeyboardOptions(keyboardType = if (isChecked) KeyboardType.Text else KeyboardType.Number)
        )


        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(stringResource(id = R.string.get_by_town_name))
            Switch(checked = isChecked, onCheckedChange = {
                value = ""
                isChecked = !isChecked
            })
        }

        TextButton(
            enabled = value.isNotEmpty(),
            modifier = Modifier.padding(top = 8.dp),
            onClick = {
                onGetForecast.invoke(value, isChecked)
            }) {
            Text(stringResource(R.string.get_forecast_text))
        }
    }
}
