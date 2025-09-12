package com.example.dflashcard.ViewModel

import androidx.lifecycle.ViewModel
import com.example.dflashcard.Model.IntroPage
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow


class IntroViewModel : ViewModel() {
    private val _pages : List<IntroPage> = listOf(
        IntroPage(
            Text = "Học mọi thú dễ dàng với FLASH CARD \n\n" +
                    "Ghi nhớ từ vựng, kiến thức...\n\n" +
                    "Hay bất cứ thứ gì\n\n" +
                    "Một cách dễ dàng qua từng thẻ",
            ButtonText = "Bắt đầu"
        ),

        IntroPage(
            Text = "Với kĩ thuật lặp lại ngắt quảng \n\n" +
                    "D-FLASHCARD sẽ luôn đồng hành cùng bạn\n" +
                    "nhắc lại những kiến thức quan trong đúng thời điểm - để bạn nhớ lâu, học sâu và tiến bộ mỗi ngày",
            ButtonText = "Tiếp tục"
        ),

        IntroPage(
            Text = "Hãy tạo thẻ cho riêng bạn ",
            ButtonText = "Bắt đầu",
            UseSpecialFont = true
        )

    )

    private val _pageIndex = MutableStateFlow(0)
    val pageIndex = _pageIndex.asStateFlow()

    val pages: List<IntroPage> get()  = _pages

    fun nextPage () {
        if(_pageIndex.value < _pages.lastIndex) {
            _pageIndex.value += 1
        }
        else {
            // logic diu huong man hinh
        }
    }


}
