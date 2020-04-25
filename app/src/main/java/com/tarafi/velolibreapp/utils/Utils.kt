package com.tarafi.velolibreapp.utils

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat


fun getLocationPermission(activity: Activity): Boolean { /*
     * Request location permission, so that we can get the location of the
     * device. The result of the permission request is handled by a callback,
     * onRequestPermissionsResult.
     */
    if (ContextCompat.checkSelfPermission(
            activity.applicationContext,
            Manifest.permission.ACCESS_FINE_LOCATION
        )
        == PackageManager.PERMISSION_GRANTED
    ) {
        return true
    } else {
        ActivityCompat.requestPermissions(
            activity, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
            1
        )
    }
    return false
}