package com.example.note_taking.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import com.example.note_taking.notes.presentation.note_detail.NoteDetailScreenRoot
import com.example.note_taking.notes.presentation.note_list.NoteListScreenRoot
import kotlin.collections.listOf


@Composable
fun NavigationRoot(
){
    val backStack = rememberNavBackStack(NoteListScreenRoute)

    val onBack: ()-> Unit = {
        backStack.removeLastOrNull()
    }
    val onNoteClick: (String) -> Unit = {
        backStack.addLast(NoteDetailScreenRoute(noteId = it))
    }

    NavDisplay(
        backStack = backStack,
        onBack = onBack,
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
                            onNoteClick = onNoteClick
                        )
                    }
                }
                is NoteDetailScreenRoute -> {
                    NavEntry(
                        key = key,
                    ){
                        NoteDetailScreenRoot(
                            noteId = key.noteId,
                            onBackClick = onBack
                        )
                    }
                }
                else -> throw RuntimeException("Invalid NavKey.")
            }
        }
    )
}


