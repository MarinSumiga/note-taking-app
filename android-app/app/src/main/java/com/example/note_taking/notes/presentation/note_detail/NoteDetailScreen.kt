package com.example.note_taking.notes.presentation.note_detail

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
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun NoteDetailScreenRoot(
    viewModel : NoteDetailViewModel = koinViewModel(),
    noteId: String,
    onBackClick : () -> Unit,
){
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(noteId) {
        viewModel.loadNote(noteId)
    }

    NoteDetailScreen(
        state = state,
        onAction = { action ->
            viewModel.OnAction (action)
        },
        onBackClick = onBackClick
    )
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteDetailScreen(
    state: NoteDetailState,
    onAction: (NoteDetailAction) -> Unit,
    onBackClick: ()-> Unit
){
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Text(state.noteTitle)
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                actions = {
                    IconButton(onClick = {}){
                        Icon(
                            imageVector = Icons.Filled.Save,
                            contentDescription = "More"
                        )
                    }
                }
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
                            onAction(NoteDetailAction.OnContentChange(it))
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