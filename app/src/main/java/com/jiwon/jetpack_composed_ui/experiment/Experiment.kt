package com.jiwon.jetpack_composed_ui.experiment

import android.graphics.Color
import android.graphics.Point
import androidx.camera.core.CameraSelector
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.PointMode
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.viewinterop.AndroidView
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.core.content.ContextCompat
import androidx.lifecycle.MutableLiveData
import coil.compose.rememberImagePainter
import com.jiwon.jetpack_composed_ui.ui.theme.Jetpack_composed_uiTheme
import java.nio.FloatBuffer


private val inferenceResult = MutableLiveData<Point>()

@Composable
fun BodyContent(){
    CameraViewWithBbox()
}

@Composable
fun CameraViewWithBbox(){
    ConstraintLayout() {
        val (cameraView, bbox) = createRefs()
        val floatBuffer = FloatBuffer.allocate(4)

        CameraView(modifier = Modifier
            .fillMaxSize()
            .constrainAs(cameraView) {

            })

        InferenceCanvas(modifier = Modifier
            .fillMaxSize()
            .constrainAs(bbox) {
                linkTo(cameraView.start, cameraView.end)
                width = Dimension.fillToConstraints
                height = Dimension.fillToConstraints
            }, floatBuffer)
    }
}

data class CameraConfig(val lensFacing : Int = CameraSelector.LENS_FACING_BACK)

@Composable
fun CameraView(config : CameraConfig = CameraConfig(), modifier : Modifier = Modifier.fillMaxSize()){
    val localContext = LocalContext.current
    val localLifecycleOwner = LocalLifecycleOwner.current

    // use @remember to persists lifcycle
    val cameraProviderFuture = remember{ ProcessCameraProvider.getInstance(localContext) }

    // AndroidView is used for views that does not currently have a corresponding View
    AndroidView( factory = { ctx ->
        val previewView = PreviewView(ctx)
        val cameraExecutor = ContextCompat.getMainExecutor(ctx)
        cameraProviderFuture.addListener({
            val cameraProvider = cameraProviderFuture.get()
            val preview = Preview.Builder().build().also {
                it.setSurfaceProvider(previewView.surfaceProvider)
            }
            val cameraSelector = CameraSelector.Builder()
                .requireLensFacing(config.lensFacing)
                .build()

            cameraProvider.unbindAll()
            cameraProvider.bindToLifecycle(
                localLifecycleOwner,
                cameraSelector,
                preview
            )
        }, cameraExecutor)
        previewView
    }, modifier =  modifier)
}

@Composable
fun InferenceCanvas(modifier : Modifier = Modifier.fillMaxSize(), buffer : FloatBuffer){
    val coordinates = FloatArray(buffer.capacity()).toOffset()

    Canvas(modifier){
        val canvasWidth = size.width
        val canvasHeight = size.height

        drawPoints(points = coordinates, PointMode.Points, Color.DKGRAY)
    }
}

private fun FloatArray.toOffset() : List<Offset> {
    val offset = ArrayList<Offset>()
    for(i in 0 until this.size step 2){
        val x = this.get(i)
        val y = this.get(i + 1)
        val pt = Offset(x, y)
        offset.add(pt)
    }

    return offset.toTypedArray().toList()


}

@androidx.compose.ui.tooling.preview.Preview
@Composable
fun MainPage(){
    Jetpack_composed_uiTheme {
        val rslt = inferenceResult.observeAsState()
        Scaffold(topBar = {
            TopAppBar {
                Text("Sample UI")
                Icon(painter  = rememberImagePainter(
                    data = "https://developer.android.com/images/brand/Android_Robot.png"
                ), contentDescription = "sample application icon")
            }
        }) {

        }
    }
}