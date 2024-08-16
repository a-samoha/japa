package presentation.screen.home.composable

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.material.icons.rounded.PlayArrow
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import japa.composeapp.generated.resources.Res
import japa.composeapp.generated.resources.ic_pause
import japa.composeapp.generated.resources.ic_stop
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.painterResource

@Composable
internal fun ButtonsBlock(
    modifier: Modifier = Modifier,
    onSettingsClick: () -> Unit,
    onPlayStopClick: () -> Unit,
    onPauseClick: () -> Unit,
) {

    var isPlaying by remember { mutableStateOf(false) }

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        // region 'Settings' button
        IconButton(
            onClick = onSettingsClick,
            Modifier
                .size(72.dp)
                .border(1.dp, Color.Gray, CircleShape)
                .background(
                    MaterialTheme.colorScheme.primaryContainer,
                    CircleShape
                ),
        ) {
            Icon(
                Icons.Rounded.MoreVert,
                contentDescription = "Settings",
                tint = Color.DarkGray,
                modifier = Modifier.size(48.dp).graphicsLayer(rotationZ = 90f),
            )
        }
        // endregion
        // region 'Play/Stop' button
        RotatingIconButton(
            isPlaying = isPlaying,
            changeIsPlayingState = { isPlaying = !isPlaying },
            onPlayStopClick,
        )
        // endregion
        // region 'Pause' button
        IconButton(
            onClick = {
                isPlaying = false
                onPauseClick()
            },
            Modifier
                .size(72.dp)
                .border(1.dp, Color.Gray, CircleShape)
                .background(
                    MaterialTheme.colorScheme.primaryContainer,
                    CircleShape
                ),
        ) {
            Icon(
                painter = painterResource(Res.drawable.ic_pause), //Icons.Rounded.Delete,
                contentDescription = "Pause",
                tint = Color.DarkGray,
                modifier = Modifier.size(48.dp),
            )
        }
        // endregion
    }
}

@Composable
fun RotatingIconButton(
    isPlaying: Boolean,
    changeIsPlayingState: () -> Unit,
    onPlayStopClick: () -> Unit,
) {

    var rotationState by remember { mutableStateOf(0f) }
    var alphaState by remember { mutableStateOf(1f) }

    val rotation by animateFloatAsState( // 'play/stop' icon rotation animation
        targetValue = rotationState,
        animationSpec = tween(durationMillis = 1000) // animation duration
    )

    val alpha by animateFloatAsState( // Animation for alpha
        targetValue = alphaState,
        animationSpec = tween(durationMillis = 500) // Duration of alpha animation
    )

    // Returns a scope that's cancelled when RotatingIconButton is removed from composition
    val coroutineScope = rememberCoroutineScope()

    IconButton(
        onClick = {
            coroutineScope.launch {
                alphaState = 0.1f
                delay(500)
                changeIsPlayingState()
                alphaState = 1f
            }
            rotationState = if (isPlaying) 0f else 360f
            onPlayStopClick()
        },
        modifier = Modifier
            .size(100.dp)
            .border(1.dp, Color.Gray, CircleShape)
            .background(
                MaterialTheme.colorScheme.primaryContainer,
                CircleShape
            ),
    ) {
        if (isPlaying)
            Icon(
                painter = painterResource(Res.drawable.ic_stop),
                contentDescription = "Play/Stop",
                tint = Color.DarkGray.copy(alpha = alpha),
                modifier = Modifier
                    .size(48.dp)
                    .graphicsLayer(rotationZ = rotation),
            ) else
            Icon(
                imageVector = Icons.Rounded.PlayArrow,
                contentDescription = "Play/Stop",
                tint = Color.DarkGray.copy(alpha = alpha),
                modifier = Modifier
                    .size(72.dp)
                    .graphicsLayer(rotationZ = rotation),
            )
    }
}
