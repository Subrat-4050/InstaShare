package com.example.instashare.ui.screens.environment

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.instashare.data.Partner

@Composable
fun PartnersList() {
    LazyColumn {
        items(20) { index ->
            PartnerItem(
                partner = Partner(
                    name = "Partner $index",
                    code = "CODE$index"
                )
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewParentList() {
    PartnersList()
}