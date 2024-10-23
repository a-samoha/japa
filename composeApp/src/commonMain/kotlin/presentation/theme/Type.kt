package presentation.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import japa.composeapp.generated.resources.Res
import japa.composeapp.generated.resources.space_mono_bold
import japa.composeapp.generated.resources.space_mono_bold_italic
import japa.composeapp.generated.resources.space_mono_italic
import japa.composeapp.generated.resources.space_mono_regular
import org.jetbrains.compose.resources.Font

// Set of Material typography styles to start with
@Composable
fun AppTypography(): Typography {
    val spaceMono = FontFamily(
        Font(
            Res.font.space_mono_regular,
        ),
        Font(
            Res.font.space_mono_bold,
            weight = FontWeight.Bold
        ),
        Font(
            Res.font.space_mono_italic,
            style = FontStyle.Italic
        ),
        Font(
            Res.font.space_mono_bold_italic,
            weight = FontWeight.Bold,
            style = FontStyle.Italic
        ),
    )

    return Typography(
        bodySmall = TextStyle(
            fontFamily = spaceMono,
            fontWeight = FontWeight.Light,
            fontSize = 12.sp,
        ),
        bodyMedium = TextStyle(
            fontFamily = spaceMono,
            fontWeight = FontWeight.Normal,
            fontSize = 14.sp
        ),
        bodyLarge = TextStyle(
            fontFamily = spaceMono,
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp,
            lineHeight = 24.sp,
            letterSpacing = 0.5.sp
        ),
        labelMedium = TextStyle(
            fontFamily = spaceMono,
            fontWeight = FontWeight.Normal,
        ),
        headlineMedium = TextStyle(
            fontFamily = spaceMono,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp
        )
    )
}