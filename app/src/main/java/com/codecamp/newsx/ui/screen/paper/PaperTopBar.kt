package com.codecamp.newsx.ui.screen.paper

//import com.codecamp.newsx.ui.component.AdMobNativeAds
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.codecamp.newsx.R
import com.codecamp.newsx.data.local.ArticleEntity
import com.codecamp.newsx.data.utils.TaskLoadState
import com.codecamp.newsx.ui.theme.iceBergBlue

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PaperScreenTopBar(onBackClick:() -> Unit, onShareClicked: (String, String) -> Unit, selectedTask: State<TaskLoadState<ArticleEntity>>) {
    Column {

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
                    modifier = Modifier
                        .padding(horizontal = 12.dp)
                        .clickable {
                            onBackClick()
                        },
                    painter = painterResource(id = R.drawable.ic_arrow_back),
                    contentDescription = ""
                )
            },
            actions = {
                if (selectedTask.value is TaskLoadState.Loading)
                    CircularProgressIndicator()
                else {
                    val task =(selectedTask.value as TaskLoadState.Success).data
                    IconButton(onClick = { onShareClicked(task.title, task.slug) }) {
                        Icon(painter = painterResource(id = R.drawable.ic_group), contentDescription = "")
                    }
                }
            }
        )

//        AdMobNativeAds(
//            adSize = AdSize.FULL_BANNER,
//            modifier = Modifier.fillMaxWidth())
    }

}

