package com.codecamp.newsx.ui.screen.paper

import android.content.Intent
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.startActivity
import com.codecamp.newsx.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PaperScreenTopBar(onClick:() -> Unit, onShareClicked: () -> Unit) {
    TopAppBar(
        modifier = Modifier.border(0.5.dp, Color.Gray),
        title = {
            Text(text = "AgresarBharat")
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        navigationIcon = {
            Icon(
                modifier = Modifier.padding(horizontal = 12.dp).clickable {
                    onClick()
                },
                painter = painterResource(id = R.drawable.ic_arrow_back),
                contentDescription = ""
            )
        },
        actions = {
            IconButton(onClick = onShareClicked) {
                Icon(painter = painterResource(id = R.drawable.ic_group), contentDescription = "")
            }
        }
    )
}