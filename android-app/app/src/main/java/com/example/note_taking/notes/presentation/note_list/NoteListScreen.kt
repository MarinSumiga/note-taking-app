package com.example.note_taking.notes.presentation.note_list

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.note_taking.notes.presentation.note_list.components.NoteList
import com.example.note_taking.notes.presentation.note_list.components.NoteListFAB
import com.example.note_taking.notes.presentation.note_list.components.NoteSearchBar
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun NoteListScreenRoot(
    onNoteClick: (String) -> Unit,
    onNoteCreateClick: () -> Unit
){
    val viewModel: NoteListViewModel = koinViewModel()

    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(Unit ) {
        viewModel.onAction(NoteListAction.OnRefresh)
    }

    NoteListScreen(
        state = state,
        onAction = {action ->
            when(action){
                is NoteListAction.OnNoteClick -> onNoteClick(action.id)
                is NoteListAction.OnCreateNewNoteClick -> onNoteCreateClick()
                else -> Unit
            }
            viewModel.onAction(action)
        },

    )
}

@Composable
fun NoteListScreen(
    state: NoteListState,
    onAction: (NoteListAction) -> Unit,
){
    val keyboardController = LocalSoftwareKeyboardController.current
    val lazyGridState = rememberLazyGridState()

    LaunchedEffect(key1 = state.notes) {
        lazyGridState.animateScrollToItem(0)
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        floatingActionButton = {
            NoteListFAB(
                onClick = {
                    onAction(NoteListAction.OnCreateNewNoteClick)
                }
            )
        },

    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            NoteSearchBar(
                searchQuery = "",
                onSearchQueryChange = {
                    onAction(NoteListAction.OnSearchQueryChange(it))
                },
                onSearch = {
                    keyboardController?.hide()
                },
                Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
            )
                Box(
                    modifier = Modifier.padding(16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    if (state.isLoading) {
                        CircularProgressIndicator()
                    } else {
                        when {
                            state.errorMessage !== null -> {
                                Text(
                                    text = state.errorMessage,
                                    color = Color.Red,
                                    style = MaterialTheme.typography.bodyMedium
                                )
                            }
                            else -> {
                                NoteList(
                                    notes = state.notes,
                                    onNoteClick = { noteId ->
                                        onAction(NoteListAction.OnNoteClick(noteId))
                                    },
                                    modifier = Modifier.fillMaxSize(),
                                    scrollState = lazyGridState,
                                    onNoteFavoriteClick = { noteId ->
                                        onAction(NoteListAction.OnNoteFavoriteClick(noteId))
                                    }
                                )
                            }
                        }
                    }
            }
        }
    }
}