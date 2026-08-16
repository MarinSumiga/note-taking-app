package com.example.note_taking.notes.domain

import java.util.Date


data class Note(
    val id: String,
    val title: String,
    val content: String,
    val createdAt: Date
)


val notes = (1..19).map {
    Note(
        id = it.toString(),
        title = "Title $it",
        content = "Content $it",
        createdAt = Date()
    )
}