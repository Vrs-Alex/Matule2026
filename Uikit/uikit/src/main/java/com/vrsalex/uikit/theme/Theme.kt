package com.vrsalex.uikit.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.core.view.WindowCompat

@Immutable
data class AppColor(
    val accent: Color,
    val accentInactive: Color,
    val black: Color,
    val white: Color,
    val error: Color,
    val success: Color,
    val inputBg: Color,
    val inputStroke: Color,
    val placeholder: Color,
    val description: Color,
    val cartStroke: Color
)


@Immutable
data class AppType(
    val title1Semibold: TextStyle,
    val title1ExtraBold: TextStyle,
    val title2Regular: TextStyle,
    val title2Semibold: TextStyle,
    val title2ExtraBold: TextStyle,
    val title3Regular: TextStyle,
    val title3Medium: TextStyle,
    val title3Semibold: TextStyle,
    val headlineRegular: TextStyle,
    val headlineMedium: TextStyle,
    val textRegular: TextStyle,
    val textMedium: TextStyle,
    val captionRegular: TextStyle,
    val captionSemibold: TextStyle,
    val caption2Regular: TextStyle,
    val caption2Bold: TextStyle
)


@Immutable
data class AppSpace(
    val space4: Dp,
    val space8: Dp,
    val space12: Dp,
    val space16: Dp,
    val space20: Dp,
    val space24: Dp,
    val space32: Dp,
    val space40: Dp,
    val space48: Dp,
    val space56: Dp,
    val space64: Dp
)


val LocalAppColorComposition = staticCompositionLocalOf {
    AppColor(
        accent = Color.Unspecified,
        accentInactive = Color.Unspecified,
        black = Color.Unspecified,
        white = Color.Unspecified,
        error = Color.Unspecified,
        success = Color.Unspecified,
        inputBg = Color.Unspecified,
        inputStroke = Color.Unspecified,
        placeholder = Color.Unspecified,
        description = Color.Unspecified,
        cartStroke = Color.Unspecified
    )
}


val LocalAppTypeComposition = staticCompositionLocalOf {
    AppType(
        title1Semibold = TextStyle.Default,
        title1ExtraBold = TextStyle.Default,
        title2Regular = TextStyle.Default,
        title2Semibold = TextStyle.Default,
        title2ExtraBold = TextStyle.Default,
        title3Regular = TextStyle.Default,
        title3Medium = TextStyle.Default,
        title3Semibold = TextStyle.Default,
        headlineRegular = TextStyle.Default,
        headlineMedium = TextStyle.Default,
        textRegular = TextStyle.Default,
        textMedium = TextStyle.Default,
        captionRegular = TextStyle.Default,
        captionSemibold = TextStyle.Default,
        caption2Regular = TextStyle.Default,
        caption2Bold = TextStyle.Default
    )
}


val LocalAppSpaceComposition = staticCompositionLocalOf {
    AppSpace(
        space4 = Dp.Unspecified,
        space8 = Dp.Unspecified,
        space12 = Dp.Unspecified,
        space16 = Dp.Unspecified,
        space20 = Dp.Unspecified,
        space24 = Dp.Unspecified,
        space32 = Dp.Unspecified,
        space40 = Dp.Unspecified,
        space48 = Dp.Unspecified,
        space56 = Dp.Unspecified,
        space64 = Dp.Unspecified
    )
}


@Composable
fun AppMatuleTheme(
    isDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
){

    val colors = AppColor(
        accent = Accent,
        accentInactive = AccentInactive,
        black = Black,
        white = White,
        error = Error,
        success = Success,
        inputBg = InputBg,
        inputStroke = InputStroke,
        placeholder = Placeholder,
        description = Description,
        cartStroke = CartStroke
    )
    
    val types = AppType(
        title1Semibold = title1Base.copy(fontWeight = FontWeight.SemiBold),
        title1ExtraBold = title1Base.copy(fontWeight = FontWeight.ExtraBold),
        title2Regular = title2Base.copy(fontWeight = FontWeight.Normal),
        title2Semibold = title2Base.copy(fontWeight = FontWeight.SemiBold),
        title2ExtraBold = title2Base.copy(fontWeight = FontWeight.ExtraBold),
        title3Regular = title3Base.copy(fontWeight = FontWeight.Normal),
        title3Medium = title3Base.copy(fontWeight = FontWeight.Medium),
        title3Semibold = title3Base.copy(fontWeight = FontWeight.SemiBold),
        headlineRegular = headlineBase.copy(fontWeight = FontWeight.Normal),
        headlineMedium = headlineBase.copy(fontWeight = FontWeight.Medium),
        textRegular = textBase.copy(fontWeight = FontWeight.Normal),
        textMedium = textBase.copy(fontWeight = FontWeight.Medium),
        captionRegular = captionBase.copy(fontWeight = FontWeight.Normal),
        captionSemibold = captionBase.copy(fontWeight = FontWeight.SemiBold),
        caption2Regular = caption2Base.copy(fontWeight = FontWeight.Normal, lineHeight = 16.sp),
        caption2Bold = caption2Base.copy(fontWeight = FontWeight.Bold, lineHeight = 20.sp)
    )
    
    val spaces = AppSpace(
        space4 = 4.dp,
        space8 = 8.dp,
        space12 = 12.dp,
        space16 = 16.dp,
        space20 = 20.dp,
        space24 = 24.dp,
        space32 = 32.dp,
        space40 = 40.dp,
        space48 = 48.dp,
        space56 = 56.dp,
        space64 = 64.dp
    )

    CompositionLocalProvider(
        LocalAppColorComposition provides colors,
        LocalAppTypeComposition provides types,
        LocalAppSpaceComposition provides spaces,
        content = content
    )
}


private val title1Base = TextStyle(
    fontFamily = robotoFont,
    fontSize = 24.sp,
    lineHeight = 28.sp,
    letterSpacing = 0.0033.em
)

private val title2Base = TextStyle(
    fontFamily = robotoFont,
    fontSize = 20.sp,
    lineHeight = 28.sp,
    letterSpacing = 0.0038.em
)

private val title3Base = TextStyle(
    fontFamily = robotoFont,
    fontSize = 17.sp,
    lineHeight = 24.sp,
    letterSpacing = 0.em
)

private val headlineBase = TextStyle(
    fontFamily = robotoFont,
    fontSize = 16.sp,
    lineHeight = 20.sp,
    letterSpacing = -(0.0033).em
)

private val textBase = TextStyle(
    fontFamily = robotoFont,
    fontSize = 15.sp,
    lineHeight = 20.sp,
    letterSpacing = 0.em
)

private val captionBase = TextStyle(
    fontFamily = robotoFont,
    fontSize = 14.sp,
    lineHeight = 20.sp,
    letterSpacing = 0.em
)

private val caption2Base = TextStyle(
    fontFamily = robotoFont,
    fontSize = 12.sp,
    letterSpacing = 0.em
)




