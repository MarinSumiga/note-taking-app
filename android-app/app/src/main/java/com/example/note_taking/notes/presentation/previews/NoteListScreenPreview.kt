package com.example.note_taking.notes.presentation.previews

import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.note_taking.notes.domain.Note
import com.example.note_taking.notes.presentation.note_list.NoteListScreen
import com.example.note_taking.notes.presentation.note_list.NoteListState
import kotlin.time.Clock

@Preview
@Composable
fun NoteListScreenPreview() {
    val notes = (1..30).map {
        Note(
            id = it.toString(),
            title = "Title $it",
            content = "Content $it",
            isFavorite = false,
            createdAt = Clock.System.now(),
        )
    }

    MaterialTheme {
        NoteListScreen(
            state =NoteListState(notes),
            onAction = {},
            onNoteClick = {},
            onCreateNoteClick = {},
            lazyGridState = rememberLazyGridState()
        )
    }
}
