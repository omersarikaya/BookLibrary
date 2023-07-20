package com.omersarikaya.booklibrary.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.omersarikaya.booklibrary.model.Book


@Dao
interface BookDao {
    @Query("SELECT * FROM book")
    fun getAll(): List<Book>

    @Query("SELECT * FROM book WHERE id IN (:bookIds)")
    fun loadAllByIds(bookIds: IntArray): List<Book>

    @Insert
    fun insertAll(vararg users: Book)

    @Delete
    fun delete(user: Book)
}