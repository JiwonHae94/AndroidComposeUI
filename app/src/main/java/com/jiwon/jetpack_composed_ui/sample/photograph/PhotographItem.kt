package com.jiwon.jetpack_composed_ui.sample.photograph

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun PhotographCard(modifier : Modifier = Modifier){
    var isSelected by remember { mutableStateOf(false) }
    val background by animateColorAsState(targetValue = if(isSelected) MaterialTheme.colors.primarySurface else MaterialTheme.colors.surface)

    Row(modifier.padding(16.dp)
        .fillMaxWidth()
        .background(color = background)
        .clickable { isSelected = !isSelected }){
        Surface(
            modifier = Modifier.size(50.dp),
            shape = CircleShape,
            color = MaterialTheme.colors.onSurface.copy(alpha = 0.2f)
        ) {
            // Image goes here

        }
        Column(modifier = Modifier
            .padding(start = 8.dp)
            .align(Alignment.CenterVertically)) {
            Text("Alfred Sisley", fontWeight = FontWeight.Bold)
            CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                Text("3 minutes ago", style = MaterialTheme.typography.body2)
            }
        }
    }
}