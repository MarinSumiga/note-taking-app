package com.example.note_taking.notes.presentation.note_editor

import androidx.activity.compose.BackHandler
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.note_taking.notes.presentation.note_editor.components.NoteEditorContentField
import com.example.note_taking.notes.presentation.note_editor.components.NoteTopAppBar
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun NoteEditorScreenRoot(
    modifier: Modifier = Modifier,
    editorMode: NoteEditorScreenMode,
    onBack: () -> Unit,
) {
    val viewModel: NoteEditorViewModel = koinViewModel {
        parametersOf(editorMode)
    }

    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(viewModel) {
        viewModel.effects.collect { effect ->
            when (effect) {
                NoteEditorEffect.NavigateBack -> onBack()
            }
        }
    }

    BackHandler {
        viewModel.onAction(NoteEditorAction.OnBackClick)
    }

    NoteEditorScreen(
        modifier=modifier,
        state = state,
        onAction = viewModel::onAction
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteEditorScreen(
    modifier: Modifier = Modifier,
    state: NoteEditorState,
    onAction: (NoteEditorAction) -> Unit,
) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.surface,
        topBar = {
            NoteTopAppBar(
                topAppBarTitle = state.note?.title.orEmpty(),
                navigationIcon = Icons.AutoMirrored.Filled.ArrowBack,
                onBackClick = {
                    onAction(NoteEditorAction.OnBackClick)
                },
                onTitleChange = {
                    onAction(NoteEditorAction.OnTitleChange(it))
                },
                actionsIcon = Icons.Filled.Save,
                actionsIconDescription = "save note",
                onActionsIconClick = {
                    onAction(NoteEditorAction.OnSaveClick)
                },
                isEditingEnabled = !state.isLoading && !state.isSaving,
                isSavingEnabled = state.hasUnsavedChanges && !state.isSaving
            )
        }
    ) { innerPadding ->
        when {
            state.isLoading -> {
                CircularProgressIndicator()
            }

            state.errorMessage != null -> {
                Text(state.errorMessage)
            }

            else -> {
                NoteEditorContentField(
                    value = state.note?.content.orEmpty(),
                    onValueChange = {
                        onAction(NoteEditorAction.OnContentChange(it))
                    },
                    modifier = Modifier.padding(innerPadding)
                )
            }
        }
    }
}