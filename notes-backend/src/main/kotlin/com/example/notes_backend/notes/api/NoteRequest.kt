package com.example.notes_backend.notes.api

import jakarta.validation.constraints.NotBlank

class CreateNoteRequest (
    @field:NotBlank(message = "Title cannot be empty") val title:String,
    @field:NotBlank(message = "Content cannot be empty") val content:String,
)
