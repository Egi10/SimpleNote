package com.bajapuik.simple_note.screen.main

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.bajapuik.simple_note.base.ObserveAsEvent
import com.bajapuik.simple_note.domain.model.Notes
import com.bajapuik.simple_note.resources.Res
import com.bajapuik.simple_note.resources.ic_add
import com.bajapuik.simple_note.screen.main.events.MainEvents
import com.bajapuik.simple_note.screen.main.events.OnMainEvents
import com.bajapuik.simple_note.screen.main.navigation.OnMainNavigationsEvents
import com.bajapuik.simple_note.screen.main.state.NotesUiState
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel

@Composable
internal fun MainScreen(
    onMainNavigationsEvents: OnMainNavigationsEvents,
    modifier: Modifier = Modifier,
    viewModel: MainViewModel = koinViewModel()
) {
    val notesState by viewModel.notesState.collectAsStateWithLifecycle()

    ObserveAsEvent(
        flow = viewModel.navigatorTarget,
        onEvent = onMainNavigationsEvents
    )

    Scaffold(
        modifier = modifier,
        content = { paddingValues ->
            NotesContent(
                modifier = Modifier
                    .padding(
                        paddingValues = paddingValues
                    ),
                notesUiState = notesState,
                onMainEvents = viewModel::onEvent
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                modifier = Modifier
                    .size(60.dp),
                backgroundColor = Color(0xFFE8505B),
                onClick = {
                    viewModel.onEvent(
                        event = MainEvents.OnAddClick
                    )
                },
                content = {
                    Icon(
                        painter = painterResource(Res.drawable.ic_add),
                        contentDescription = null,
                        tint = Color.White
                    )
                }
            )
        },
        floatingActionButtonPosition = FabPosition.Center
    )
}

@Composable
private fun NotesContent(
    notesUiState: NotesUiState,
    onMainEvents: OnMainEvents,
    modifier: Modifier = Modifier
) {
    when (notesUiState) {
        NotesUiState.Loading -> {
            LoadingIndicator()
        }

        NotesUiState.Empty -> {
            EmptyState()
        }

        is NotesUiState.Success -> {
            LazyVerticalStaggeredGrid(
                modifier = modifier,
                columns = StaggeredGridCells.Fixed(2),
                verticalItemSpacing = 13.dp,
                horizontalArrangement = Arrangement.spacedBy(
                    space = 19.dp
                ),
                contentPadding = PaddingValues(
                    start = 33.dp,
                    end = 36.dp,
                    bottom = 39.dp,
                    top = 39.dp
                ),
                content = {
                    itemsIndexed(
                        items = notesUiState.notes,
                        key = { _, note ->
                            note.id
                        }
                    ) { index, note ->
                        NoteCard(
                            note = note,
                            color = colors[index % colors.size],
                            onClickItem = {
                                onMainEvents(
                                    MainEvents.OnItemClick(
                                        note = note
                                    )
                                )
                            }
                        )
                    }
                }
            )
        }

        NotesUiState.Failed -> {
            FailedState(
                onRetry = {
                    onMainEvents(
                        MainEvents.OnRetryNotes
                    )
                }
            )
        }
    }
}

@Composable
private fun LoadingIndicator(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            color = MaterialTheme.colors.primary,
            strokeWidth = 4.dp
        )
    }
}

@Composable
private fun NoteCard(
    note: Notes,
    color: Color,
    onClickItem: (Notes) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .padding(8.dp)
            .fillMaxWidth()
            .shadow(
                shape = RoundedCornerShape(16.dp),
                elevation = 1.dp
            )
            .clickable {
                onClickItem.invoke(note)
            },
        elevation = 4.dp,
        backgroundColor = color
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                modifier = Modifier
                    .fillMaxWidth(),
                text = note.title,
                style = MaterialTheme.typography.h6,
                color = Color.Black,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                modifier = Modifier
                    .fillMaxWidth(),
                text = note.content,
                style = MaterialTheme.typography.body2,
                color = Color.DarkGray,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
private fun EmptyState(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(all = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            imageVector = Icons.Default.Info,
            contentDescription = "Empty",
            modifier = Modifier.size(80.dp),
            tint = Color.Gray
        )

        Spacer(
            modifier = Modifier
                .height(16.dp)
        )
        Text(
            text = "No Content Available",
            color = Color.Gray
        )

        Spacer(
            modifier = Modifier
                .height(8.dp)
        )

        Text(
            text = "It seems like there's nothing here yet.",
            color = Color.Gray
        )
    }
}

@Composable
private fun FailedState(
    onRetry: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(all = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            imageVector = Icons.Default.Person,
            contentDescription = "Error",
            modifier = Modifier.size(80.dp),
            tint = Color.Red
        )

        Spacer(
            modifier = Modifier.height(16.dp)
        )

        Text(
            text = "Something went wrong",
            color = Color.Black
        )

        Spacer(
            modifier = Modifier.height(16.dp)
        )

        Button(onClick = onRetry) {
            Text(text = "Retry")
        }
    }
}


private val colors = listOf(
    Color(0xFFC5CBFF), // Light Purple
    Color(0xFFC4FFCA), // Light Green
    Color(0xFFFBBECF), // Light Pink
    Color(0xFF96F4F4), // Light Cyan
    Color(0xFFFDF3BF), // Light Yellow
    Color(0xFFFEC5FF)  // Light Magenta
)