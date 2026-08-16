package com.example.note_taking.notes.presentation.composables

import android.R
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.note_taking.notes.domain.Note

@Composable
fun NoteList(
    notes: List<Note>,
    onNoteClick: (String) -> Unit,
    modifier : Modifier = Modifier,
    scrollState : LazyGridState = rememberLazyGridState()
){
    LazyVerticalGrid(
        modifier = modifier,
        columns = GridCells.Fixed(maxOf(3)),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        state = scrollState,
    ) {
        items(
            items = notes,
            key = {it.id}
        ){
            NoteListItem(
                note = it,
                onClick = {onNoteClick(it.id)}
            )
        }
    }
}