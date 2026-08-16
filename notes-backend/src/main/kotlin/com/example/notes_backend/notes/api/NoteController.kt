package com.example.notes_backend.notes.api


import com.example.notes_backend.notes.Note
import com.example.notes_backend.notes.NoteService
import jakarta.validation.Valid
import org.bson.types.ObjectId
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException


@RestController
@RequestMapping("/notes")
class NoteController (
    private val noteService: NoteService
){

    @PutMapping("/{id}")
    fun updateNote(
        @PathVariable id: String,
        @Valid @RequestBody body: CreateNoteRequest,
    ): NoteResponse {
        val updatedNote = noteService.updateNote(
            id = ObjectId(id),
            body = body,
        )
        return updatedNote.toNoteResponse()
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(
        @Valid @RequestBody body: CreateNoteRequest,
    ): NoteResponse =
        noteService.save(
            Note(
                title = body.title,
                content = body.content,
            )
        ).toNoteResponse()

    @GetMapping
    fun getNotes(): List<NoteResponse> {
        return noteService.findAll().map{
            it.toNoteResponse()
        }
    }

    @PatchMapping("/{id}/favorite")
    fun toggleFavorite(
        @PathVariable id: String,
    ): NoteResponse =
         noteService.toggleFavorite(ObjectId(id)).toNoteResponse()
}