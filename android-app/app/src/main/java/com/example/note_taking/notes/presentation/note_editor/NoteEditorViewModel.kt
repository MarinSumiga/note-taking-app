package com.example.note_taking.notes.presentation.note_editor

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.note_taking.notes.domain.Note
import com.example.note_taking.notes.domain.NoteRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
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
    private var saveJob: Job?=null
    private val _effects = Channel<NoteEditorEffect>()
    val effects = _effects.receiveAsFlow()
    private var navigateBackAfterSave = false

    init {
        noteId?.let ( ::loadNote )
    }

    fun onAction(action: NoteEditorAction) {
        when (action) {
            is NoteEditorAction.OnTitleChange -> {
                mutate(
                    NoteEditorMutation.TitleChanged(title = action.title)
                )
            }
            is NoteEditorAction.OnContentChange -> {
                mutate(
                    NoteEditorMutation.ContentChanged(content = action.content)
                )
            }
            NoteEditorAction.OnBackClick -> {
                if (state.value.hasUnsavedChanges) {
                    navigateBackAfterSave = true
                    saveNote()
                }else{
                    emitNavigateBack()
                }
            }
            NoteEditorAction.OnSaveClick -> {
                if (state.value.hasUnsavedChanges) {
                    saveNote()
                }
            }
            NoteEditorAction.OnRefresh -> {
                state.value.noteId?.let(::loadNote)
            }
        }
    }
    private fun mutate(mutation: NoteEditorMutation) {
        _state.update { currentState ->
            NoteEditorReducer.reduce(currentState,mutation)
        }
    }

    private fun loadNote(id: String) {
        mutate(NoteEditorMutation.LoadingStarted)
        viewModelScope.launch {
            try {
                val note = repository.findNoteById(id)
                mutate(
                    NoteEditorMutation.LoadingCompleted(note)
                )
            } catch (error: Exception) {
                mutate(NoteEditorMutation.LoadingFailed(error.message))
            }
        }
    }

    private fun saveNote() {
        if (saveJob?.isActive == true) return
        val currentState = state.value
        mutate(NoteEditorMutation.SavingStarted)
        saveJob = viewModelScope.launch {
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
                mutate(NoteEditorMutation.SavingCompleted(savedNote))
                if(navigateBackAfterSave){
                    navigateBackAfterSave = false
                    _effects.send(NoteEditorEffect.NavigateBack)
                }
            } catch (error: Exception) {
                mutate(NoteEditorMutation.SavingFailed(error.message))
            }
        }
    }
    private fun emitNavigateBack(){
        viewModelScope.launch {
            _effects.send(NoteEditorEffect.NavigateBack)
        }
    }

}
