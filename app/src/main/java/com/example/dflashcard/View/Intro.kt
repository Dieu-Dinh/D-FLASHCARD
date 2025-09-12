package com.example.dflashcard.View

import android.media.Image
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.dflashcard.R
import com.example.dflashcard.ViewModel.IntroViewModel
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.dflashcard.Navigation.App


@Composable
fun IntroLayout(
    text: String,
    buttonText: String,
    useSpecialFont: Boolean = false,
    onButtonClick: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize().padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Box(
            modifier = Modifier
                .size(200.dp)
                .background(Color(0xFFD98B8B), RoundedCornerShape(24.dp)),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Logo Flash_Card",
                modifier = Modifier.size(100.dp)
            )
        }

        Text(
            text = text,
            fontSize = 16.sp,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Medium,
            fontFamily = if (useSpecialFont) FontFamily.Cursive else FontFamily.Default
        )

        Button(
            onClick = onButtonClick,
            shape = RoundedCornerShape(24.dp),
            modifier = Modifier.fillMaxWidth().height(50.dp)
        ) {
            Text(text = buttonText, fontSize = 16.sp)
        }
    }
}


@Composable
fun IntroPages (viewModel: IntroViewModel = viewModel(), navController: NavController) {
    val pageIndex by viewModel.pageIndex.collectAsState()
    val page = viewModel.pages[pageIndex]

    IntroLayout (
        text = page.Text,
        buttonText = page.ButtonText,
        useSpecialFont = page.UseSpecialFont,
        onButtonClick = {
            if (pageIndex < viewModel.pages.lastIndex) {
                viewModel.nextPage()
            } else {
                navController.navigate("Login") {
                    popUpTo("Intro") { inclusive = true }
                }
            }
        }
    )
}
