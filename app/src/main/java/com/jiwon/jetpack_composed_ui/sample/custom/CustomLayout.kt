package com.jiwon.jetpack_composed_ui.sample.custom

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.AlignmentLine
import androidx.compose.ui.layout.FirstBaseline
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.layout
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.jiwon.jetpack_composed_ui.ui.theme.Jetpack_composed_uiTheme

@Composable
fun Modifier.firstBaseLineToTop(firstBaselineToTop: Dp) = this.then(
    layout { measurable, constraints ->
        val placeable = measurable.measure(constraints)

        // Check the composable has a first baseline
        check(placeable[FirstBaseline] != AlignmentLine.Unspecified)
        val firstBaseline = placeable[FirstBaseline]

        // Height of the composable with padding - first baseline
        val placeableY = firstBaselineToTop.roundToPx() - firstBaseline
        val height = placeable.height + placeableY

        layout(placeable.width, height) {
            placeable.placeRelative(0, placeableY)
        }
    }
)

@Composable
fun CustomColumn(modifier : Modifier = Modifier, content : @Composable () -> Unit){
    Layout(
        modifier = modifier,
        content = content
    ){ measurables, constraints->

        // Don't constrain child views further, measure them with given constraints
        // List of measured children
        val placeable = measurables.map{ measurable ->
            measurable.measure(constraints)
        }

        // Track the y co-ord we have placed children up to
        var yPosition = 0

        layout(constraints.maxWidth, constraints.maxHeight){
            placeable.forEach { placeable ->
                // Position item on the screen
                placeable.placeRelative(x = 0, y = yPosition)

                // Record the y co-ord placed up to
                yPosition += placeable.height
            }
        }
    }
}

@Preview
@Composable
fun BodyContent(modifier: Modifier = Modifier){
    CustomColumn(modifier.padding(8.dp)) {
        Text("MyOwnColumn")
        Text("places items")
        Text("vertically.")
        Text("We've done it by hand!")
    }
}

@Preview
@Composable
fun TextWithPaddingToBaselinePreview(){
    Jetpack_composed_uiTheme {
        Text("Hi there", Modifier.firstBaseLineToTop(32.dp))
    }
}


@Preview
@Composable
fun TextWithNormalPaddingPreview() {
    Jetpack_composed_uiTheme {
        Text("Hi there!", Modifier.padding(top = 32.dp))
    }
}