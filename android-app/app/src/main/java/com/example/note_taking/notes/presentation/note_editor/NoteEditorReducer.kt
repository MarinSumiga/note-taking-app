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
                state.copy(
                    noteTitle = mutation.title
                )
            }

            is NoteEditorMutation.ContentChanged -> {
                state.copy(
                    noteContent = mutation.content
                )
            }

            NoteEditorMutation.LoadingStarted -> {
                state.copy(
                    isLoading = true,
                    errorMessage = null
                )
            }

            is NoteEditorMutation.LoadingCompleted -> {
                val note = mutation.note

                state.copy(
                    noteId = note.id,
                    noteTitle = note.title,
                    noteContent = note.content,
                    savedTitle = note.title,
                    savedContent = note.content,
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
                val note = mutation.note

                state.copy(
                    noteId = note.id,
                    noteTitle = note.title,
                    noteContent = note.content,
                    savedTitle = note.title,
                    savedContent = note.content,
                    isSaving = false,
                    errorMessage = null
                )
            }

            is NoteEditorMutation.SavingFailed -> {
                state.copy(
                    isSaving = false,
                    errorMessage = mutation.errorMessage
                )
            }
        }
    }
}