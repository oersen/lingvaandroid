package dev.atajan.lingva_android.ui.components

import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun NotificationDialog(shouldShowDialog: MutableState<Boolean>) {

    if (shouldShowDialog.value) {
        AlertDialog(
            backgroundColor = MaterialTheme.colorScheme.secondary,
            contentColor = MaterialTheme.colorScheme.onSecondary,
            onDismissRequest = {
                // Dismiss the dialog when the user clicks outside the dialog or on the back
                // button. If you want to disable that functionality, simply use an empty
                // onCloseRequest.
                shouldShowDialog.value = false
            },
            title = {
                Text(text = "Uh oh :(", style = MaterialTheme.typography.headlineLarge)
            },
            text = {
                Text(
                    "Something went wrong. Please try again later."
                )
            },
            confirmButton = {
                // this component is meant only for a passive notification. No positive action should be here.
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        shouldShowDialog.value = false
                    }
                ) {
                    Text(
                        text = "Got it",
                        color = MaterialTheme.colorScheme.onSecondary,
                        style = MaterialTheme.typography.labelLarge
                    )
                }
            }
        )
    }
}

@Preview
@Composable
fun PreviewNotificationDialog() {
    val state = remember{ mutableStateOf(true) }
    NotificationDialog(state)
}