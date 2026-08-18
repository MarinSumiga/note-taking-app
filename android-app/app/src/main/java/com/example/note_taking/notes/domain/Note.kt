package com.example.note_taking.notes.domain


import java.util.UUID
import kotlin.time.Clock
import kotlin.time.Instant

data class Note(
    val id: String,
    val title: String,
    val content: String,
    val createdAt: Instant,
    val isFavorite: Boolean,
){
    companion object {
        fun createNew():Note{
            return Note(
                id = UUID.randomUUID().toString(),
                title = "",
                content = "",
                createdAt = Clock.System.now(),
                isFavorite = false
            )
        }
    }
}
