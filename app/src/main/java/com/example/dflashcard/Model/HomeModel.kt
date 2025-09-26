package com.example.dflashcard.Model

data class Category(
    val id: Int,
    val name: String,
    val icon: Int
)

data class Flashcard(
    val id : Int,
    val title: String,
    val learner: Int
)