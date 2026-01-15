package com.vrsalex.uikit.component.button

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.vrsalex.uikit.R
import com.vrsalex.uikit.component.icon.AppIcon
import com.vrsalex.uikit.theme.AppTheme

@Composable
fun AppCartButton(
    price: Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    text: String = stringResource(R.string.in_cart)
) {

    Button(
        onClick = onClick,
        modifier = modifier.height(56.dp).fillMaxWidth(),
        shape = RoundedCornerShape(10.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = AppTheme.color.accent,
            contentColor = AppTheme.color.white
        ),
        contentPadding = PaddingValues(0.dp)
    ) {
        Row(
            Modifier.fillMaxSize().padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AppIcon(R.drawable.icon_shopping_cart, tint = AppTheme.color.white)
            Spacer(Modifier.width(16.dp))
            Text(
                text = text,
                style = AppTheme.type.title3Semibold
            )
            Spacer(Modifier.weight(1f))
            Text(
                text = "$price ₽",
                style = AppTheme.type.title3Semibold
            )
        }
    }


}