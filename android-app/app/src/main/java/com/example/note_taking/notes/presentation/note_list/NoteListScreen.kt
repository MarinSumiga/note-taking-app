package com.example.note_taking.notes.presentation.note_list

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
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
import com.example.note_taking.notes.domain.notes
import com.example.note_taking.notes.presentation.composables.NoteList
import com.example.note_taking.notes.presentation.composables.NoteSearchBar
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun NoteListScreenRoot(
    viewModel: NoteListViewModel = koinViewModel(),
    onNoteClick: (String) -> Unit,
){
    val state by viewModel.state.collectAsStateWithLifecycle()

    NoteListScreen(
        state = state,
        onAction = {action ->
            when(action){
                is NoteListAction.OnNoteClick -> onNoteClick(action.id)
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

    LaunchedEffect(key1 = state.searchResults) {
        lazyGridState.animateScrollToItem(0)
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        NoteSearchBar(
            searchQuery = state.searchQuery,
            onSearchQueryChange = {
                onAction(NoteListAction.OnSearchQueryChange(it))
            },
            onSearch = {
                keyboardController?.hide()
            },
            Modifier.padding(16.dp)
                .fillMaxWidth()
        )
        Surface(
            modifier = Modifier
                .weight(1f)
                .padding(12.dp)
                .fillMaxWidth(),
            color = Color.Transparent,
            shape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp)
        ) {
            if (state.isLoading){
                CircularProgressIndicator()
            }else{
                when{
                    state.searchResults.isNotEmpty() ->
                        NoteList(
                        notes = state.searchResults,
                        onNoteClick = { noteId ->
                            onAction(NoteListAction.OnNoteClick(noteId))
                        },
                        modifier = Modifier.fillMaxSize(),
                        scrollState = lazyGridState,
                            onNoteFavoriteClick = { noteId ->
                                onAction(NoteListAction.OnNoteFavoriteClick(noteId))
                            }
                    )
                    state.errorMessage !== null ->{
                        Text(
                            text = state.errorMessage,
                            color = Color.Red,
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                    else -> {
                        NoteList(
                            notes = notes,
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