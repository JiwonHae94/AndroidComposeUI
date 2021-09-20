package com.jiwon.jetpack_composed_ui.sample.photograph

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import kotlinx.coroutines.launch

@Composable
fun PhotoBook(modifier : Modifier = Modifier){
    val coroutineScope = rememberCoroutineScope()
    val scrollState = rememberLazyListState()

    Column{
        Row{
            Button(onClick = {
                coroutineScope.launch {
                    scrollState.animateScrollToItem(0)
                }
            }){
                Text("To First")
            }

        }

        LazyColumn(state = scrollState) {
            item(){
                Text("Image API Test")
            }

            items(100){ index ->
                val state = rememberSaveable(index){ mutableStateOf(false)}
                PhotographCard(modifier, index, state.value){
                    state.value = !state.value
                }
            }

        }
    }
}

@Composable
fun PhotographCard(modifier : Modifier = Modifier, cnt : Int, isSelected : Boolean, onClickListener : () -> Unit){
    var isSelected by remember { mutableStateOf(isSelected) }
    val background by animateColorAsState(targetValue = if(isSelected) MaterialTheme.colors.primarySurface else MaterialTheme.colors.surface)

    Row(
        modifier
            .padding(16.dp)
            .fillMaxWidth()
            .background(color = background)
            .clickable { isSelected = !isSelected
                onClickListener()
            }){
        Surface(
            modifier = Modifier.size(50.dp),
            shape = CircleShape,
            color = MaterialTheme.colors.onSurface.copy(alpha = 0.2f)
        ) {
            // Image goes here
            Image(
                painter = rememberImagePainter(
                    data = "https://developer.android.com/images/brand/Android_Robot.png"
                ),
                contentDescription = "Android Logo",
                modifier = Modifier.size(50.dp)
            )
        }
        Column(modifier = Modifier
            .padding(start = 8.dp)
            .align(Alignment.CenterVertically)) {
            Text("Alfred Sisley $cnt", fontWeight = FontWeight.Bold)
            CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                Text("3 minutes ago", style = MaterialTheme.typography.body2)
            }
        }
    }
}
