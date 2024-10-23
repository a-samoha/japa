package presentation.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import font

// Set of Material typography styles to start with
@Composable
fun AppTypography(): Typography {
    val spaceMono = FontFamily(
        font(
            name = "SpaceMono Regular",
            res = "font/space_mono_regular.ttf",
        ),
        font(
            name = "SpaceMono Italic",
            res = "font/space_mono_italic.ttf",
            style = FontStyle.Italic
        ),
        font(
            name = "SpaceMono Bold",
            res = "font/space_mono_bold.ttf",
            weight = FontWeight.Bold
        ),
        font(
            name = "SpaceMono Bold Italic",
            res = "font/space_mono_bold_italic.ttf",
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