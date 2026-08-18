package com.example.note_taking.notes.presentation.note_list.components.note_list_item

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.note_taking.notes.domain.Note

@Composable
fun NoteListItem(
    note: Note,
    onClick: () -> Unit,
    onFavoriteClick: () -> Unit,
    onDeleteClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    var menuExpanded by remember(note.id) {
        mutableStateOf(false)
    }

    Box(
        modifier = modifier
    ) {
        NoteListItemContent(
            note = note,
            onClick = onClick,
            onLongClick = { menuExpanded = true },
            onFavoriteClick = onFavoriteClick,
            onMenuClick = { menuExpanded = true },
        )

        NoteListItemMenu(
            expanded = menuExpanded,
            onDismiss = { menuExpanded = false },
            onDeleteClick = {
                menuExpanded = false
                onDeleteClick()
            },
            modifier = Modifier.align(Alignment.TopEnd),
        )
    }
}
