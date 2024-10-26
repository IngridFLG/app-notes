package com.ufps.android_notes.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.ufps.android_notes.model.Note

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteDetailsScreen(note: Note, navController: NavHostController) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Detalles de Nota") })
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = note.title, style = MaterialTheme.typography.titleLarge)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = note.content, style = MaterialTheme.typography.bodyMedium)
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = { navController.popBackStack() },
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text("Regresar")
            }
        }
    }
}
