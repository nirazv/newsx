package com.codecamp.newsx.ui.screen.paper

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.codecamp.newsx.R
import com.codecamp.newsx.data.local.ArticleEntity
import com.codecamp.newsx.data.utils.TaskLoadState
import com.codecamp.newsx.ui.theme.iceBergBlue
import de.charlex.compose.material3.HtmlText

@Composable
fun PaperContent(
    selectedTask: State<TaskLoadState<ArticleEntity>>,
    paddingValues: PaddingValues,
    onShareClick: (String, String) -> Unit
) {
    LazyColumn(modifier = Modifier.padding(paddingValues)) {

        if (selectedTask.value is TaskLoadState.Loading) {
            item {
                Box(
                    modifier = Modifier.fillParentMaxHeight(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(240.dp)
                    )
                }
            }
            return@LazyColumn
        }

        if (selectedTask.value is TaskLoadState.Success) {
            val article = (selectedTask.value as TaskLoadState.Success<ArticleEntity>).data

            item {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .crossfade(true)
                        .placeholder(R.drawable.img)
                        .data(article.image)
                        .build(),
                    contentDescription = "",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .height(260.dp)
                        .fillMaxWidth()
                )
            }

            item {
                HtmlText(
                    modifier = Modifier
                        .padding(vertical = 14.dp, horizontal = 14.dp),
                    text = article.title,
                    style = TextStyle(
                        fontSize = MaterialTheme.typography.titleSmall.fontSize
                    ),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Medium
                )
            }

//            item {
//                AdMobNativeAds(
//                    adSize = AdSize.LARGE_BANNER,
//                    modifier = Modifier.fillMaxWidth()
//                )
//            }

            item {
                Spacer(modifier = Modifier.size(10.dp))
            }

            item {
                Divider()
            }

            item {
                Row(
                    modifier = Modifier
                        .background(iceBergBlue)
                        .fillMaxWidth()
                        .padding(16.dp), horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_reading),
                            contentDescription = ""
                        )
                        Spacer(modifier = Modifier.size(10.dp))
                        Text(text = "Published 5 hours ago.")
                    }

                    Row(modifier = Modifier.clickable {
                        onShareClick(article.title, article.slug)
                    }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_group),
                            contentDescription = ""
                        )
                        Spacer(modifier = Modifier.size(10.dp))
                        Text(text = "Share")
                    }
                }
            }

            item {
                Divider()
            }

            item {
                Spacer(modifier = Modifier.size(10.dp))
            }

//            item {
//                AdMobNativeAds(
//                    adSize = AdSize.MEDIUM_RECTANGLE,
//                    modifier = Modifier.fillMaxWidth()
//                )
//            }

            item {
                Spacer(modifier = Modifier.size(10.dp))
            }

            item {
                HtmlText(
                    modifier = Modifier
                        .background(Color.White)
                        .padding(horizontal = 14.dp),
                    text = article.content
                )
            }

            item {
                Divider()
            }

            item {
                Text(
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(iceBergBlue)
                        .padding(vertical = 20.dp),
                    text = stringResource(id = R.string.app_copyright)
                )
            }

        }
    }
}