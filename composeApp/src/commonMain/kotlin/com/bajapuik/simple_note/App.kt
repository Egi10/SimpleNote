package com.bajapuik.simple_note

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.bajapuik.simple_note.screen.editor.navigation.EditorRoute
import com.bajapuik.simple_note.screen.editor.EditorScreen
import com.bajapuik.simple_note.screen.editor.navigation.EditorNavigationsEvents
import com.bajapuik.simple_note.screen.main.navigation.MainRoute
import com.bajapuik.simple_note.screen.main.MainScreen
import com.bajapuik.simple_note.screen.main.navigation.MainNavigationsEvents
import com.bajapuik.simple_note.screen.pin.PinScreen
import com.bajapuik.simple_note.screen.pin.navigation.PinNavigationEvents
import com.bajapuik.simple_note.screen.pin.navigation.PinRoute
import com.bajapuik.simple_note.screen.splash.SplashRoute
import com.bajapuik.simple_note.screen.splash.SplashScreen

@Composable
fun App() {
    val navController = rememberNavController()

    MaterialTheme {
        NavHost(
            navController = navController,
            startDestination = SplashRoute
        ) {
            composable<SplashRoute> {
                SplashScreen(
                    onNavigateHome = {
                        navController.navigate(PinRoute) {
                            popUpTo<SplashRoute> {
                                inclusive = true
                            }
                        }
                    }
                )
            }

            composable<PinRoute> {
                PinScreen(
                    onPinNavigationEvents = { navigation ->
                        when(navigation) {
                            PinNavigationEvents.OnHomeNavigation -> {
                                navController.navigate(MainRoute) {
                                    popUpTo<PinRoute> {
                                        inclusive = true
                                    }
                                }
                            }
                        }
                    }
                )
            }

            composable<MainRoute> {
                MainScreen(
                    onMainNavigationsEvents = { navigations ->
                        when(navigations) {
                            MainNavigationsEvents.OnAddNoteNavigation -> {
                                navController.navigate(
                                    EditorRoute()
                                ) {
                                    popUpTo<MainRoute> {
                                        inclusive = false
                                    }
                                }
                            }

                            is MainNavigationsEvents.OnSelectNoteNavigation -> {
                                val note = navigations.note

                                navController.navigate(
                                    EditorRoute(
                                        id = note.id,
                                        title = note.title,
                                        content = note.content
                                    )
                                ) {
                                    popUpTo<MainRoute> {
                                        inclusive = false
                                    }
                                }
                            }
                        }
                    }
                )
            }

            composable<EditorRoute> { backStackEntry ->
                val editor = backStackEntry.toRoute<EditorRoute>()

                EditorScreen(
                    id = editor.id,
                    title = editor.title,
                    content = editor.content,
                    onEditorNavigationsEvents = { navigations ->
                        when (navigations) {
                            EditorNavigationsEvents.OnSuccessNavigation -> {
                                navController.popBackStack()
                            }

                            EditorNavigationsEvents.OnBackNavigation -> {
                                navController.popBackStack()
                            }
                        }
                    }
                )
            }
        }
    }
}