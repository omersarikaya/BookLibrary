package com.omersarikaya.booklibrary.databinding

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.RoomDatabase
import com.omersarikaya.booklibrary.adaptor.BookAdaptor
import com.omersarikaya.booklibrary.model.Book

@BindingAdapter("setData")
fun setData(view:RecyclerView,data:List<Book>){
    view.layoutManager = LinearLayoutManager(view.context)

    // This will pass the ArrayList to our Adapter
    val adapter = BookAdaptor(RoomDatabase)

    // Setting the Adapter with the recyclerview
    view.adapter = adapter
}