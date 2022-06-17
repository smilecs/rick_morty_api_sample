package com.smile.presentation.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.smile.presentation.uimodel.CharacterUI

//@Composable
//fun DetailView(characterUI: CharacterUI) {
//    Column(modifier = Modifier.fillMaxWidth()) {
//        Text(characterUI.name, modifier = Modifier.paddingFromBaseline(top = 8.dp))
//    }
//}

@Composable
fun Greeting() {
    Text("Hello name")
}


@Preview
@Composable
fun ShowDetailView() {
    Greeting()
    // DetailView(CharacterUI(1, "Smile", "Immortal", "", "Male", "", null))
}