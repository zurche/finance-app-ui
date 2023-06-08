package com.az.financeapp.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.az.financeapp.R

private val mockNotifications = listOf(
    "You have 3 recurring charges that are due at the beginning of next month.",
    "There are 5 subscription payments upcoming next week.",
    "Your budget for restaurants is exceeded by $134."
)

@Composable
@Preview
fun NotificationCarousel(notifications: List<String> = mockNotifications) {

    CarouselItem(text = notifications[0], icon = R.drawable.credit_card_outline)

}

@Composable
@Preview
private fun CarouselItem(
    text: String = "",
    icon: Int = R.drawable.credit_card_outline
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(9.dp)
            .clip(RoundedCornerShape(20.dp))
    )
    {
        Row(
            modifier = Modifier
                .background(Color(0xFFBDAFAF))
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp),
            verticalAlignment = CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Text(
                text = mockNotifications[0], modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(0.80f)
            )


            InnerShadowRoundIcon(icon)
        }
    }
}

@Composable
@Preview
private fun InnerShadowRoundIcon(icon: Int = R.drawable.credit_card_outline) {
    BoxWithConstraints(
        modifier = Modifier
            .size(30.dp)
            .clip(CircleShape)
            .background(Color(0xFFBDAFAF))
            .drawWithContent {

                // Draw the inner shadow
                drawCircle(
                    color = Color.Black,
                    center = Offset(size.width / 2, size.height / 2),
                    radius = size.width / 2,
                    style = Stroke(4.dp.toPx())
                )

                drawContent()
            }
    ) {
        Icon(
            painter = painterResource(id = icon),
            contentDescription = "Icon",
            tint = Color.Black,
            modifier = Modifier
                .padding(9.dp)
                .align(Alignment.Center)
        )
    }
}

