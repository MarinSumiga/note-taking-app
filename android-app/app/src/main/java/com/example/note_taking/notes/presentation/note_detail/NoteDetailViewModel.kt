package com.example.note_taking.notes.presentation.note_detail

import androidx.lifecycle.ViewModel
import com.example.note_taking.notes.domain.notes
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class NoteDetailViewModel(
    private val noteId:String,
) : ViewModel() {
    private val _state = MutableStateFlow(NoteDetailState())
    val state = _state.asStateFlow()

    init {
        loadNote()
    }

    fun onAction(action: NoteDetailAction){
        when(action){
            is NoteDetailAction.OnTitleChange -> {

            }
            is NoteDetailAction.OnContentChange -> {
                _state.update {
                    it.copy(noteContent = action.content)
                }
            }
        }
    }
    private fun loadNote(){
        val note = notes.firstOrNull{ it.id == noteId }

        _state.update { state ->
            if(note == null){
                state.copy(
                    isLoading = false,
                    errorMessage = "Note not found"
                )
            }else{
                state.copy(
                    isLoading = false,
                    noteId = note.id,
                    noteTitle = note.title,
                    noteContent = note.content,
                    errorMessage = null
                )
            }
        }
    }

}