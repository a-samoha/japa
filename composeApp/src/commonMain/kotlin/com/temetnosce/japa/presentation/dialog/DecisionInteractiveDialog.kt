package com.temetnosce.japa.presentation.dialog

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.AlertDialogDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import japa.composeapp.generated.resources.Res
import japa.composeapp.generated.resources.cancel
import japa.composeapp.generated.resources.ok
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun DecisionInteractiveDialog(
    title: String,
    text: String,
    confirmActionResId: StringResource = Res.string.ok,
    dismissActionResId: StringResource = Res.string.cancel,
    onConfirmAction: () -> Unit = {},
    onDismissRequest: () -> Unit = {},
    content: @Composable () -> Unit = {},
) {
    AlertDialog(
        shape = RoundedCornerShape(24.dp),
        containerColor = MaterialTheme.colorScheme.background,
        onDismissRequest = onDismissRequest,
        confirmButton = {
            TextButton(onClick = onConfirmAction) {
                Text(text = stringResource(confirmActionResId))
            }
        },
        dismissButton = {
            TextButton(onClick = onDismissRequest) {
                Text(text = stringResource(dismissActionResId))
            }
        },
        title = {
            Text(
                text = title,
                fontSize = 18.sp,
                color = AlertDialogDefaults.titleContentColor,
            )
        },
        text = {
            Text(
                text = text,
                fontSize = 14.sp,
                color = AlertDialogDefaults.textContentColor,
            )
            content()
        }
    )
}

