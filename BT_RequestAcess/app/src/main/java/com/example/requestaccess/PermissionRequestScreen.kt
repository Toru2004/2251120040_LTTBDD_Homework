package com.example.requestaccess

import android.Manifest
import android.os.Build
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.google.accompanist.permissions.*
import android.widget.Toast

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun PermissionRequestScreen() {
    val context = LocalContext.current

    // Permissions
    // Multi-permission for location
    val locationPermissions = rememberMultiplePermissionsState(
        listOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
        )
    )
    val cameraPermission = rememberPermissionState(Manifest.permission.CAMERA)
    val notificationPermission = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        rememberPermissionState(Manifest.permission.POST_NOTIFICATIONS)
    } else null

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Location
        Button(
            onClick = {
                locationPermissions.launchMultiplePermissionRequest()
                if (locationPermissions.allPermissionsGranted) {
                    Toast.makeText(context, "Location permission granted", Toast.LENGTH_SHORT).show()
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Request Location Permission")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Notification
        notificationPermission?.let {
            Button(
                onClick = {
                    it.launchPermissionRequest()
                    if (it.status.isGranted) {
                        Toast.makeText(context, "Notification permission granted", Toast.LENGTH_SHORT).show()
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Request Notification Permission")
            }

            Spacer(modifier = Modifier.height(16.dp))
        }

        // Camera
        Button(
            onClick = {
                cameraPermission.launchPermissionRequest()
                if (cameraPermission.status.isGranted) {
                    Toast.makeText(context, "Camera permission granted", Toast.LENGTH_SHORT).show()
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Request Camera Permission")
        }
    }
}
