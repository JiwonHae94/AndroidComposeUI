package com.jiwon.jetpack_composed_ui.sample.counter

import android.graphics.Color
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun Counter(){
    val count = remember { mutableStateOf(0)}
    Button(onClick = {count.value+=1}){
        Text("num click : ${count.value}")
    }
}

@Composable
fun Greeting(name : String){
    var isSelected by remember { mutableStateOf(false) }
    val backgroundColor by animateColorAsState(targetValue = if(isSelected) androidx.compose.ui.graphics.Color.Red else androidx.compose.ui.graphics.Color.White)
    Text(
        text = "Welcome Mr. $name",
        modifier = Modifier
            .padding(24.dp)
            .background(color = backgroundColor)
            .clickable { isSelected = !isSelected }
        )

}

@Composable
fun TestCounter(sample : List<String> = listOf("test1", "test2")){
    Column{
        for(s in sample){
            Greeting(s)
            Divider(color = androidx.compose.ui.graphics.Color.Black)
        }

        Divider(color = androidx.compose.ui.graphics.Color.Transparent, thickness = 10.dp)
        Counter()
    }
}