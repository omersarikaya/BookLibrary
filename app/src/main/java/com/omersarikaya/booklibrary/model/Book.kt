package com.omersarikaya.booklibrary.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class Book(

   @PrimaryKey val id : Int? = null,
   @ColumnInfo(name="title") val title: String,
   @ColumnInfo (name = "author")val author: String,
   @ColumnInfo (name = "pages") val pages: String,
   @ColumnInfo (name = "publisher") val publisher: String
)
