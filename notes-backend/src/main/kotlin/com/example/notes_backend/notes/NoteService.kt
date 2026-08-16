package com.example.notes_backend.notes

import org.bson.types.ObjectId
import org.springframework.stereotype.Service

@Service
class NoteService(
    private val repository: NoteRepository
) {

    fun toggleFavorite(id: ObjectId): Note? {
        val existingNote = repository.findById(id).orElse(null) ?: return null

        val updatedNote = existingNote.copy(isFavorite = !existingNote.isFavorite)

        return repository.save(updatedNote)
    }

}