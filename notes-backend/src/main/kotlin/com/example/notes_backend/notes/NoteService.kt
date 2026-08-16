package com.example.notes_backend.notes

import com.example.notes_backend.notes.api.CreateNoteRequest
import org.bson.types.ObjectId
import org.springframework.stereotype.Service

@Service
class NoteService(
    private val repository: NoteRepository
) {

    fun updateNote(
        id: ObjectId,
        body: CreateNoteRequest,
    ): Note {
        val existingNote = repository.findById(id).orElseThrow()
        val updatedNote = existingNote.copy(
            title = body.title,
            content = body.content,
        )
        return repository.save(updatedNote)
    }

    fun save(note: Note): Note {
        return repository.save(note)
    }

    fun findAll():List<Note>{
        return repository.findAll()
    }

    fun toggleFavorite(id: ObjectId): Note {
        val existingNote = repository.findById(id).orElseThrow()
        val updatedNote = existingNote.copy(isFavorite = !existingNote.isFavorite)

        return repository.save(updatedNote)
    }

}