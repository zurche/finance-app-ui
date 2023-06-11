package com.az.financeui.composables

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Devices.PIXEL_4
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import com.az.financeui.R
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
fun FinanceCardVerticalPager(notifications: List<NotificationUIModel> = mockNotifications) {
    val pagerState = rememberPagerState()

    Row(modifier = Modifier.height(110.dp)) {
        VerticalPager(
            modifier = Modifier
                .height(110.dp)
                .padding(16.dp)
                .fillMaxWidth(0.9f),
            pageCount = notifications.size,
            state = pagerState,
            pageSize = PageSize.Fixed(75.dp)
        ) { page ->

            FinanceNotificationCard(
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

        Column(
            modifier = Modifier
                .height(150.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
        ) {
            repeat(notifications.size) { iteration ->
                val transition =
                    updateTransition(targetState = pagerState.currentPage == iteration, label = "")

                val color by transition.animateColor(label = "") { isSelected ->
                    if (isSelected) notifications[pagerState.currentPage].color else Color.LightGray
                }

                Box(
                    modifier = Modifier
                        .padding(2.dp)
                        .clip(CircleShape)
                        .background(color)
                        .size(10.dp)

                )
            }
        }
    }


}

@Composable
@Preview(device = PIXEL_4)
private fun FinanceNotificationCard(
    modifier: Modifier = Modifier,
    uiNotification: NotificationUIModel = mockNotifications[0]
) {
    Card(modifier = modifier,
        elevation = CardDefaults.cardElevation(
            defaultElevation = 7.dp
        )) {
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
                    .padding(start = 16.dp, top = 16.dp, bottom = 16.dp)
                    .fillMaxWidth(0.85f)
            )

            Icon(
                painter = painterResource(id = uiNotification.icon),
                contentDescription = "Icon",
                tint = Color.Black,
                modifier = Modifier
                    .padding(9.dp)
                    .size(24.dp)
            )
        }
    }
}

