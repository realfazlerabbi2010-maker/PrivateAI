package com.privateai.app.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PrivateAIScreen() {
    var textState by remember { mutableStateOf("") }
    val messages = remember { mutableStateListOf<Pair<String, Boolean>>() }

    Scaffold(
        topBar = { TopAppBar(title = { Text("Private Offline AI") }) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                reverseLayout = true
            ) {
                items(messages.reversed()) { message ->
                    val alignment = if (message.second) Alignment.End else Alignment.Start
                    Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = alignment) {
                        Card(
                            modifier = Modifier.padding(vertical = 4.dp)
                        ) {
                            Text(text = message.first, modifier = Modifier.padding(8.dp))
                        }
                    }
                }
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextField(
                    value = textState,
                    onValueChange = { textState = it },
                    modifier = Modifier.weight(1f),
                    placeholder = { Text("Type a message...") }
                )
                Spacer(modifier = Modifier.width(8.dp))
                Button(onClick = {
                    if (textState.isNotBlank()) {
                        messages.add(Pair(textState, true))
                        messages.add(Pair("AI: Processing offline...", false))
                        textState = ""
                    }
                }) {
                    Text("Send")
                }
            }
        }
    }
}
