package com.codecamp.newsx.ui.screen.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.codecamp.newsx.R
import com.codecamp.newsx.ui.theme.iceBergBlue

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeTopBar() {
    CenterAlignedTopAppBar(
        modifier = Modifier.border(0.5.dp, Color.Gray),
        title = {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    style = TextStyle(
                        fontSize = MaterialTheme.typography.titleMedium.fontSize,
                        fontWeight = FontWeight.Bold
                    ),
                    text = stringResource(id = R.string.app_name))
                Text(
                    style = TextStyle(
                        fontSize = MaterialTheme.typography.bodyMedium.fontSize
                    ),
                    text = stringResource(id = R.string.app_description))
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = iceBergBlue
        ),
        navigationIcon = {
            Icon(
                modifier = Modifier.padding(horizontal = 12.dp),
                painter = painterResource(id = R.drawable.ic_nav_newsstand),
                contentDescription = ""
            )
        },
        actions = {
            IconButton(onClick = { }) {
                Image(painter = painterResource(id = R.drawable.app_logo), contentDescription = "")
            }
        }
    )
}