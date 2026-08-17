package com.example.note_taking.previews

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.note_taking.notes.presentation.note_list.components.NoteSearchBar

@Preview
@Composable
fun NoteSearchBarPreview(){
    MaterialTheme()
       {
        NoteSearchBar(
            searchQuery = "",
            onSearchQueryChange = {},
            onSearch = {}
        )
    }
}