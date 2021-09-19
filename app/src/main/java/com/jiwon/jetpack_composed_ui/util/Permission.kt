package com.jiwon.jetpack_composed_ui.util

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat
import java.security.acl.Permission

object Permission {
    const val PERMISSION_REQUEST_CODE = 100

    val requiredPermission = listOf(Manifest.permission.CAMERA)

    /** Convenience method used to check if all permissions required by this app are granted */
    fun hasPermissions(context: Context) = requiredPermission.all {
        ContextCompat.checkSelfPermission(context, it) == PackageManager.PERMISSION_GRANTED
    }


}