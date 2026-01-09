package com.example.instashare.ui.screens.environment

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.tv.material3.Text
import com.airbnb.lottie.model.content.RoundedCorners

enum class BubbleAlignment {
    LEFT, RIGHT
}

fun Modifier.chatBubble(
    alignment: BubbleAlignment,
    corners: Dp = 16.dp,
    tailwidth: Dp = 8.dp,
    tailheight: Dp = 10.dp
): Modifier = this.then(
    Modifier.drawBehind {
        val w = size.width
        val h = size.height
        val r = corners.toPx()
        val tw = tailwidth.toPx()
        val th = tailheight.toPx()
        val bubbleLeft = if (alignment == BubbleAlignment.LEFT) tw else 0f
        val bubbleRight = size.width - if (alignment == BubbleAlignment.RIGHT) tw else 0f
        val bubbleTop = 0f
        val bubbleBottom = size.height

        val bubblePath = Path().apply {
            addRoundRect(
                RoundRect(
                    left = bubbleLeft,
                    top = bubbleTop,
                    right = bubbleRight,
                    bottom = bubbleBottom,
                    cornerRadius = CornerRadius(r, r)
                )
            )


            if (alignment == BubbleAlignment.LEFT) {
                moveTo(bubbleLeft, bubbleBottom - r)
                lineTo(0f, bubbleBottom - r - th/2.0f)
                lineTo(bubbleLeft, bubbleBottom - r - th)
                close()
            } else {
                moveTo(bubbleRight, bubbleBottom-r)
                lineTo(size.width, bubbleBottom - r - th/2.0f)
                lineTo(bubbleRight, bubbleBottom - r - th)
                close()
            }
        }
        drawPath(
            path = bubblePath,
            color = Color(
                if(alignment == BubbleAlignment.LEFT)
                    0xff3DC0B2
                else
                    0xff34A853
            )
        )
    }
)

@Composable
fun ChatBubble(txt: String) {
    Box(
        modifier = Modifier
            .background(color = Color.Transparent)
            .chatBubble(
                alignment = BubbleAlignment.LEFT,
                corners = 2.dp,
                tailwidth = 6.dp,
                tailheight = 8.dp
            )
            .padding(horizontal = 12.dp, vertical = 3.dp)
    ) {
        Text(
            text = txt,
            modifier = Modifier
        )
    }
}

@Preview
@Composable
private fun PreviewChatBubble() {
    ChatBubble("Hello, this is a chat bubble!")
}
