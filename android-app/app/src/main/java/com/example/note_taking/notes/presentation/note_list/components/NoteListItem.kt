package com.example.note_taking.notes.presentation.note_list.components


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.StarOutline
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.note_taking.notes.domain.Note

@Composable
fun NoteListItem(
    modifier: Modifier=Modifier,
    note: Note,
    onClick: () -> Unit,
    onNoteFavoriteClick: (String) -> Unit,
    onNoteDeleteClick: (String) -> Unit
) {
    ElevatedCard(
        modifier = modifier
            .height(170.dp)
            .padding(top = 4.dp),
        onClick = onClick,
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 8.dp
        )
    ) {
        Column(
            modifier = Modifier
                .padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = note.title,
                color = Color.Black,
                style = MaterialTheme.typography.titleMedium
            )
            HorizontalDivider(
                color = Color.Black,
                modifier = Modifier.padding(vertical = 4.dp),
            )
            Text(
                maxLines = 3,
                text = note.content,
                color = Color.Black,
                style = MaterialTheme.typography.bodySmall
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
                .height(128.dp),
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            IconButton(
                onClick = {
                    onNoteFavoriteClick(note.id)
                },
                modifier = Modifier
                    .padding(4.dp),
            ) {
                Icon(
                    imageVector = if (note.isFavorite) {
                        Icons.Filled.Star
                    } else {
                        Icons.Outlined.StarOutline
                    },
                    contentDescription = "Favorite",
                    tint = if (note.isFavorite) {
                        Color.Yellow
                    } else {
                        Color.Black
                    }
                )
            }

            IconButton(
                onClick = {
                    onNoteDeleteClick(note.id)
                },
                modifier = Modifier
                    .padding(4.dp),
            ) {
                Icon(
                    imageVector = Icons.Filled.Delete,
                    contentDescription = "Delete",
                    tint = Color.Black
                )
            }
        }

    }

}