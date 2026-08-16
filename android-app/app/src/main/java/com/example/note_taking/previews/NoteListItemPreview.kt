package com.example.note_taking.previews

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.note_taking.notes.domain.Note
import com.example.note_taking.notes.presentation.composables.NoteListItem
import java.time.Instant


@Preview
@Composable
fun NoteListItemPreview(){
    val note = Note(
        id = "1",
        title = "Title 1",
        content = "Content",
        isFavorite = false,
        createdAt = Instant.now(),
    )

    MaterialTheme(

    ) {
        NoteListItem(
            note = note,
            onNoteFavoriteClick = {},
            onClick = {},
        )
    }
}