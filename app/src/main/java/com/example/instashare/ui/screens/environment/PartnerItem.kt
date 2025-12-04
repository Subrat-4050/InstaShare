package com.example.instashare.ui.screens.environment

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedIconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.instashare.data.Partner

@Composable
fun PartnerItem(
    partner: Partner,
    onConfirm: () -> Unit = {},
    onDismiss: () -> Unit = {}
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = partner.name,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold
            )
            Text(text = partner.code)
        }
        Row {
            OutlinedIconButton(
                onClick = onConfirm,
                colors = IconButtonDefaults.outlinedIconButtonColors(containerColor = Color.Transparent, contentColor = Color.Green),
                border = BorderStroke(2.dp, Color.Green)
            ) {
                Icon(Icons.Default.Check, contentDescription = "Reject", tint = Color.Green)
            }
            FilledIconButton(
                onClick = onDismiss,
                colors = IconButtonDefaults.outlinedIconButtonColors(containerColor = Color.Transparent, contentColor = Color.Transparent)
            ) {
                Icon(Icons.Default.Close, contentDescription = "Reject", tint = Color.Red)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PrevPartnerItem() {
    PartnerItem(Partner("John Doe", "ABC-123-abc"))
}