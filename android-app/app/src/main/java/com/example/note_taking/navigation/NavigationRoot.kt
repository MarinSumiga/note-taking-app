package com.example.note_taking.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import com.example.note_taking.notes.presentation.note_editor.NoteEditorScreenRoot
import com.example.note_taking.notes.presentation.note_editor.NoteEditorScreenMode
import com.example.note_taking.notes.presentation.note_list.NoteListScreenRoot
import java.util.Map.entry
import kotlin.collections.listOf


@Composable
fun NavigationRoot(
    modifier: Modifier = Modifier,
) {
    val backStack = rememberNavBackStack(Route.NoteListScreenRoute)

    val navigateBack: () -> Unit = {
        backStack.removeLastOrNull()
    }

    val navigateToNoteEditor: (String) -> Unit = {
        backStack.addLast(Route.NoteEditorScreenRoute(mode = NoteEditorScreenMode.Edit(it)))
    }

    val navigateToCreateNote: () -> Unit = {
        backStack.addLast(Route.NoteEditorScreenRoute(mode = NoteEditorScreenMode.Create))
    }

    NavDisplay(
        modifier = modifier,
        backStack = backStack,
        onBack = navigateBack,
        entryDecorators = listOf(
            rememberSaveableStateHolderNavEntryDecorator(),
            rememberViewModelStoreNavEntryDecorator(),
        ),
        entryProvider =
            entryProvider {
                entry<Route.NoteEditorScreenRoute> {
                    NoteEditorScreenRoot(
                        editorMode = it.mode,
                        onBack = navigateBack
                    )
                }

                entry<Route.NoteListScreenRoute> {
                    NoteListScreenRoot(
                        onNoteClick = navigateToNoteEditor,
                        onCreateNoteClick = navigateToCreateNote
                    )
                }
            }
    )
}


