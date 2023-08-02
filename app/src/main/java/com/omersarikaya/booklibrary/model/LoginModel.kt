package com.omersarikaya.booklibrary.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class LoginModel (
    @PrimaryKey val id: Int? = null,
    @ColumnInfo(name = "username")val userName: String,
   @ColumnInfo(name = "password") val password: String,
)