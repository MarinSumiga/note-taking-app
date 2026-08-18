package com.example.note_taking.notes.presentation.note_list

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.note_taking.R
import com.example.note_taking.notes.presentation.note_list.components.NoteList
import com.example.note_taking.notes.presentation.note_list.components.NoteListFAB
import com.example.note_taking.notes.presentation.note_list.components.NoteSearchBar
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun NoteListScreenRoot(
    modifier: Modifier = Modifier,
    onNoteClick: (String) -> Unit,
    onCreateNoteClick: () -> Unit
){
    val viewModel: NoteListViewModel = koinViewModel()
    val state by viewModel.state.collectAsStateWithLifecycle()
    val lazyGridState = rememberLazyGridState()
    val snackbarHostState = remember { SnackbarHostState() }
    val deletedMessage = stringResource(R.string.note_deleted)

    LaunchedEffect(viewModel, deletedMessage) {
        viewModel.effects.collect { effect ->
            when (effect) {
                NoteListEffect.NoteDeleted -> {
                    snackbarHostState.showSnackbar(deletedMessage)
                }
            }
        }
    }

    NoteListScreen(
        modifier = modifier,
        state = state,
        onAction = viewModel::onAction,
        onNoteClick = onNoteClick,
        onCreateNoteClick = onCreateNoteClick,
        lazyGridState = lazyGridState,
        snackbarHostState = snackbarHostState,
    )
}

@Composable
fun NoteListScreen(
    modifier: Modifier = Modifier,
    state: NoteListState,
    lazyGridState: LazyGridState,
    onAction: (NoteListAction) -> Unit,
    onNoteClick: (String) -> Unit,
    onCreateNoteClick: () -> Unit,
    snackbarHostState: SnackbarHostState = remember { SnackbarHostState() },
){
    val keyboardController = LocalSoftwareKeyboardController.current

    Scaffold(
        modifier = modifier.fillMaxSize(),
        floatingActionButton = {
            NoteListFAB(
                onClick = {
                    onCreateNoteClick()
                }
            )
        },
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
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
                                        onNoteClick(noteId)
                                    },
                                    modifier = Modifier.fillMaxSize(),
                                    scrollState = lazyGridState,
                                    onNoteFavoriteClick = { noteId ->
                                        onAction(NoteListAction.OnNoteFavoriteClick(noteId))
                                    },
                                    onNoteDeleteClick = { noteId ->
                                        onAction(NoteListAction.OnDeleteNoteClick(noteId))
                                    }
                                )
                            }
                        }
                    }
            }
        }
    }
}
