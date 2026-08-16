package com.example.notes_backend.notes

import org.bson.types.ObjectId
import org.springframework.data.mongodb.core.query.where
import org.springframework.data.mongodb.repository.MongoRepository

interface NoteRepository: MongoRepository<Note, ObjectId>{
    //Ovdje custom funkcije idu i queryi
    fun findByIsFavorite(isFavorite: Boolean) : List<Note>
}