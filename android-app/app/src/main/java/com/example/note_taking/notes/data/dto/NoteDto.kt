package com.example.note_taking.notes.data.dto

import kotlinx.serialization.Serializable
import kotlin.time.Instant

@Serializable
data class NoteDto(
    val id: String,
    val title: String,
    val content: String,
    val createdAt: Instant,
    val isFavorite: Boolean,
)
