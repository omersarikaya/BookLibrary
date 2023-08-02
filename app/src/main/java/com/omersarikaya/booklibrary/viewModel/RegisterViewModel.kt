package com.omersarikaya.booklibrary.viewModel

import androidx.lifecycle.ViewModel
import com.omersarikaya.booklibrary.dao.BookDao
import com.omersarikaya.booklibrary.dao.UserDao
import com.omersarikaya.booklibrary.model.Book
import com.omersarikaya.booklibrary.model.LoginModel

class RegisterViewModel:ViewModel() {


    fun registerUser(
        userDao: UserDao,
        id: Int?,
        userName: String,
        password: String
    ) {
        val user = LoginModel(id,userName, password)
        userDao.insertAll(user)
    }

}