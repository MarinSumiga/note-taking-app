package com.example.note_taking.di

import com.example.note_taking.notes.data.remote.NoteApi
import com.example.note_taking.notes.data.remote.createHttpClient
import com.example.note_taking.notes.data.repository.NoteRepositoryImpl
import com.example.note_taking.notes.domain.NoteRepository
import com.example.note_taking.notes.presentation.note_editor.NoteEditorViewModel
import com.example.note_taking.notes.presentation.note_list.NoteListViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module{

    viewModel {
        NoteListViewModel(repository = get())
    }
    viewModel {
        NoteEditorViewModel(
            noteId = it.get(),
            repository = get()
        )
    }
    single{
        createHttpClient()
    }
    single{
        NoteApi(get())
    }
    single<NoteRepository>{
        NoteRepositoryImpl(get())
    }
}