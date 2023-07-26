package com.omersarikaya.booklibrary.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.omersarikaya.booklibrary.model.Book

class ListButtonViewModel : ViewModel() {
    val bookLiveData: MutableLiveData<List<Book>> = MutableLiveData()
}

