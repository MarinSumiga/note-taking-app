package com.example.note_taking.notes.presentation.note_editor
/**
 * Akcijje su nam zapravo sve moguce UI akcije koje korisnik moze izvrsiti
 * Klik na gumb, promjena teksta, unos teksta itd.
 * NAVIGACIJA SAMA PO SEBI NIJE AKCIJA I VIEWMODEL NE MORA ZNATI ZA NJU OSIM AKO IMAMO SPECIFICAN
 * EFFECT KOJI SE KORISTI ZA NAVIGACIJU
 */
sealed interface NoteEditorAction {
    data class OnTitleChange(val title: String) : NoteEditorAction
    data class OnContentChange(val content: String) : NoteEditorAction
    data object OnSaveClick : NoteEditorAction
    data object OnBackClick : NoteEditorAction
    data object OnRefresh : NoteEditorAction
}

