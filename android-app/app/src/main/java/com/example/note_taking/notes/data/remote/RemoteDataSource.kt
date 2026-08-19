package com.example.note_taking.notes.data.remote

import com.example.note_taking.core.data.safeNetworkCall
import com.example.note_taking.core.domain.DataError
import com.example.note_taking.core.domain.Result
import com.example.note_taking.notes.data.remote.dto.NoteDto
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.http.appendPathSegments

private const val BASE_URL = "http://10.0.2.2:8080"

class RemoteDataSource(
    private val httpClient: HttpClient
) {
    suspend fun getNotes(): Result<List<NoteDto>, DataError.Remote> {
        return safeNetworkCall {
            httpClient.get(BASE_URL){
                url {
                    appendPathSegments("notes")
                }
            }
        }
    }
}
