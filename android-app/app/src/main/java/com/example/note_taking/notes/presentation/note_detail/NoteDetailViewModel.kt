package com.example.note_taking.notes.presentation.note_detail

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class NoteDetailViewModel(
    private val noteId:String,
) : ViewModel() {
    private val _state = MutableStateFlow(NoteDetailState())
    val state = _state.asStateFlow()

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
}