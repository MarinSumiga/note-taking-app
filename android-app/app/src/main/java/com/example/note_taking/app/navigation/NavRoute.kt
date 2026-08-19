package com.example.note_taking.app.navigation

import androidx.navigation3.runtime.NavKey
import com.example.note_taking.notes.presentation.note_editor.NoteEditorScreenMode
import kotlinx.serialization.Serializable

@Serializable
sealed interface Route: NavKey {

    @Serializable
    data object NoteListScreenRoute : Route
    @Serializable
    data class NoteEditorScreenRoute(
        val mode: NoteEditorScreenMode,
    ) : Route

}

