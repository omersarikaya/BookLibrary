package com.omersarikaya.booklibrary.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.omersarikaya.booklibrary.model.Book

class BookViewModel : ViewModel() {
    val bookLiveData: MutableLiveData<Book> = MutableLiveData()

    fun saveBook(title: String, author: String, pages: String, publisher: String) {
        val book = Book(title, author, pages, publisher)
        bookLiveData.value = book
    }
}

