package com.example.note_taking.notes.data.network

import androidx.room.util.appendPlaceholders
import com.example.note_taking.notes.data.dto.CreateNoteRequestDto
import com.example.note_taking.notes.data.dto.NoteDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.appendPathSegments
import io.ktor.http.contentType

private const val BASE_URL = "http://10.0.2.2:8080"

class NoteApi(
    private val httpClient: HttpClient
) {
    suspend fun getNotes(): List<NoteDto> {
        return httpClient
            .get(BASE_URL){
                url {
                    appendPathSegments("notes")
                }
        }.body()
    }

    suspend fun createNote(
        request: CreateNoteRequestDto
    ): NoteDto {
        return httpClient
            .post(BASE_URL){
                url{
                    appendPathSegments("notes")
                }
                contentType(ContentType.Application.Json)
                setBody(request)
            }
            .body()
    }

    suspend fun updateNote(
        id: String,
        request: CreateNoteRequestDto
    ): NoteDto{
        return httpClient.put (BASE_URL){
            url{
                appendPathSegments("notes", id)
            }
            contentType(ContentType.Application.Json)
            setBody(request)
        }.body()
    }

    suspend fun toggleFavorite(id: String): NoteDto {
        return httpClient.put(BASE_URL){
            url{
                appendPathSegments("notes", id, "favorite")
            }
        }.body()
    }

    suspend fun findNoteById(id: String): NoteDto {
        return httpClient
            .get(BASE_URL){
                url{
                    appendPathSegments("notes", id)
                }
            }
            .body()
    }
}
