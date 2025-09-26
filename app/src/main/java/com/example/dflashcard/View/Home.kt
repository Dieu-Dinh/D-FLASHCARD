package com.example.dflashcard.View

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.dflashcard.ViewModel.HomeVM
import androidx.compose.ui.Alignment
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.res.painterResource
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.ui.text.font.FontWeight
import com.example.dflashcard.R
import androidx.compose.material3.Icon
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.foundation.lazy.items
@Composable
fun Home( navController: NavHostController, viewModel : HomeVM = viewModel()) {
    // Home Screen Content
    Scaffold (
        topBar = {topHomeBar()},
        bottomBar = { bottomHomeBar(navController) },
        floatingActionButton = { AddFlashCardButton() }
    ){ innerPadding ->
        Column (
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp)
                .fillMaxSize()

        ) {
            SearchBar()

            Spacer(modifier = Modifier.height(16.dp))

            SectionTitle(title = "THỂ LOẠI")

            LazyRow {
                items(10) { index ->
                    // Replace with your category item composable
                    //CategoryItem()
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            SectionTitle(title = "FLASHCARD NHIỀU NGƯỜI HỌC")

            LazyRow {
                items(10) { index ->
                    // Replace with your flashcard item composable
                   // FlashCardItem()
                }

            }
            
        }

    }

}

@Composable
fun topHomeBar() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ){
        Image(
            painterResource(id = R.drawable.logo),
            contentDescription = "Logo Flash_Card",
            modifier = Modifier.padding(48.dp)
        )

        Text ("HELLO {userName}", fontWeight = FontWeight.Bold)

        Image(
            painterResource(id = R.drawable.avatar),
            contentDescription = "Profile Avatar",
            modifier = Modifier.padding(48.dp)
        )
    }
}

@Composable
fun bottomHomeBar(navController: NavHostController) {
    NavigationBar {
        NavigationBarItem(
            selected = true,
            onClick = { /* Handle home click */ },
            icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
            )
        NavigationBarItem(
            selected = true,
            onClick = { /* Handle stats click */ },
            icon = { Icon(Icons.Default.Add, contentDescription = "Add") },
            )

        NavigationBarItem(
            selected = true,
            onClick = { /* Handle stats click */ },
            icon = { Icon(Icons.Default.Menu, contentDescription = "Menu") },
        )

    }
}

@Composable
fun AddFlashCardButton() {
    FloatingActionButton(onClick = {
        // Navigate to Add Flashcard Screen
    }) {
        Icon(Icons.Default.Add, contentDescription = "Add Flashcard")
    }
}

@Composable
fun SearchBar() {
    // Implement Search Bar UI here
    var searchText by remember { mutableStateOf("") }

    OutlinedTextField(
        value = searchText,
        onValueChange = {searchText =  it },
        label = { Text("Search") },
        trailingIcon = {
            Icon(Icons.Default.Search, contentDescription = "Search")
        },
        modifier = Modifier.fillMaxWidth().padding(8.dp)
    )
}


@Composable
fun SectionTitle(title: String) {
    Text(
        text = title,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(vertical = 8.dp)
    )

}

