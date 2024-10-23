import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import com.temetnosce.japa.R

@Composable
actual fun font(
    name: String,
    res: String,
    weight: FontWeight,
    style: FontStyle
): Font {
    val context = LocalContext.current
    //val id = context.resources.getIdentifier(res, "font", context.packageName)
    val id = when (res) {
        "font/space_mono_regular.ttf" -> R.font.space_mono_regular
        "font/space_mono_bold.ttf" -> R.font.space_mono_bold
        "font/space_mono_italic.ttf" -> R.font.space_mono_italic
        "font/space_mono_bold_italic.ttf" -> R.font.space_mono_bold_italic
        else -> R.font.space_mono_regular
    }
    return Font(id, weight, style)
}