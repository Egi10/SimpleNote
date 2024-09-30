package com.bajapuik.simple_note.screen.editor

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bajapuik.simple_note.base.ObserveAsEvent
import com.bajapuik.simple_note.screen.editor.events.EditorEvents
import com.bajapuik.simple_note.screen.editor.events.OnEditorEvents
import com.bajapuik.simple_note.screen.editor.navigation.OnEditorNavigationsEvents
import org.koin.compose.viewmodel.koinViewModel

@Composable
internal fun EditorScreen(
    id: Int?,
    title: String?,
    content: String?,
    onEditorNavigationsEvents: OnEditorNavigationsEvents,
    modifier: Modifier = Modifier,
    viewModel: EditorViewModel = koinViewModel()
) {
    LaunchedEffect(
        key1 = title,
        key2 = content
    ) {
        if (title != null) {
            viewModel.onEvent(
                event = EditorEvents.OnTitleInputChange(
                    value = title
                )
            )
        }

        if (content != null) {
            viewModel.onEvent(
                event = EditorEvents.OnContentInputChange(
                    value = content
                )
            )
        }
    }

    ObserveAsEvent(
        flow = viewModel.navigatorTarget,
        onEvent = onEditorNavigationsEvents
    )

    Scaffold(
        modifier = modifier,
        topBar = {
            EditorTopAppBar(
                id = id,
                onEditorEvents = viewModel::onEvent
            )
        },
        content = { paddingValues ->
            EditorContent(
                title = viewModel.title,
                content = viewModel.content,
                onEditorEvents = viewModel::onEvent,
                modifier = Modifier
                    .padding(paddingValues = paddingValues)
            )
        }
    )
}

@Composable
private fun EditorTopAppBar(
    id: Int?,
    onEditorEvents: OnEditorEvents
) {
    TopAppBar(
        backgroundColor = Color.White,
        title = { },
        navigationIcon = {
            NotesIconButton(
                onClick = {
                    onEditorEvents(
                        EditorEvents.OnBackClick
                    )
                },
                imageVector = Icons.AutoMirrored.Filled.ArrowBack
            )
        },
        actions = {
            if (id != null) {
                NotesIconButton(
                    onClick = {
                        onEditorEvents(
                            EditorEvents.OnDeleteNoteClick(
                                id = id
                            )
                        )
                    },
                    imageVector = Icons.Default.Delete
                )
            }

            NotesIconButton(
                    onClick = {
                        onEditorEvents(
                            EditorEvents.OnSaveNoteClick(
                                id = id
                            )
                        )
                    },
                    imageVector = if (id == null) {
                        Icons.Default.Add
                    } else {
                        Icons.Default.Edit
                    }
                )
        },
        elevation = 0.dp
    )
}

@Composable
private fun EditorContent(
    title: String,
    content: String,
    onEditorEvents: OnEditorEvents,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(
                color = Color.White
            )
            .padding(
                all = 16.dp
            )
    ) {
        NotesTextField(
            value = title,
            onValueChange = { value ->
                onEditorEvents(
                    EditorEvents.OnTitleInputChange(
                        value = value
                    )
                )
            },
            textStyle = TextStyle(
                fontSize = 24.sp,
                color = Color.Gray
            ),
            placeholder = "Title"
        )

        NotesTextField(
            value = content,
            onValueChange = { value ->
                onEditorEvents(
                    EditorEvents.OnContentInputChange(
                        value = value
                    )
                )
            },
            placeholder = "Note",
            textStyle = TextStyle(
                fontSize = 16.sp,
                color = Color.Gray
            )
        )
    }
}

@Composable
private fun NotesTextField(
    value: String,
    placeholder: String,
    textStyle: TextStyle = TextStyle.Default,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    BasicTextField(
        value = value,
        onValueChange = onValueChange,
        textStyle = textStyle,
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 8.dp),
        decorationBox = { innerTextField ->
            if (value.isEmpty()) {
                Text(
                    text = placeholder,
                    style = textStyle
                )
            }
            innerTextField()
        }
    )
}

@Composable
private fun NotesIconButton(
    onClick: () -> Unit,
    imageVector: ImageVector,
    modifier: Modifier = Modifier,
) {
    IconButton(
        onClick = onClick,
        modifier = modifier
    ) {
        Icon(
            imageVector = imageVector,
            contentDescription = "Back"
        )
    }
}