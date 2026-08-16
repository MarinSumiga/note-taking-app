package com.example.note_taking.notes.presentation.composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.note_taking.notes.domain.Note
import com.example.note_taking.ui.theme.BrighBrown

@Composable
fun NoteListItem (
    note: Note,
    onClick: () -> Unit,
){
    Surface(
        shape = RoundedCornerShape(24.dp),
        modifier = Modifier
            .clickable (
                onClick = onClick
            ),
        color = BrighBrown,
        border = BorderStroke(1.dp, Color.Black)
    ){
        Column (
            modifier = Modifier
                .padding(12.dp)
                .width(100.dp)
                .height(150.dp),
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
    }
}