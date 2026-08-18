package com.example.note_taking.notes.presentation.note_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.note_taking.notes.domain.NoteRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class NoteListViewModel(
    private val repository: NoteRepository
): ViewModel() {

    private val _state = MutableStateFlow(NoteListState())
    val state = _state.asStateFlow()

    init {
        observeNotes()
    }

    fun onAction(action : NoteListAction){
        when(action){

            is NoteListAction.OnSearchQueryChange ->{

            }
            is NoteListAction.OnNoteFavoriteClick -> {
                toggleFavorite(action.id)
            }
            is NoteListAction.OnDeleteNoteClick -> {
                viewModelScope.launch {
                    repository.deleteNote(action.id)
                }
            }

            is NoteListAction.OnRefresh -> {}
        }
    }

    private fun observeNotes() {
        viewModelScope.launch {
            repository.getNotes()
                .onStart {
                    _state.update {
                        it.copy(
                            isLoading = true,
                            errorMessage = null
                        )
                    }
                }
                .catch { error ->
                    _state.update {
                        it.copy(
                            isLoading = false,
                            errorMessage = error.message
                        )
                    }
                }
                .collect { notes ->
                    _state.update {
                        it.copy(
                            notes = notes,
                            isLoading = false,
                            errorMessage = null
                        )
                    }
                }
        }
    }

    private fun toggleFavorite(id: String){
        viewModelScope.launch {
            repository.toggleFavorite(id)
        }
    }
}