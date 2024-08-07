package presentation.screen.home.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.PlayArrow
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import japa.composeapp.generated.resources.Res
import japa.composeapp.generated.resources.ic_pause
import org.jetbrains.compose.resources.painterResource

@Composable
internal fun ButtonsBlock(modifier: Modifier = Modifier) {
    fun onSettingsClick() {}
    fun onPlayStopClick() {}
    fun onPauseClick() {}

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            onClick = ::onSettingsClick,
            Modifier
                .size(72.dp)
                .border(1.dp, Color.Gray, CircleShape)
                .background(
                    MaterialTheme.colorScheme.primaryContainer,
                    CircleShape
                )
        ) {
            Icon(
                Icons.Rounded.Settings,
                contentDescription = "Favorite",
                tint = Color.Red,
                modifier = Modifier.size(48.dp)
            )
        }
        IconButton(
            onClick = ::onPlayStopClick,
            Modifier
                .size(100.dp)
                .border(1.dp, Color.Gray, CircleShape)
                .background(
                    MaterialTheme.colorScheme.primaryContainer,
                    CircleShape
                )
        ) {
            Icon(
                Icons.Rounded.PlayArrow,
                contentDescription = "Favorite",
                tint = Color.Red,
                modifier = Modifier.size(72.dp)
            )
        }
        IconButton(
            onClick = ::onPauseClick,
            Modifier
                .size(72.dp)
                .border(1.dp, Color.Gray, CircleShape)
                .background(
                    MaterialTheme.colorScheme.primaryContainer,
                    CircleShape
                )
        ) {
            Icon(
                painter = painterResource(Res.drawable.ic_pause),
                contentDescription = "Pause",
                tint = Color.Red,
                modifier = Modifier.size(48.dp)
            )
        }
    }
}