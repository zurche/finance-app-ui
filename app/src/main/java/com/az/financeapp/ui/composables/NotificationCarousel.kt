package com.az.financeapp.ui.composables

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Devices.PIXEL_4
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import com.az.financeapp.R
import kotlin.math.absoluteValue

data class NotificationUIModel(
    val text: String,
    val icon: Int,
    val color: Color
)

private val mockNotifications: List<NotificationUIModel> = listOf(
    NotificationUIModel(
        "You have 3 recurring charges that are due at the beginning of next month.",
        R.drawable.credit_card_outline,
        Color(0xFFe1e1fd)
    ),
    NotificationUIModel(
        "There are 5 subscription payments upcoming next week.",
        R.drawable.recurring_payment_icon,
        Color(0xFFf9dac5)
    ),
    NotificationUIModel(
        "Your budget for restaurants is exceeded by \$134.",
        R.drawable.exceed_budget,
        Color(0xFFc4eceb)
    )
)

@OptIn(ExperimentalFoundationApi::class)
@Composable
@Preview(device = PIXEL_4)
fun NotificationCarousel(notifications: List<NotificationUIModel> = mockNotifications) {
    val pagerState = rememberPagerState()

    VerticalPager(
        modifier = Modifier.height(120.dp).padding(16.dp),
        pageCount = notifications.size,
        state = pagerState,
        pageSize = PageSize.Fixed(70.dp)
    ) { page ->

        CarouselItem(
            modifier = Modifier
                .graphicsLayer {
                    val pageOffset = (
                            (pagerState.currentPage - page) + pagerState
                                .currentPageOffsetFraction
                            ).absoluteValue

                    alpha = lerp(
                        start = 0.7f,
                        stop = 1f,
                        fraction = 1f - pageOffset.coerceIn(0f, 1f)
                    )
                },
            uiNotification = notifications[page]
        )
    }

}

@Composable
@Preview(device = PIXEL_4)
private fun CarouselItem(
    modifier: Modifier = Modifier,
    uiNotification: NotificationUIModel = mockNotifications[0]
) {
    Card(modifier = modifier) {
        Row(
            modifier = Modifier
                .background(uiNotification.color)
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp),
            verticalAlignment = CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Text(
                text = uiNotification.text,
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(0.80f)
            )


            RoundIcon(uiNotification.icon)
        }
    }
}

@Composable
@Preview
private fun RoundIcon(icon: Int = R.drawable.credit_card_outline) {
    BoxWithConstraints(
        modifier = Modifier
            .size(30.dp)
            .clip(CircleShape)
            .drawBehind {
                val circleSize = size.width

                // Draw the inner shadow
                drawCircle(
                    color = Color.DarkGray,
                    center = Offset(circleSize / 2, circleSize / 2),
                    radius = circleSize / 2 - 1.dp.toPx(),
                    style = Stroke(1.dp.toPx())
                )
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


//TODO: Add shadow to inner circle
//TODO: Add animation to carousel

