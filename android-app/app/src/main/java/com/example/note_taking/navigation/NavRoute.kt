package com.example.note_taking.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable



@Serializable
data object NoteListScreenRoute : NavKey

@Serializable
data class NoteEditorScreenRoute(
    val noteId: String?,
) : NavKey

