package com.jiwon.jetpack_composed_ui.ui.camera

import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import android.content.Context
import androidx.camera.core.*
import androidx.lifecycle.LifecycleOwner
import com.google.common.util.concurrent.ListenableFuture

object CameraxUI {
    @Composable
    fun DefaultPreview(context:Context? = null, lifecycleOwner : LifecycleOwner? = null, lensFacing : Int = CameraSelector.LENS_FACING_FRONT, modifier : Modifier? = null){
        val defaultContext = LocalContext.current
        val defaultLifecycleOwner = LocalLifecycleOwner.current

        // use @remember to persists lifcycle
        val cameraProviderFuture = remember{ ProcessCameraProvider.getInstance(context ?: defaultContext) }

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
                    .requireLensFacing(lensFacing)
                    .build()

                cameraProvider.unbindAll()
                cameraProvider.bindToLifecycle(
                    lifecycleOwner ?: defaultLifecycleOwner,
                    cameraSelector,
                    preview
                )
            }, cameraExecutor)
            previewView
        }, modifier =  modifier ?: Modifier.fillMaxSize())
    }

    @Composable
    fun AnalysisPreview(context:Context? = null, lifecycleOwner : LifecycleOwner? = null, lensFacing : Int = CameraSelector.LENS_FACING_FRONT, modifier : Modifier? = null, imageAnalysis : ImageAnalysis){
        val defaultContext = LocalContext.current
        val defaultLifecycleOwner = LocalLifecycleOwner.current

        // use @remember to persists lifcycle
        val cameraProviderFuture = remember{ ProcessCameraProvider.getInstance(context ?: defaultContext) }

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
                    .requireLensFacing(lensFacing)
                    .build()

                cameraProvider.unbindAll()
                cameraProvider.bindToLifecycle(
                    lifecycleOwner ?: defaultLifecycleOwner,
                    cameraSelector,
                    imageAnalysis,
                    preview
                )
            }, cameraExecutor)
            previewView
        }, modifier =  modifier ?: Modifier.fillMaxSize())
    }
}