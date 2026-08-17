package com.example.note_taking.notes.presentation.note_editor

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

sealed interface NoteEditorEffect {
    data object  NavigateBack : NoteEditorEffect
}