package com.example.note_taking.notes.presentation.note_editor

import com.example.note_taking.notes.domain.Note

internal sealed interface NoteEditorMutation
{
    data class TitleChanged(
        val title: String
    ): NoteEditorMutation
    data class ContentChanged(
        val content: String
    ): NoteEditorMutation
    data object SavingStarted: NoteEditorMutation
    data class SavingCompleted(
        val note: Note
    ): NoteEditorMutation
    data class SavingFailed(
        val errorMessage: String?
    ): NoteEditorMutation
    data object LoadingStarted: NoteEditorMutation
    data class LoadingCompleted(
        val note: Note
    ): NoteEditorMutation
    data class LoadingFailed(
        val errorMessage: String?
    ): NoteEditorMutation
}

internal object NoteEditorReducer {
    fun reduce(
        state: NoteEditorState,
        mutation: NoteEditorMutation
    ): NoteEditorState {
        return when (mutation) {

            is NoteEditorMutation.TitleChanged -> {
                val note = state.note ?: return state
                state.copy(
                    note = note.copy(title = mutation.title),
                    hasUnsavedChanges = true
                )
            }

            is NoteEditorMutation.ContentChanged -> {
                val note = state.note ?: return state
                state.copy(
                    note = note.copy(content = mutation.content),
                    hasUnsavedChanges = true
                )
            }

            NoteEditorMutation.LoadingStarted -> {
                state.copy(
                    isLoading = true,
                    errorMessage = null
                )
            }

            is NoteEditorMutation.LoadingCompleted -> {
                state.copy(
                    note = mutation.note,
                    hasUnsavedChanges = false,
                    isLoading = false,
                    errorMessage = null
                )
            }

            is NoteEditorMutation.LoadingFailed -> {
                state.copy(
                    isLoading = false,
                    errorMessage = mutation.errorMessage
                )
            }

            NoteEditorMutation.SavingStarted -> {
                state.copy(
                    isSaving = true,
                    errorMessage = null
                )
            }

            is NoteEditorMutation.SavingCompleted -> {

                state.copy(
                    note = mutation.note,
                    hasUnsavedChanges = false,
                    isSaving = false,
                    errorMessage = null
                )
            }

            is NoteEditorMutation.SavingFailed -> {
                state.copy(
                    hasUnsavedChanges = true,
                    isSaving = false,
                    errorMessage = mutation.errorMessage
                )
            }
        }
    }
}