package com.example.note_taking.app.di

import androidx.room.Room
import com.example.note_taking.notes.data.local.NoteDatabase
import com.example.note_taking.notes.data.remote.RemoteDataSource
import com.example.note_taking.notes.data.remote.createHttpClient
import com.example.note_taking.notes.data.repository.NoteRepositoryImpl
import com.example.note_taking.notes.domain.NoteRepository
import com.example.note_taking.notes.presentation.note_editor.NoteEditorViewModel
import com.example.note_taking.notes.presentation.note_list.NoteListViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import com.example.note_taking.notes.data.local.NoteDao
import com.example.note_taking.notes.presentation.note_editor.NoteEditorScreenMode
import io.ktor.client.engine.android.Android

val appModule = module{

    viewModel {
        NoteListViewModel(repository = get())
    }
    viewModel { parameters ->
        NoteEditorViewModel(
            mode = parameters.get<NoteEditorScreenMode>(),
            repository = get()
        )
    }
    single{
        createHttpClient(engine = Android.create())
    }
    single{
        RemoteDataSource(get())
    }
    single<NoteRepository>{
        NoteRepositoryImpl(get())
    }
    single {
        Room.databaseBuilder(
            context = androidContext(),
            klass = NoteDatabase::class.java,
            name = "notes.db"
        ).build()
    }
    single<NoteDao>{
        get<NoteDatabase>().noteDao()
    }
}