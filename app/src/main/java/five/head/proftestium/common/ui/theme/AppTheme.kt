package five.head.proftestium.common.ui.theme

import android.app.Activity
import androidx.compose.material.MaterialTheme
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.core.view.WindowCompat


private val colorsThemeDefault = lightColors(
    primary = primary,
    background = Color.White
)


@Composable
fun AppTheme(
    //    isDark: Boolean = isSystemInDarkTheme(),
    content: @Composable ()-> Unit
){

    val activity = LocalContext.current as? Activity
    SideEffect {
        activity?.window?.let {window->
            WindowCompat
                .getInsetsController(window, window.decorView).isAppearanceLightStatusBars = false // !isDark
        }
    }
    MaterialTheme(
        colors = colorsThemeDefault,
        shapes = ShapesDefault,
        typography = TypographyDefault,
        content = content,
    )
}