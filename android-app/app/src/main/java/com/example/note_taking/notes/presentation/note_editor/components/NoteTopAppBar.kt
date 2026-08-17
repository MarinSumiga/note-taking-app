package com.example.note_taking.notes.presentation.note_editor.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteTopAppBar(
    topAppBarTitle: String,
    navigationIcon: ImageVector,
    onBackClick: ()-> Unit,
    onTitleChange: (String) -> Unit,
    actionsIcon: ImageVector,
    actionsIconDescription: String,
    onActionsIconClick: ()-> Unit

){
    TopAppBar(
        title = {
            OutlinedTextField(
                value = topAppBarTitle,
                onValueChange = onTitleChange,
                modifier = Modifier
                    .fillMaxWidth()
            )
        },
        navigationIcon = {
            IconButton(
                onClick = onBackClick
            ) {
                Icon(
                imageVector = navigationIcon,
                contentDescription = "Back"
            )}
        },
        actions={
            IconButton(
                onClick = onActionsIconClick
            ) {
                Icon(
                    imageVector = actionsIcon,
                    contentDescription = actionsIconDescription
                )
            }
        }
    )
}