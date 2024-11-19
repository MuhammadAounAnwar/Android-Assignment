package com.android.goally.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.DrawerState
import androidx.compose.material.DrawerValue
import androidx.compose.material.ModalDrawer
import androidx.compose.material.rememberDrawerState
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.android.goally.R
import com.android.goally.ui.copilot.FilterType


@Composable
fun RightSideDrawer(
    heading: String,
    items: Map<String, Int>,
    selectedItem: Map.Entry<String, Int>?,
    onItemSelected: (Map.Entry<String, Int>) -> Unit,
    drawerState: DrawerState = rememberDrawerState(DrawerValue.Closed),
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    val scope = rememberCoroutineScope()

    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
        ModalDrawer(
            drawerState = drawerState,
            drawerContent = {
                CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Ltr) {
                    DrawerContent(heading, items, selectedItem, onItemSelected)
                }
            },
            modifier = modifier,
            content = {
                CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Ltr) {
                    content()
                }
            },
            drawerShape = RoundedCornerShape(topStart = 16.dp, bottomStart = 16.dp),
            drawerContentColor = MaterialTheme.colorScheme.surface,
        )
    }
}

@Composable
fun DrawerContent(
    heading: String,
    items: Map<String, Int>,
    selectedItem: Map.Entry<String, Int>?,
    onItemSelected: (Map.Entry<String, Int>) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.End) {
            Icon(
                painter = painterResource(id = if (heading == FilterType.SCHEDULE.name) R.drawable.fo_leading_icon else R.drawable.fo_folder_icon),
                contentDescription = "Home Icon",
                tint = Color.Black,
                modifier = Modifier
                    .size(30.dp)
            )

            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = heading,
                style = MaterialTheme.typography.headlineSmall,
//                modifier = Modifier.padding(bottom = 16.dp)
            )
        }


        items.forEach { item ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onItemSelected(item) }
                    .padding(vertical = 4.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {
                    RadioButton(
                        selected = item == selectedItem,
                        onClick = { onItemSelected(item) },
                        colors = RadioButtonDefaults.colors(selectedColor = Color(0xFF275A54))
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = item.key)
                }
                Spacer(modifier = Modifier.weight(1f))
                Text(text = item.value.toString())
            }

        }
    }
}

