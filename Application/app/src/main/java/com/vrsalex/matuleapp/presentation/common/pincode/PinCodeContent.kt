package com.vrsalex.matuleapp.presentation.common.pincode

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.vrsalex.matuleapp.R
import com.vrsalex.matuleapp.presentation.common.BaseColumn
import com.vrsalex.uikit.theme.AppTheme

@Composable
fun PinCodeContent(
    state: PinCodeContract.State,
    event: (e: PinCodeContract.Event) -> Unit
) {
    val scrollState = rememberScrollState()
    BaseColumn(
        Modifier
            .fillMaxSize()
            .verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = state.title,
            style = AppTheme.type.title1ExtraBold
        )
        Spacer(Modifier.height(16.dp))
        Text(
            text = state.subtitle,
            style = AppTheme.type.textRegular,
            color = AppTheme.color.caption
        )
        Spacer(Modifier.height(56.dp))

        PinCodeField(state.pinCode, state.codeLength, state.error)

        Spacer(Modifier.height(60.dp))

        PinCodeKeyboard(
            onNumberClick = { event(PinCodeContract.Event.OnNumberClick(it)) },
            onDeleteClick = { event(PinCodeContract.Event.OnDeleteClick) }
        )

    }

}


@Composable
private fun PinCodeField(pinCode: String, codeLength: Int, error: Boolean) {
    val offsetX = remember { Animatable(0f) }
    LaunchedEffect(error) {
        if (error) {
            offsetX.animateTo(0f,
                animationSpec = keyframes {
                    durationMillis = 500
                    -10f at 50
                    10f at 100
                    -10f at 150
                    10f at 200
                    -5f at 300
                    5f at 400
                }
            )
        }
    }
    Row(
        Modifier.offset(x = offsetX.value.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        repeat(codeLength){ index ->

            val background by animateColorAsState(
                if (pinCode.getOrNull(index) != null) AppTheme.color.accent
                else Color.Transparent
            )

            Box(
                Modifier
                    .size(16.dp)
                    .clip(CircleShape)
                    .border(1.dp, AppTheme.color.accent, CircleShape)
                    .background(background)
            )
        }
    }
}


@Composable
private fun PinCodeKeyboard(
    onNumberClick: (number: String) -> Unit,
    onDeleteClick: () -> Unit
) {
    val items = listOf(
        listOf("1", "2", "3"),
        listOf("4", "5", "6"),
        listOf("7", "8", "9"),
        listOf("", "0", "del")
    )
    Column(verticalArrangement = Arrangement.spacedBy(24.dp)) {
        items.forEach { row ->
            Row(horizontalArrangement = Arrangement.spacedBy(24.dp)) {
                row.forEach { key ->
                    when(key){
                        "" -> Box(Modifier.size(80.dp))
                        "del" -> {
                            Box(
                                modifier = Modifier
                                    .size(80.dp)
                                    .clip(CircleShape)
                                    .clickable(
                                        interactionSource = null,
                                        indication = ripple(),
                                        onClick = onDeleteClick
                                    ),
                                contentAlignment = Alignment.Center
                            ) {
                                Image(
                                    painter = painterResource(R.drawable.del_icon),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .height(24.dp)
                                        .width(35.dp)
                                )
                            }
                        }
                        else -> {
                            PinKey(key, onNumberClick)
                        }
                    }
                }
            }
        }

    }
}


@Composable
fun PinKey(
    value: String,
    onClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var isPressed by remember { mutableStateOf(false) }

    val backgroundColor by animateColorAsState(
        targetValue = if (isPressed) Color(0xFF166FF1) else Color(0xFFF7F7F9),
        label = "backgroundColor"
    )
    val contentColor = if (isPressed) Color.White else Color.Black

    Box(
        modifier = modifier
            .size(80.dp)
            .clip(CircleShape)
            .background(backgroundColor)
            .pointerInput(Unit) {
                detectTapGestures(
                    onPress = {
                        isPressed = true // Меняем цвет сразу
                        try {
                            awaitRelease() // Ждем, пока отпустишь
                        } finally {
                            isPressed = false // Возвращаем обратно
                        }
                    },
                    onTap = {
                        onClick(value)
                        println("Click!")
                    }
                )
            },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = value,
            style = AppTheme.type.title1Semibold,
            color = contentColor
        )
    }
}