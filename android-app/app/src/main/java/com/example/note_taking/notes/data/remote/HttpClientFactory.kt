package com.example.note_taking.notes.data.remote

import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

fun createHttpClient() = HttpClient(Android){
    expectSuccess = true

    install(ContentNegotiation){
        json(
            Json{
                isLenient = true
                ignoreUnknownKeys = true
            }
        )
    }
}