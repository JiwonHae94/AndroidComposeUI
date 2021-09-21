package com.jiwon.jetpack_composed_ui.sample.constraintLayout

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester.Companion.createRefs
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.jiwon.jetpack_composed_ui.ui.theme.Jetpack_composed_uiTheme
import kotlinx.coroutines.NonDisposableHandle.parent

@Composable
fun ConstraintLayoutContent(){
    ConstraintLayout {
        val (button, text) = createRefs()
        Button(
            onClick = {},
            modifier = Modifier.constrainAs(button){
                top.linkTo(parent.top, margin = 16.dp)
                absoluteRight.linkTo(parent.absoluteRight)
                absoluteLeft.linkTo(parent.absoluteLeft)

            }.fillMaxWidth()
        ){
            Text("Button")
        }

        Text("Text", Modifier.constrainAs(text){
            top.linkTo(button.bottom, margin = 16.dp)
            linkTo(button.start, button.end)
            width = Dimension.fillToConstraints
        }.background(Color.Cyan))
    }
}

@Preview
@Composable
fun ConstraintLayoutPreview(){
    Jetpack_composed_uiTheme {
        ConstraintLayoutContent()
    }
}