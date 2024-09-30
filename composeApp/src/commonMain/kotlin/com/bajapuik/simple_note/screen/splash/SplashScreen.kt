package com.bajapuik.simple_note.screen.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.bajapuik.simple_note.resources.Res
import io.github.alexzhirkevich.compottie.LottieCompositionSpec
import io.github.alexzhirkevich.compottie.animateLottieCompositionAsState
import io.github.alexzhirkevich.compottie.rememberLottieComposition
import io.github.alexzhirkevich.compottie.rememberLottiePainter
import org.jetbrains.compose.resources.ExperimentalResourceApi

@OptIn(ExperimentalResourceApi::class)
@Composable
internal fun SplashScreen(
    onNavigateHome: () -> Unit
) {
    val composition by rememberLottieComposition {
        LottieCompositionSpec.JsonString(
            Res.readBytes("files/notes_lottie_json.json").decodeToString()
        )
    }
    val progress by animateLottieCompositionAsState(composition)

    LaunchedEffect(key1 = progress) {
        if (progress == 1.0f) {
            onNavigateHome.invoke()
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = Color.White
            )
    ) {
        Image(
            modifier = Modifier
                .align(
                    alignment = Alignment.Center
                ),
            painter = rememberLottiePainter(
                composition = composition,
                progress = {
                    progress
                }
            ),
            contentDescription = null
        )
    }
}