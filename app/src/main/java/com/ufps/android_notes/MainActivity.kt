package com.ufps.android_notes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf // Importación necesaria
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ufps.android_notes.model.Note
import com.ufps.android_notes.screens.CreateEditNoteScreen
import com.ufps.android_notes.screens.NoteDetailsScreen
import com.ufps.android_notes.screens.NotesListScreen

class MainActivity : ComponentActivity() {
    // Lista mutable de notas, utilizada en todas las pantallas
    private val notesList = mutableStateListOf<Note>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NotesApp(notesList)
        }
    }
}

@Composable
fun NotesApp(notesList: MutableList<Note>) {
    val navController = rememberNavController()
    SetupNavGraph(navController = navController, notesList = notesList)
}

@Composable
fun SetupNavGraph(navController: NavHostController, notesList: MutableList<Note>) {
    NavHost(navController = navController, startDestination = "notesList") {
        // Pantalla principal: Lista de Notas
        composable("notesList") {
            NotesListScreen(notes = notesList, navController = navController)
        }

        // Pantalla para Crear/Editar Notas
        composable("createEditNote") {
            CreateEditNoteScreen(navController = navController, notesList = notesList)
        }

        // Pantalla para Detalles de la Nota seleccionada
        composable("noteDetails/{noteIndex}") { backStackEntry ->
            val index = backStackEntry.arguments?.getString("noteIndex")?.toIntOrNull()
            if (index != null && index < notesList.size) {
                NoteDetailsScreen(note = notesList[index], notesList = notesList, navController = navController)
            }
        }
    }
}
