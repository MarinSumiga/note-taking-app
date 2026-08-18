package com.example.note_taking.notes.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes")
data class NoteEntity(
    @PrimaryKey
    val id : String,

    val title: String,
    val content: String,
    val isFavorite: Boolean = false,
    val createdAt: Long
)
