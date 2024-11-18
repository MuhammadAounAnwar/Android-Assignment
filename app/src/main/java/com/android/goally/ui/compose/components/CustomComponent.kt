package com.android.goally.ui.compose.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.android.goally.R

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun FilterOption(
    leadingIconResId: Int = R.drawable.fo_leading_icon,
    trailingIconResId: Int = R.drawable.fo_trailing_icon,
    text: String = "Heading",
    onClick: () -> Unit = {}
) {
    Row(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .clickable { onClick.invoke() },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.End
    ) {

        Icon(
            painter = painterResource(id = leadingIconResId),
            contentDescription = null,
            modifier = Modifier.size(24.dp)
        )

        Spacer(modifier = Modifier.width(8.dp))

        Text(
            text = text,
            style = MaterialTheme.typography.bodyMedium
        )

        Icon(
            painter = painterResource(id = trailingIconResId),
            contentDescription = null,
            modifier = Modifier.size(24.dp)
        )
    }
}

@Composable
fun FilterTagComponent(onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick.invoke() },
        horizontalArrangement = Arrangement.spacedBy(4.dp, Alignment.End)
    ) {
        Text(text = "Filtered")
        Icon(
            imageVector = Icons.Default.Close, // Replace with your desired icon
            contentDescription = "Filter Icon",
            modifier = Modifier.size(20.dp), // Adjust size if needed
            tint = MaterialTheme.colorScheme.error // Adjust tint if needed
        )
    }
}