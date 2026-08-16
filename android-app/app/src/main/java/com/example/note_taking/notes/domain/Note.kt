package com.example.note_taking.notes.domain

import java.time.Instant


data class Note(
    val id: String,
    val title: String,
    val content: String,
    val createdAt: Instant,
    val isFavorite: Boolean,
)


val notes = (1..19).map {
    Note(
        id = it.toString(),
        title = "Title $it",
        content = "Content $it",
        isFavorite = false,
        createdAt = Instant.now()
    )
}