package com.example.notes_backend.notes

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import kotlin.time.Clock
import kotlin.time.Instant

@Document(collection = "notes")
data class Note(
    @Id val id: ObjectId? = ObjectId.get(),
    val title: String,
    val content: String,
    val createdAt: Instant = Clock.System.now(),
    val isFavorite:Boolean = false
)
