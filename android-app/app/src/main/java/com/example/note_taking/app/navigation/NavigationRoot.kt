package com.example.note_taking.app.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.togetherWith
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import com.example.note_taking.notes.presentation.note_editor.NoteEditorScreenRoot
import com.example.note_taking.notes.presentation.note_editor.NoteEditorScreenMode
import com.example.note_taking.notes.presentation.note_list.NoteListScreenRoot
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
        transitionSpec = {
            (
                    scaleIn(
                        initialScale = 0.92f,
                        animationSpec = tween(durationMillis = 300),
                    ) + fadeIn(
                        animationSpec = tween(durationMillis = 300),
                    )
                    ) togetherWith (
                    scaleOut(
                        targetScale = 1.05f,
                        animationSpec = tween(durationMillis = 250),
                    ) + fadeOut(
                        animationSpec = tween(durationMillis = 250),
                    )
                    )
        },
        popTransitionSpec = {
            (
                    scaleIn(
                        initialScale = 1.05f,
                        animationSpec = tween(durationMillis = 300),
                    ) + fadeIn(
                        animationSpec = tween(durationMillis = 300),
                    )
                    ) togetherWith (
                    scaleOut(
                        targetScale = 0.92f,
                        animationSpec = tween(durationMillis = 250),
                    ) + fadeOut(
                        animationSpec = tween(durationMillis = 250),
                    )
                    )
        },
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


