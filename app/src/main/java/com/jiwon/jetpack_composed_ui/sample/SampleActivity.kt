package com.jiwon.jetpack_composed_ui.sample

import android.content.res.Configuration
import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jiwon.jetpack_composed_ui.R
import com.jiwon.jetpack_composed_ui.sample.chat.Conversation
import com.jiwon.jetpack_composed_ui.sample.chat.Message
import com.jiwon.jetpack_composed_ui.sample.chat.MessageCard
import com.jiwon.jetpack_composed_ui.sample.counter.Counter
import com.jiwon.jetpack_composed_ui.sample.counter.TestCounter
import com.jiwon.jetpack_composed_ui.sample.photograph.PhotoBook
import com.jiwon.jetpack_composed_ui.sample.photograph.PhotographCard
import com.jiwon.jetpack_composed_ui.sample.ui.theme.Jetpack_composed_uiTheme

class SampleActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PhotoBook()
        }
    }
}

@Preview(showBackground = true, name = "Dark Mode", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun DefaultPreview() {
    Jetpack_composed_uiTheme {
        PhotoBook()
    }
}