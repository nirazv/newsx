package com.codecamp.newsx.ui.screen.nav_setting

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Divider
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.codecamp.newsx.R

@Composable
fun NavSettingScreen(paddingValues: PaddingValues) {

    val appNotification = remember {
        mutableStateOf(true)
    }

    val appNightMode = remember {
        mutableStateOf(true)
    }

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(paddingValues = paddingValues)
        .padding(horizontal = 20.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(vertical = 20.dp)) {
            Image(
                modifier = Modifier.size(50.dp),
                painter = painterResource(id = R.drawable.app_logo), contentDescription = "")
            Spacer(modifier = Modifier.size(16.dp))
            Column {
                Text(text = stringResource(id = R.string.app_name), fontWeight = FontWeight.SemiBold, fontSize = 20.sp)
                Text(text = stringResource(id = R.string.app_description))
            }

        }
        Divider()

        Spacer(modifier = Modifier.size(36.dp))
        Text(text = "Settings", fontWeight = FontWeight.SemiBold)
        Spacer(modifier = Modifier.size(20.dp))
        Divider()
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 5.dp), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween) {
            Text(text = "App Notification")
            Switch(checked = appNotification.value, onCheckedChange = {
                appNotification.value = !appNotification.value
            })
        }
        Divider()
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 5.dp), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween) {
            Text(text = "Night Mode")
            Switch(checked = appNightMode.value, onCheckedChange = {
                appNightMode.value = !appNightMode.value
            })
        }
        Divider()
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween) {
            Text(modifier = Modifier.padding(vertical = 10.dp), text = "Contact Us")
            Text(text = "agresarbharat@gmail.com")
        }
        Divider()
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween) {
            Text(modifier = Modifier.padding(vertical = 10.dp), text = "Website URL")
            Text(text = "www.agresarbharat.com")
        }
        Divider()
        Spacer(modifier = Modifier.size(20.dp))
        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween) {
            Text( text = "App Version")
            Text( text = stringResource(id = R.string.app_version))
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewScreen() {
    NavSettingScreen(paddingValues = PaddingValues(10.dp))
}