package com.example.note_taking.notes.presentation.note_editor

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.note_taking.notes.presentation.components.NoteTopAppBar
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun NoteEditorScreenRoot(
    noteId: String?,
    viewModel : NoteEditorViewModel = koinViewModel {
        parametersOf(noteId)
    },
    onBackClick : () -> Unit,
){
    val state by viewModel.state.collectAsStateWithLifecycle()

    NoteEditorScreen(
        state = state,
        onAction = viewModel::onAction,
        onBackClick = onBackClick
    )
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteEditorScreen(
    state: NoteEditorState,
    onAction: (NoteEditorAction) -> Unit,
    onBackClick: ()-> Unit
){

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            NoteTopAppBar(
                topAppBarTitle = state.noteTitle,
                navigationIcon = Icons.AutoMirrored.Filled.ArrowBack,
                onBackClick = onBackClick,
                onTitleChange = {
                    onAction(NoteEditorAction.OnTitleChange(it))
                },
                actionsIcon = Icons.Filled.Save,
                actionsIconDescription = "save note",
                onActionsIconClick = {
                    onAction(NoteEditorAction.OnSaveClick,)
                },
            )
        }
    ) {innerPadding->
        when{
            state.isLoading ->{
                CircularProgressIndicator()
            }
            state.errorMessage != null ->{
                Text(state.errorMessage)
            }
            else -> {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding),
                    verticalArrangement = Arrangement.Center
                ) {
                    OutlinedTextField(
                        value = state.noteContent,
                        onValueChange = {
                            onAction(NoteEditorAction.OnContentChange(it))
                        },
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxWidth()
                    )
                }
            }
        }
    }
}