package com.codecamp.newsx.ui.screen.article

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.codecamp.newsx.R
import com.codecamp.newsx.data.local.ArticleEntity
import de.charlex.compose.material3.HtmlText


@Composable
fun ArticleItem(onClicked: (Int) -> Unit, article: ArticleEntity) {
    Column {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(12.dp)
                .clickable {
                    onClicked(article.id)
                }
        ) {
            Card {
                AsyncImage(
                    model = article.image,
                    modifier = Modifier.size(height = 120.dp, width = 160.dp),
                    contentScale = ContentScale.Crop,
                    placeholder = painterResource(id = R.drawable.img),
                    contentDescription = "")
            }
            Spacer(modifier = Modifier.size(10.dp))
            Column(verticalArrangement = Arrangement.SpaceAround) {
                HtmlText(
                    modifier = Modifier.padding(vertical = 6.dp),
                    maxLines = 3,
                    fontWeight = FontWeight.Medium,
                    color = Color.Black,
                    fontSize = 17.sp,
                    text = article.title)
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_dot), contentDescription = "",
                        tint = MaterialTheme.colorScheme.primaryContainer
                    )
                    Spacer(modifier = Modifier.size(6.dp))
                    Text(text = article.date, maxLines = 1)
                }
                Spacer(modifier = Modifier.size(10.dp))
            }
        }
    }
}