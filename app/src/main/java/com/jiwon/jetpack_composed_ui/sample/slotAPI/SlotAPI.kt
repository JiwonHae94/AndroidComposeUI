package com.jiwon.jetpack_composed_ui.sample.slotAPI

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


/**
 * Slot APIs are a pattern Compose introduces to bring in a layer of customization on top of composables
 */

@Composable
fun CustomButton(){
    Button {
        Row{
            Spacer(modifier = Modifier.padding(4.dp))
            Text("Button")
        }
    }
}

@Composable
fun Button(
    modifier : Modifier = Modifier,
    onClick : (() -> Unit)? = null,
    content : @Composable () -> Unit
){}

/**
 * Scaffold allows you to implement a UI with the basic Material Design layout structure.
 * It provides slots for the most common top-level Material components such as
 * TopAppBar, BottomAppBar, FloatingActionButton and Drawer.
 */
@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun ScaffoldSample(){
    Scaffold(
        topBar = {
            Text(text = "LayoutsCodelab",
                style = MaterialTheme.typography.h3
            )
        }
    ) { innerPadding ->
        BodyContent(Modifier.padding(innerPadding))
    }

    val composableScope = rememberCoroutineScope()
    composableScope.launch {
        test()
    }
}

suspend fun test() = withContext(Dispatchers.IO){

}


@Composable
fun BodyContent(modifier : Modifier = Modifier){
    Column(modifier = modifier) {
        Text(text = "Hi there!")
        Text(text = "Thanks for going through the Layouts codelab")
    }
}