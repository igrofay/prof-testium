package five.head.proftestium.common.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import five.head.proftestium.R

//val fontProximaNova = FontFamily(
//    Font(R.font.gilroy_thin, FontWeight.Thin),
//    Font(R.font.gilroy_ultra_light, FontWeight.ExtraLight),
//    Font(R.font.gilroy_light, FontWeight.Light),
//    Font(R.font.gilroy_regular, FontWeight.Normal),
//    Font(R.font.gilroy_medium, FontWeight.Medium),
//    Font(R.font.gilroy_semibold, FontWeight.SemiBold),
//    Font(R.font.gilroy_bold, FontWeight.Bold),
//    Font(R.font.gilroy_extrabold, FontWeight.ExtraBold),
//    Font(R.font.gilroy_black, FontWeight.Black),
//)

val TypographyDefault = Typography(
    h4 = TextStyle(
        fontWeight = FontWeight.W800,
        fontSize = 30.sp,
    ),
    h6 = TextStyle(
        fontWeight = FontWeight.W900,
        fontSize = 20.sp,
    ),
    subtitle1 = TextStyle(
        fontWeight = FontWeight.W700,
        fontSize = 16.sp,
    ),
    subtitle2 = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
    ),
    caption = TextStyle(
        fontWeight = FontWeight.W700,
        fontSize = 12.sp,
    ),
)