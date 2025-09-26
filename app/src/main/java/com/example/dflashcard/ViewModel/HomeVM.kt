package com.example.dflashcard.ViewModel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.dflashcard.Model.Category
import com.example.dflashcard.Model.Flashcard
import androidx.compose.runtime.snapshots.SnapshotStateList
import com.example.dflashcard.R


class HomeVM: ViewModel() {
    //Danh sach flashcard
    private val _categories = mutableStateListOf<Category>()
    val categories: SnapshotStateList<Category> = _categories

    //Danh sach flashcard nhieu nguoi hoc
    private val _flashcards = mutableStateListOf<Flashcard>()
    val flashcards: SnapshotStateList<Flashcard> = _flashcards

    init {
        loadFakeData()
    }

    private fun loadFakeData() {
        _categories.addAll(
            listOf(
                Category(1, "Science", icon = R.drawable.science_icon),
                Category(2, "Math", icon = R.drawable.math_icon),
                Category(3, "History", icon = R.drawable.history_icon),
                Category(4, "Language", icon = R.drawable.language_icon),
                Category(5, "Art", icon = R.drawable.art_icon),
                Category(6, "Geography", icon = R.drawable.geography_icon)
            )
        )

        _flashcards.addAll(
            listOf(
                Flashcard(1, "Biology Basics", learner = 1200),
                Flashcard(2, "Algebra Fundamentals", learner = 950),
                Flashcard(3, "World War II Overview", learner = 800),
                Flashcard(4, "Spanish Vocabulary", learner = 1100),
                Flashcard(5, "Renaissance Art", learner = 600),
                Flashcard(6, "European Capitals", learner = 700)
            )
        )
    }

}