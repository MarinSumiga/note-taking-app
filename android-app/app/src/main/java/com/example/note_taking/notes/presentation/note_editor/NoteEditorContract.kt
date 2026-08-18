package com.example.note_taking.notes.presentation.note_editor

import com.example.note_taking.notes.domain.Note
import kotlinx.serialization.Serializable

data class NoteEditorState(
    val note: Note?,
    val hasUnsavedChanges: Boolean = false,
    val errorMessage: String? = null,
    val isLoading: Boolean = false,
    val isSaving: Boolean = false
)

sealed interface NoteEditorAction {
    /**
     * Akcijje su nam zapravo sve moguce UI akcije koje korisnik moze izvrsiti
     * Klik na gumb, promjena teksta, unos teksta itd.
     * NAVIGACIJA SAMA PO SEBI NIJE AKCIJA I VIEWMODEL NE MORA ZNATI ZA NJU OSIM AKO IMAMO SPECIFICAN
     * EFFECT KOJI SE KORISTI ZA NAVIGACIJU
     */
    data class OnTitleChange(val title: String) : NoteEditorAction
    data class OnContentChange(val content: String) : NoteEditorAction
    data object OnSaveClick : NoteEditorAction
    data object OnBackClick : NoteEditorAction
    data object OnRefresh : NoteEditorAction
}


@Serializable
sealed interface NoteEditorScreenMode {
    @Serializable
    data object Create : NoteEditorScreenMode
    @Serializable
    data class Edit(val noteId: String) : NoteEditorScreenMode
}


sealed interface NoteEditorEffect {
    /**
     * Effectovi su nam naredbe UI-a koje se izvrsavaju nakon nekog dogadjaja.
     * Npr. NavigateBack cemo koristit kada dode do klika na gumb nazad da pokrecemo
     * spremanje izmjena u bazi.
     */

    /**
     * Ili isto tako nam govore recimo da imamo effect ShowSavedComplete
     * koji ce nam pokazat snackbar na uspjesno sacuvani Note u nasem slucaju
     * Isto tako mozemo imat i snack bar i effect ShowSavingStarted koji ce nam rec
     * da smo uspjesno pokrenuli spremanje
     * */
    data object  NavigateBack : NoteEditorEffect
}