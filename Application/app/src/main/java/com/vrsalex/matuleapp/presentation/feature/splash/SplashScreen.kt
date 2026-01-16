package com.vrsalex.matuleapp.presentation.feature.splash

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vrsalex.matuleapp.R
import com.vrsalex.uikit.theme.AppTheme

@Composable
fun SplashScreen() {

    Box(
        Modifier.fillMaxSize()
            .background(Color.Black),
        contentAlignment = Alignment.Center
    ){

        Box(
            Modifier
                .matchParentSize()
                .background(
                    Brush.linearGradient(
                        0f to Color(0xFF74C8E4),
                        0.51f to Color(0xFF73D5BC),
                        1f to Color(0xFF74C8E4)
                    )
                )
        )

        Box(
            Modifier
                .matchParentSize()
                .background(
                    Brush.verticalGradient(
                        0f to Color(0xFF6269F0).copy(alpha = 0.05f),
                        0.29f to Color(0xFF3740F5).copy(alpha = 0.65f),
                        0.5f to Color(0xFF2254F5),
                        0.71f to Color(0xFF3740F5).copy(alpha = 0.65f),
                        1f to Color(0xFF6269F0).copy(alpha = 0.05f)
                    )
                )
        )


        Box(
            Modifier
                .align(Alignment.TopStart)
                .size(453.dp)
                .offset((-127).dp, -(250.dp))
                .clip(CircleShape)
                .alpha(0.6f)
                .background(
                    Brush.radialGradient(
                        colors = listOf(
                            Color(0xFF83A0F8),
                            Color(0xFF83A0F8).copy(alpha = 0.5f),
                            Color(0xFF83A0F8).copy(alpha = 0f)
                        )
                    )
                )
        )

        Box(
            Modifier
                .align(Alignment.BottomEnd)
                .size(453.dp)
                .offset(248.dp, 248.dp)
                .clip(CircleShape)
                .alpha(0.6f)
                .background(
                    Brush.radialGradient(
                        colors = listOf(
                            Color(0xFF83A0F8),
                            Color(0xFF83A0F8).copy(alpha = 0.5f),
                            Color(0xFF83A0F8).copy(alpha = 0f)
                        )
                    )
                )
        )




        Text(
            text = stringResource(R.string.name),
            style = AppTheme.type.textRegular.copy(
                fontSize = 40.sp,
                lineHeight = 64.sp,
                letterSpacing = 1.04.sp
            ),
            color = Color.White
        )

    }

}


@Preview(showBackground = true)
@Composable
private fun SplashPreview(){
    SplashScreen()
}