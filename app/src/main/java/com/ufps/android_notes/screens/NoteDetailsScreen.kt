package com.ufps.android_notes.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.ufps.android_notes.model.Note

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteDetailsScreen(note: Note, notesList: MutableList<Note>, navController: NavHostController) {
    var title by remember { mutableStateOf(note.title) }
    var content by remember { mutableStateOf(note.content) }
    val index = notesList.indexOfFirst { it.title == note.title && it.content == note.content }
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Editar Nota") })
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.Center
        ) {
            OutlinedTextField(
                value = title,
                onValueChange = { title = it },
                label = { Text("Título") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = content,
                onValueChange = { content = it },
                label = { Text("Contenido") },
                modifier = Modifier.fillMaxWidth().height(150.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {
                    // Actualiza la nota en la lista
                    if (index != -1) {
                        notesList[index] = note.copy(title = title, content = content)
                    }
                    navController.popBackStack()
                },
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text("Guardar cambios")
            }
        }
    }
}
