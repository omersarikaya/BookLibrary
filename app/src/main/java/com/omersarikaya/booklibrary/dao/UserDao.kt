package com.omersarikaya.booklibrary.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.omersarikaya.booklibrary.model.LoginModel


@Dao
interface UserDao {
    @Query("SELECT * FROM loginmodel")
    fun getAll(): List<LoginModel>

    @Query("SELECT * FROM loginmodel WHERE password IN (:password) AND username IN (:userName)")
    fun checkPassword(password: String , userName: String): Boolean

    @Query("SELECT * FROM loginmodel WHERE username IN (:userName)")
    fun checkUserName( userName: String): Boolean



    @Insert
    fun insertAll(vararg user: LoginModel)


}