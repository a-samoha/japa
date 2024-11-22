@file:OptIn(ExperimentalMaterial3Api::class)

package com.temetnosce.japa.presentation.screen.settings

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.twotone.Build
import androidx.compose.material.icons.twotone.Check
import androidx.compose.material.icons.twotone.Star
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.temetnosce.japa.LocalNavController
import com.temetnosce.japa.LocalizationProvider
import com.temetnosce.japa.domain.entity.Language.English
import com.temetnosce.japa.domain.entity.Language.Russian
import com.temetnosce.japa.domain.entity.Language.Ukrainian
import com.temetnosce.japa.presentation.screen.settings.components.SettingsItem
import japa.composeapp.generated.resources.Res
import japa.composeapp.generated.resources.darkTheme
import japa.composeapp.generated.resources.dscArrowBack
import japa.composeapp.generated.resources.language
import japa.composeapp.generated.resources.settings
import japa.composeapp.generated.resources.systemDarkTheme
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.koinInject
import org.koin.compose.viewmodel.koinViewModel

@Composable
internal fun SettingsScreen(
    viewModel: SettingsViewModel = koinViewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val navController = LocalNavController.current
    SettingsContent(
        state = state,
        processIntent = viewModel::processIntent,
        onBackPressed = { navController.popBackStack() },
    )
}

@Composable
private fun SettingsContent(
    state: SettingsState,
    processIntent: (SettingsIntent) -> Unit = {},
    onBackPressed: () -> Unit = {},
) {
   println("test SettingsContent ${state.settings.language}")
    Scaffold(
        topBar = {
            Column {
                CenterAlignedTopAppBar(
                    navigationIcon = {
                        IconButton(onClick = onBackPressed) {
                            Icon(
                                Icons.AutoMirrored.Rounded.ArrowBack,
                                contentDescription = stringResource(Res.string.dscArrowBack),
                                tint = Color.DarkGray,
                                modifier = Modifier.size(48.dp).padding(8.dp),
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

            Column(modifier = Modifier.padding(paddingValues).verticalScroll(scrollState)) {

                val localizationProvider: LocalizationProvider = koinInject()
                var expanded by remember { mutableStateOf(false) }
                val languages = listOf(English.uiName, Ukrainian.uiName, Russian.uiName)

                SettingsItem(
                    text = stringResource(Res.string.language),
                    startIcon = Icons.TwoTone.Build,
                    onClick = { expanded = true },
                    endValueContent = {
                        Box(
                            modifier = Modifier
                                .padding(horizontal = 8.dp)
                                .wrapContentSize(Alignment.TopEnd)
                        ) {
                            Text(
                                text = state.settings.language.uiName,
                                modifier = Modifier
                                    .padding(8.dp)
                                    .clickable { expanded = true },
                                style = MaterialTheme.typography.bodyLarge,
                            )
                            DropdownMenu(
                                expanded = expanded,
                                onDismissRequest = { expanded = false }
                            ) {
                                languages.forEach { language ->
                                    DropdownMenuItem(
                                        text = { Text(language) },
                                        onClick = {
                                            expanded = false
                                            val selected = when (language) {
                                                English.uiName -> English
                                                Ukrainian.uiName -> Ukrainian
                                                Russian.uiName -> Russian
                                                else -> English
                                            }
                                            localizationProvider.changeLang(selected)
                                            processIntent(SettingsIntent.UserLanguage(selected))
                                        }
                                    )
                                }
                            }
                        }
                    },
                )

                SettingsItem(
                    text = stringResource(Res.string.systemDarkTheme),
                    startIcon = Icons.TwoTone.Star,
                    onClick = {
                        processIntent(SettingsIntent.UpdateFlag.SystemDarkTheme(!state.settings.designUseSystemDarkTheme))
                    },
                    endValueContent = {
                        Switch(
                            modifier = Modifier.padding(horizontal = 8.dp),
                            checked = state.settings.designUseSystemDarkTheme,
                            onCheckedChange = {
                                processIntent(SettingsIntent.UpdateFlag.SystemDarkTheme(it))
                            },
                        )
                    },
                )

                SettingsItem(
                    text = stringResource(Res.string.darkTheme),
                    startIcon = Icons.TwoTone.Check,
                    onClick = {
                        processIntent(SettingsIntent.UpdateFlag.DarkTheme(!state.settings.designDarkTheme))
                    },
                    endValueContent = {
                        Switch(
                            modifier = Modifier.padding(horizontal = 8.dp),
                            checked = state.settings.designDarkTheme,
                            onCheckedChange = {
                                processIntent(SettingsIntent.UpdateFlag.DarkTheme(it))
                            },
                        )
                    },
                )
            }
        }
    )
}
