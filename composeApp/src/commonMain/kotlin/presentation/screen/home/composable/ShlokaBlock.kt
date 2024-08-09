package presentation.screen.home.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import japa.composeapp.generated.resources.Res
import japa.composeapp.generated.resources.shloka
import japa.composeapp.generated.resources.synonyms
import japa.composeapp.generated.resources.translation
import org.jetbrains.compose.resources.stringResource
import presentation.screen.home.model.ShlokaModel

@Composable
internal fun ShlokaBlock(modifier: Modifier = Modifier) {
    // Создаём состояние для управления видимостью блоков
    var isShlokaVisible by remember { mutableStateOf(true) }
    var isSynonymsVisible by remember { mutableStateOf(true) }
    var isTranslationVisible by remember { mutableStateOf(true) }

    val shlokaModel = ShlokaModel()

    Column(modifier = modifier.padding(top = 16.dp, bottom = 16.dp, end = 16.dp)) {
        // Заголовок
        Text(
            text = shlokaModel.title,
            style = MaterialTheme.typography.titleLarge,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth().padding(start = 48.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Прокручиваемая область
        LazyColumn {
            item {
                // Первый блок: Шлока
                ShlokaSection(
                    visible = isShlokaVisible,
                    onToggleVisibility = { isShlokaVisible = !isShlokaVisible },
                    title = stringResource(Res.string.shloka),
                    content = shlokaModel.shloka,
                    modifier = Modifier.align(Alignment.Start)
                )

                Spacer(modifier = Modifier.height(8.dp))
            }
            item {
                // Второй блок: Синонимы
                ShlokaSection(
                    visible = isSynonymsVisible,
                    onToggleVisibility = { isSynonymsVisible = !isSynonymsVisible },
                    title = stringResource(Res.string.synonyms),
                    content = shlokaModel.synonyms,
                    modifier = Modifier.align(Alignment.Start)
                )

                Spacer(modifier = Modifier.height(8.dp))
            }
            item {
                // Третий блок: Перевод
                ShlokaSection(
                    visible = isTranslationVisible,
                    onToggleVisibility = { isTranslationVisible = !isTranslationVisible },
                    title = stringResource(Res.string.translation),
                    content = shlokaModel.translation,
                    modifier = Modifier.align(Alignment.Start)
                )
            }
        }
    }
}

@Composable
fun ShlokaSection(
    visible: Boolean,
    onToggleVisibility: () -> Unit,
    title: String?,
    content: String,
    modifier: Modifier = Modifier
) {
    Row(modifier = modifier) {
        // Кнопка для переключения видимости
        IconButton(
            modifier = Modifier
                .padding(start = 16.dp, top = 8.dp, end = 8.dp, bottom = 8.dp)
                .border(1.dp, Color.Gray, CircleShape)
                .background(
                    MaterialTheme.colorScheme.primaryContainer,
                    CircleShape
                ).size(24.dp),
            onClick = onToggleVisibility,
        ) {
            Icon(
                imageVector = if (visible) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                contentDescription = null
            )
        }

        // Отображаем содержимое только если оно видимо
        Column {
            title?.let {
                Text(
                    text = title,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                )
            }
            if (visible) {
                Text(
                    text = content,
                    style = MaterialTheme.typography.bodyMedium,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}
