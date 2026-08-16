package com.example.note_taking.di

import com.example.note_taking.notes.presentation.note_detail.NoteDetailViewModel
import com.example.note_taking.notes.presentation.note_list.NoteListViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module{
    viewModel {
        NoteListViewModel()
    }
    viewModel {
        NoteDetailViewModel(noteId = it.get())
    }
}