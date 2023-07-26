package com.omersarikaya.booklibrary.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.omersarikaya.booklibrary.dao.BookDao
import com.omersarikaya.booklibrary.model.Book

class BookViewModel : ViewModel() {
    val bookLiveData: MutableLiveData<List<Book>> = MutableLiveData()

    fun saveBook(
        userDao: BookDao,
        id: Int?,
        title: String,
        author: String,
        pages: String,
        publisher: String
    ) {
        val book = Book(id,title, author, pages, publisher)
        userDao.insertAll(book)
        bookLiveData.value = bookLiveData.value?.plus(book)
    }

}

