package com.example.note_taking.notes.data.dto

import kotlinx.serialization.Serializable


@Serializable
data class CreateNoteRequestDto(
    val title: String,
    val content: String,
)

