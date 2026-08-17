package com.example.notes_backend.notes.api

import com.example.notes_backend.notes.Note
import kotlin.time.Instant

data class NoteResponse (
    val id:String,
    val title:String,
    val content:String,
    val createdAt: Instant,
    val isFavorite:Boolean,
)

fun Note.toNoteResponse() : NoteResponse {
    return NoteResponse(
        id = requireNotNull(id).toHexString() ,
        title = title,
        content = content,
        createdAt = createdAt,
        isFavorite = isFavorite,
    )
}
