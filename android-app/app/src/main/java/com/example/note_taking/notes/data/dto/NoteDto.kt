package com.example.note_taking.notes.data.dto

import kotlinx.serialization.Serializable
import kotlinx.serialization.builtins.InstantComponentSerializer
import kotlin.time.ExperimentalTime
import kotlin.time.Instant

@OptIn(ExperimentalTime::class)
@Serializable
data class NoteDto(
    val id: String,
    val title: String,
    val content: String,
    @Serializable(with = InstantComponentSerializer::class)
    val createdAt: Instant,
    val isFavorite: Boolean,
)
