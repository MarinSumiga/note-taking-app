package com.example.note_taking.notes.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

/**
 * Room database sluzi nam za exposanje svih povezanih DATA ACCESS OBJECTA
 * vezanih uz nasu bazu podataka
 * */
@Database(
    entities = [NoteEntity::class],
    version = 1,
    exportSchema = false
)

abstract class NoteDatabase : RoomDatabase(){
    abstract fun noteDao(): NoteDao
}

