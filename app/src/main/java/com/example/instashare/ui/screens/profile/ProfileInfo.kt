package com.example.instashare.ui.screens.profile

import android.view.View
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.VerticalAlignmentLine
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.tv.material3.Icon
import androidx.tv.material3.Text

@Composable
fun ProfileInfo(icon: ImageVector = Icons.Default.Person, heading: String = "Name", about: String = "developer") {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            imageVector = icon,
            contentDescription = "Profile Icon"
        )

        Spacer(
            modifier = Modifier.width(14.dp)
        )

        Column {
            Text(text = heading, fontWeight = FontWeight.Bold, fontSize = 18.sp)
            Text(text = about)
        }
    }
    HorizontalDivider(
        modifier = Modifier.padding(horizontal = 12.dp),
        thickness = 1.dp,
        color = Color.LightGray
    )

}

@Preview(showBackground = true)
@Composable
private fun PreviewProfileInfo() {
    ProfileInfo()
}