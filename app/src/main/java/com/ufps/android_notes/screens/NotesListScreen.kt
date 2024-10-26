package com.ufps.android_notes.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.ufps.android_notes.model.Note

@Composable
fun NotesListScreen(notes: List<Note>, navController: NavHostController) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { navController.navigate("createEditNote") }) {
                Text("+")
            }
        }
    ) { padding ->
        LazyColumn(
            contentPadding = padding,
            modifier = Modifier.fillMaxSize()
        ) {
            items(notes) { note ->
                NoteItem(note = note, navController = navController, index = notes.indexOf(note))
            }
        }
    }
}

@Composable
fun NoteItem(note: Note, navController: NavHostController, index: Int) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        onClick = { navController.navigate("noteDetails/$index") }
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = note.title, style = MaterialTheme.typography.titleLarge)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = note.content, style = MaterialTheme.typography.bodyMedium)
        }
    }
}
