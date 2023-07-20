package com.omersarikaya.booklibrary.adaptor

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView
import androidx.recyclerview.widget.RecyclerView
import com.omersarikaya.booklibrary.R
import com.omersarikaya.booklibrary.model.Book

class BookAdaptor(private val mList: List<Book>) : RecyclerView.Adapter<BookAdaptor.BookViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_book, parent, false)

        return BookViewHolder(view) //layout ile adaptör birbirine baglandi
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {

        val bookModel = mList[position]

        holder.editTitle.text = bookModel.title
        holder.editAuthor.text = bookModel.author
        holder.editPages.text = bookModel.pages
        holder.editPublisher.text = bookModel.publisher

    }
    class BookViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView){

        val editTitle: TextView = itemView.findViewById(R.id.editTitle)
        val editAuthor: TextView = itemView.findViewById(R.id.editAuthor)
        val editPages: TextView = itemView.findViewById(R.id.editPages)
        val editPublisher: TextView = itemView.findViewById(R.id.editPublisher)
    }


}