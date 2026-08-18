package com.example.note_taking.notes.presentation.note_editor.components


import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteTopAppBar(
    modifier: Modifier = Modifier,
    topAppBarTitle: String,
    navigationIcon: ImageVector,
    onBackClick: () -> Unit,
    onTitleChange: (String) -> Unit,
    actionsIcon: ImageVector,
    actionsIconDescription: String,
    onActionsIconClick: () -> Unit,
    isEditingEnabled: Boolean,
    isSavingEnabled: Boolean
) {
    TopAppBar(
        modifier = modifier,
        title = {
            TextField(
                value = topAppBarTitle,
                onValueChange = onTitleChange,
                modifier = Modifier
                    .fillMaxWidth(),
                enabled = isEditingEnabled,
                singleLine = true,
                placeholder = {
                    Text("Title")
                },
                textStyle = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.SemiBold
                ),
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = Color.Transparent,
                    disabledContainerColor = Color.Transparent,
                    focusedContainerColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                )
            )
        },
        navigationIcon = {
            IconButton(
                onClick = onBackClick
            ) {
                Icon(
                    imageVector = navigationIcon,
                    contentDescription = "Back"
                )
            }
        },
        actions = {
            IconButton(
                onClick = onActionsIconClick,
                enabled = isSavingEnabled
            ) {
                Icon(
                    imageVector = actionsIcon,
                    contentDescription = actionsIconDescription
                )
            }
        }
    )
}