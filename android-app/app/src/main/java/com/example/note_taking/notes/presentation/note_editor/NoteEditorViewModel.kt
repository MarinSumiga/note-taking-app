package com.example.note_taking.notes.presentation.note_editor

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.note_taking.notes.domain.NoteRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class NoteEditorViewModel(
    private val repository: NoteRepository,
    noteId: String?
) : ViewModel() {
    private val _state = MutableStateFlow(
        NoteEditorState(
            noteId = noteId,
            isLoading = noteId != null
        )
    )
    val state = _state.asStateFlow()

    init {
        noteId?.let(::loadNote)
    }

    fun onAction(action: NoteEditorAction) {
        when (action) {
            is NoteEditorAction.OnTitleChange -> _state.update {
                it.copy(noteTitle = action.title)
            }

            is NoteEditorAction.OnContentChange -> _state.update {
                it.copy(noteContent = action.content)
            }

            NoteEditorAction.OnSaveClick -> saveNote()
            NoteEditorAction.OnRefresh -> state.value.noteId?.let(::loadNote)
        }
    }

    private fun loadNote(id: String) {
        viewModelScope.launch {
            showLoading()
            try {
                val note = repository.findNoteById(id)

                _state.update {
                    it.copy(
                        noteId = note.id,
                        noteTitle = note.title,
                        noteContent = note.content,
                        isLoading = false
                    )
                }
            } catch (error: Exception) {
                showError(error)
            }
        }
    }

    private fun saveNote() {
        viewModelScope.launch {
            val currentState = state.value
            showLoading()

            try {
                val savedNote = if (currentState.noteId.isNullOrBlank()) {
                    repository.createNote(
                        title = currentState.noteTitle,
                        content = currentState.noteContent
                    )
                } else {
                    repository.updateNote(
                        id = currentState.noteId,
                        title = currentState.noteTitle,
                        content = currentState.noteContent
                    )
                }
                _state.update {
                    it.copy(
                        noteId = savedNote.id,
                        noteTitle = savedNote.title,
                        noteContent = savedNote.content,
                        isLoading = false,
                        errorMessage = null
                    )
                }
            } catch (error: Exception) {
                showError(error)
            }
        }
    }

    private fun showLoading() {
        _state.update {
            it.copy(
                isLoading = true,
                errorMessage = null
            )
        }
    }

    private fun showError(error: Exception) {
        _state.update {
            it.copy(
                errorMessage = error.message,
                isLoading = false
            )
        }
    }
}
