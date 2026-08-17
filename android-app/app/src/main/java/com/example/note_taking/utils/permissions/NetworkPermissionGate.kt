package com.example.note_taking.utils.permissions

import android.Manifest
import android.R.attr.text
import android.content.pm.PackageManager
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import com.example.note_taking.utils.components.PermissionDialog


@Composable
fun NetworkPermissionGate(
    content: @Composable () -> Unit
){
    val context = LocalContext.current

    var permissionGranted by remember { mutableStateOf(
            Build.VERSION.SDK_INT < 37 || ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_LOCAL_NETWORK
            ) == PackageManager.PERMISSION_GRANTED
        )
    }

    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { granted ->
            permissionGranted = granted },
        )

    if (permissionGranted){
        content()
    }else{
        PermissionDialog(
            onDismiss = {},
            onGrantPermission = { permissionLauncher.launch(Manifest.permission.ACCESS_LOCAL_NETWORK) },
            text = "The App needs internet Permission to work properly",
        )
    }
}