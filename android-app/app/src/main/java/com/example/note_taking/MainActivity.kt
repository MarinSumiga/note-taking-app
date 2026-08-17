package com.example.note_taking

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.note_taking.ui.theme.NotetakingTheme
import com.example.note_taking.utils.permissions.NetworkPermissionGate

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NotetakingTheme {
                NetworkPermissionGate {
                    NotesApp()
                }
            }
        }
    }
}
