package com.temetnosce.japa.presentation.screen.settings.components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun SettingsItem(
    modifier: Modifier = Modifier,
    loading: Boolean = false,
    text: String = "Item Tittle",
    enabled: Boolean = true,
    selected: Boolean = false,
    startIcon: ImageVector? = null,
    animateBackground: Boolean = false,
    onClick: () -> Unit = {},
    endValueContent: (@Composable () -> Unit)? = null,
) {
    Row(
        modifier = modifier
            .clip(RoundedCornerShape(16.dp))
            .fillMaxWidth()
            .defaultMinSize(minHeight = 50.dp)
            .clickable(enabled = enabled) { onClick() }
            .border(
                width = 2.dp,
                shape = RoundedCornerShape(16.dp),
                color = if (selected) MaterialTheme.colorScheme.primary else Color.Transparent,
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Spacer(modifier = Modifier.width(8.dp))
        startIcon?.let {
            Icon(
                modifier = Modifier.padding(horizontal = 8.dp),
                imageVector = it,
                contentDescription = null,
            )
        }
        Text(
            text = text,
            style = MaterialTheme.typography.bodyLarge,
        )
        Spacer(modifier = Modifier.weight(1f))
        endValueContent?.invoke()
    }
}