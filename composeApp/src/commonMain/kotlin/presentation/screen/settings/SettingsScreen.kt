@file:OptIn(ExperimentalMaterial3Api::class)

package presentation.screen.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import japa.composeapp.generated.resources.Res
import japa.composeapp.generated.resources.settings
import org.jetbrains.compose.resources.stringResource

@Composable
internal fun SettingsScreen() {
    Scaffold(
        topBar = {
            Column {
                CenterAlignedTopAppBar(
                    navigationIcon = {
                        IconButton(onClick = {}) {
                            Icon(
                                Icons.AutoMirrored.Rounded.ArrowBack,
                                contentDescription = "ArrowBack",
                                tint = Color.DarkGray,
                                modifier = Modifier.size(48.dp),
                            )
                        }
                    },
                    title = {
                        Text(
                            text = stringResource(Res.string.settings),
                            style = MaterialTheme.typography.headlineMedium,
                        )
                    },
                    windowInsets = WindowInsets(0, 0, 0, 0),
                )
            }
        },
        content = { paddingValues ->
            val scrollState = rememberScrollState()
            Column(modifier = Modifier.verticalScroll(scrollState)) {
                SettingsItem()
            }
        }
    )
}

@Composable
inline fun SettingsItem() {

}