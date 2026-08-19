package com.example.note_taking.notes.presentation.previews

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.note_taking.notes.domain.Note
import com.example.note_taking.notes.presentation.note_list.components.note_list_item.NoteListItem
import kotlin.time.Clock



@Preview
@Composable
fun NoteListItemPreview(){
    val note = Note(
        id = "1",
        title = "Title 1",
        content = "Content",
        isFavorite = false,
        createdAt = Clock.System.now(),
    )

    MaterialTheme {
        NoteListItem(
            modifier = Modifier,
            note = note,
            onFavoriteClick = {},
            onClick = {},
            onDeleteClick = {},
        )
    }
}
