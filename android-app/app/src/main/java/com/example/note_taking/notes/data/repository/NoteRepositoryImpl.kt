package com.example.note_taking.notes.data.repository

import com.example.note_taking.notes.data.local.NoteDao
import com.example.note_taking.notes.data.local.toEntity
import com.example.note_taking.notes.data.local.toNote
import com.example.note_taking.notes.domain.Note
import com.example.note_taking.notes.domain.NoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


class NoteRepositoryImpl(
    private val noteDao: NoteDao
): NoteRepository {
    override fun getNotes(): Flow<List<Note>> {
        return noteDao.getNotes().map { entities ->
            entities.map {
                it.toNote()
            }
        }
    }
    override suspend fun upsertNote(note: Note){
        noteDao.upsertNote(note.toEntity())
    }

    override suspend fun toggleFavorite(id: String){
        noteDao.toggleFavorite(id)
    }

    override suspend fun findNoteById(id: String): Note {
        return noteDao.getNoteById(id)?.toNote()
            ?: throw NoSuchElementException("Note with ID $id was not found")
    }

    override suspend fun deleteNote(id: String){
        noteDao.deleteNote(id)
    }
}