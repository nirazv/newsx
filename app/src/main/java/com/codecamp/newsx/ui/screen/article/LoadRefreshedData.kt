package com.codecamp.newsx.ui.screen.article

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.codecamp.newsx.R

@Composable
fun LoadRefreshedData(modifier: Modifier, isVisible:Boolean, onClick: () -> Unit) {
    AnimatedVisibility(
        visible = isVisible,
        enter = slideInVertically(
            initialOffsetY = { -it }
        ),
        exit = fadeOut()
    ) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onClick()
            }
            .background(MaterialTheme.colorScheme.primaryContainer)
            .padding(vertical = 12.dp)
            , verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center) {
            Icon(painter = painterResource(id = R.drawable.ic_refresh), contentDescription = "", modifier = Modifier.size(20.dp))
            Spacer(modifier = Modifier.size(6.dp))
            Text(text = "Load Refreshed Data", fontWeight = FontWeight.SemiBold)
            Spacer(modifier = Modifier.size(6.dp))
        }
    }
}