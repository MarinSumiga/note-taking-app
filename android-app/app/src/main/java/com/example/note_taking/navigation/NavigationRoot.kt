package com.example.note_taking.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import com.example.note_taking.notes.presentation.note_editor.NoteEditorScreenRoot
import com.example.note_taking.notes.presentation.note_list.NoteListScreenRoot
import kotlin.collections.listOf


@Composable
fun NavigationRoot(
){
    val backStack = rememberNavBackStack(NoteListScreenRoute)

    val onNavigateBack: ()-> Unit = {
        backStack.removeLastOrNull()
    }

    val onNavigateToNoteDetails: (String) -> Unit = {
        backStack.addLast(NoteEditorScreenRoute(noteId = it))
    }

    val onNavigateToCreateNote: () -> Unit = {
        backStack.addLast(NoteEditorScreenRoute(noteId = null))
    }

    NavDisplay(
        backStack = backStack,
        onBack = onNavigateBack,
        entryDecorators = listOf(
            rememberSaveableStateHolderNavEntryDecorator(),
            rememberViewModelStoreNavEntryDecorator(),
        ),
        entryProvider ={ key ->
            when(key){
                is NoteListScreenRoute -> {
                    NavEntry(
                        key = key,
                    ){
                        NoteListScreenRoot(
                            onNoteClick = onNavigateToNoteDetails,
                            onNoteCreateClick = onNavigateToCreateNote
                        )
                    }
                }
                is NoteEditorScreenRoute -> {
                    NavEntry(
                        key = key,
                    ){
                        NoteEditorScreenRoot(
                            noteId = key.noteId,
                            onBackClick = onNavigateBack
                        )
                    }
                }
                else -> throw RuntimeException("Invalid NavKey.")
            }
        }
    )
}


