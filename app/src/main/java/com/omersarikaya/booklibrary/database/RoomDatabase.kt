package com.omersarikaya.booklibrary.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.omersarikaya.booklibrary.dao.BookDao
import com.omersarikaya.booklibrary.dao.UserDao
import com.omersarikaya.booklibrary.model.Book
import com.omersarikaya.booklibrary.model.LoginModel

@Database(entities = [Book::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun bookDao(): BookDao
}

@Database(entities = [LoginModel::class], version = 1)
abstract class AppDatabase2 : RoomDatabase() {
    abstract fun userDao(): UserDao
}

