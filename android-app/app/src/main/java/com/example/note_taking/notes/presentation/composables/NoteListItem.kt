package com.example.note_taking.notes.presentation.composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.StarOutline
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
    onNoteFavoriteClick: (String) -> Unit,
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
        Box(
            modifier = Modifier,
        ){
            Column (
                modifier = Modifier
                    .padding(12.dp)
                    .height(128.dp),
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

            IconButton(
                onClick ={
                    onNoteFavoriteClick(note.id)
                },
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(4.dp),
            ){
                Icon(
                    imageVector = if(note.isFavorite){
                        Icons.Filled.Star
                    }else{
                        Icons.Outlined.StarOutline
                    },
                    contentDescription = "Favorite",
                    tint = if (note.isFavorite){
                        Color.Yellow
                    }else{
                        Color.LightGray
                    }
                )
            }
        }
    }
}