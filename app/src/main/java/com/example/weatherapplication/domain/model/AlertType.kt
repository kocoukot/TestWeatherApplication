package com.example.weatherapplication.domain.model

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

abstract class AlertType {

    @Composable
    abstract fun ShowError(onCloseError: () -> Unit)

    object EmptyAlert : AlertType() {
        @Composable
        override fun ShowError(onCloseError: () -> Unit) {
        }
    }


    class TextAlert(private val alertText: String) : AlertType() {
        @Composable
        override fun ShowError(onCloseError: () -> Unit) {
            AlertDialog(onDismissRequest = { onCloseError.invoke() },
                confirmButton = {
                    Button(onClick = { onCloseError.invoke() }) {
                        Text("OK")
                    }
                }, text = {
                    Text(alertText)
                })

        }

    }
}