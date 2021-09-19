package com.jiwon.jetpack_composed_ui

import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.fragment.app.FragmentActivity
import com.jiwon.jetpack_composed_ui.ui.camera.CameraxUI
import com.jiwon.jetpack_composed_ui.ui.theme.Jetpack_composed_uiTheme
import com.jiwon.jetpack_composed_ui.util.Permission

class MainActivity : FragmentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Jetpack_composed_uiTheme(true) {
                // A surface container using the 'background' color from the theme
                CameraxUI.DefaultPreview()
            }
        }

        requestPermissions(Permission.requiredPermission.toTypedArray(), Permission.PERMISSION_REQUEST_CODE)
    }


    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == Permission.PERMISSION_REQUEST_CODE) {
            if (PackageManager.PERMISSION_GRANTED == grantResults.firstOrNull()) {
                // Take the user to the success fragment when permission is granted
                Toast.makeText(this, "Permission request granted", Toast.LENGTH_LONG).show()
                //navigateToCamera()

            } else {
                Toast.makeText(this, "Permission request denied", Toast.LENGTH_LONG).show()
                finish()
            }
        }
    }
}

@Preview
@Composable
fun Preview(){
    Jetpack_composed_uiTheme(true) {
        CameraxUI.DefaultPreview()
    }
}


