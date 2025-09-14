package com.example.dflashcard.View

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.dflashcard.ViewModel.HomeVM

@Composable
fun Home( navController: NavHostController, ViewModel : HomeVM = viewModel()) {
    // Home Screen Content
    Scaffold (
        topBar = topHomeBar(navController = navController),
        bottomBar = { bottomHomeBar(navController = navController) },
        floatingActionButton = { AddFlashCardButton(navController = navController) }
    ){ innerPadding ->
        Column () {
            // Main content
        }

    }

}

@Composable
fun topHomeBar(navController =  navController) {

}

@Composable
fun bottomHomeBar(navController = navController) {

}

@Composable
fun AddFlashCardButton(navController = navController) {

}