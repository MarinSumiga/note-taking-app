package com.example.note_taking.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import com.example.note_taking.notes.presentation.note_editor.NoteEditorScreenRoot
import com.example.note_taking.notes.presentation.note_editor.NoteEditorScreenMode
import com.example.note_taking.notes.presentation.note_list.NoteListScreenRoot
import kotlin.collections.listOf


@Composable
fun NavigationRoot(
){
    val backStack = rememberNavBackStack(NoteListScreenRoute)

    val navigateBack: ()-> Unit = {
        backStack.removeLastOrNull()
    }

    val navigateToNoteEditor: (String) -> Unit = {
        backStack.addLast(NoteEditorScreenRoute(mode = NoteEditorScreenMode.Edit(it)))
    }

    val navigateToCreateNote: () -> Unit = {
        backStack.addLast(NoteEditorScreenRoute(mode=NoteEditorScreenMode.Create))
    }

    NavDisplay(
        backStack = backStack,
        onBack = navigateBack,
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
                            onNoteClick = navigateToNoteEditor,
                            onCreateNoteClick= navigateToCreateNote,
                        )
                    }
                }
                is NoteEditorScreenRoute -> {
                    NavEntry(
                        key = key,
                    ){
                        NoteEditorScreenRoot(
                            editorMode = key.mode,
                            onBack = navigateBack
                        )
                    }
                }
                else -> throw RuntimeException("Invalid NavKey.")
            }
        }
    )
}


