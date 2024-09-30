package com.bajapuik.simple_note.screen.pin

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bajapuik.simple_note.base.ObserveAsEvent
import com.bajapuik.simple_note.resources.Res
import com.bajapuik.simple_note.resources.enter_pin
import com.bajapuik.simple_note.resources.no_pin
import com.bajapuik.simple_note.screen.pin.events.OnPinEvents
import com.bajapuik.simple_note.screen.pin.events.PinEvents
import com.bajapuik.simple_note.screen.pin.navigation.OnPinNavigationEvents
import kotlinx.coroutines.delay
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel

@Composable
internal fun PinScreen(
    onPinNavigationEvents: OnPinNavigationEvents,
    modifier: Modifier = Modifier,
    viewModel: PinViewModel = koinViewModel()
) {
    LaunchedEffect(Unit) {
        viewModel.getExistPin()
    }

    ObserveAsEvent(
        flow = viewModel.navigatorTarget,
        onEvent = onPinNavigationEvents
    )

    PinContent(
        modifier = modifier,
        pin = viewModel.pin,
        isExistPin = viewModel.isExistPin,
        isErrorPin = viewModel.isErrorPin,
        onPinEvents = viewModel::onEvent
    )
}

@Composable
private fun PinContent(
    pin: String,
    isExistPin: Boolean,
    isErrorPin: Boolean,
    onPinEvents: OnPinEvents,
    modifier: Modifier = Modifier
) {
    val keyboardController = LocalSoftwareKeyboardController.current

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(
            modifier = Modifier
                .padding(
                    top = 24.dp
                )
        )

        Text(
            text = if (isExistPin) {
                stringResource(
                    resource = Res.string.enter_pin
                )
            } else {
                stringResource(
                    resource = Res.string.no_pin
                )
            },
            modifier = Modifier
                .fillMaxWidth(),
            textAlign = TextAlign.Center
        )

        Spacer(
            modifier = Modifier
                .padding(
                    top = 16.dp
                )
        )

        PinTextField(
            modifier = Modifier
                .fillMaxWidth(),
            pinText = pin,
            isError = isErrorPin
        ) { value, pinInputFilled ->
            if (pinInputFilled) {
                keyboardController?.hide()
            }

            onPinEvents(
                PinEvents.OnPinChange(
                    value = value,
                    isComplete = pinInputFilled
                )
            )
        }
    }
}

@Composable
private fun PinTextField(
    modifier: Modifier = Modifier,
    pinText: String,
    pinCount: Int = 6,
    isError: Boolean = false,
    onOtpTextChange: (String, Boolean) -> Unit
) {
    val focusRequester = remember { FocusRequester() }

    LaunchedEffect(Unit) {
        delay(300)
        focusRequester.requestFocus()
    }

    BasicTextField(
        modifier = modifier
            .focusRequester(focusRequester),
        value = TextFieldValue(
            text = pinText,
            selection = TextRange(pinText.length)
        ),
        onValueChange = {
            if (it.text.length <= pinCount) {
                onOtpTextChange.invoke(it.text, it.text.length == pinCount)
            }
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.NumberPassword,
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions(
            onDone = null
        ),
        decorationBox = {
            Row(horizontalArrangement = Arrangement.Center) {
                repeat(pinCount) { index ->
                    CharView(
                        index = index,
                        text = pinText,
                        isError = isError
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                }
            }
        }
    )
}

@Composable
private fun CharView(
    index: Int,
    text: String,
    isError: Boolean = false
) {
    val isFocused = text.length == index
    val char = when {
        index == text.length -> ""
        index > text.length -> ""
        else -> text[index].toString()
    }

    val colors = if (isError) {
        Color(0xFFB00020)
    } else {
        when {
            isFocused -> Color(0xFF60626C)
            else -> Color(0xFFB5B6BA)
        }
    }

    Text(
        modifier = Modifier
            .width(40.dp)
            .border(
                width = 1.dp,
                color = colors,
                shape = RoundedCornerShape(8.dp)
            )
            .padding(2.dp),
        text = char,
        style = MaterialTheme.typography.h4.copy(
            fontSize = 18.sp
        ),
        color = colors,
        textAlign = TextAlign.Center
    )
}