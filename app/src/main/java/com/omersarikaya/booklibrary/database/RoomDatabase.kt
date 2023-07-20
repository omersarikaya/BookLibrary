package com.omersarikaya.booklibrary.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.omersarikaya.booklibrary.dao.BookDao
import com.omersarikaya.booklibrary.model.Book

@Database(entities = [Book::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun bookDao(): BookDao
}