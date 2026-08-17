package com.example.note_taking.notes.data.network

import com.example.note_taking.notes.data.dto.NoteDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

private const val BASE_URL = "http://10.0.2.2:8080"

class NoteApi(
    private val httpClient: HttpClient
) {
    suspend fun getNotes(): List<NoteDto> {
        return httpClient
            .get("$BASE_URL/notes")
            .body()
    }
}