package com.omersarikaya.booklibrary.adaptor

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.omersarikaya.booklibrary.databinding.ItemBookBinding
import com.omersarikaya.booklibrary.model.Book
import com.omersarikaya.booklibrary.BR


class BookAdaptor(private var mList: List<Book>) :
    RecyclerView.Adapter<BookAdaptor.BookViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {

        val binding: ItemBookBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            com.omersarikaya.booklibrary.R.layout.item_book, parent, false
        )

        return BookViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    //Data binding ile oluşturuyoruz eskisi gibi değil artık aşağıdaki linkleri inceleyebilirsin
    //https://medium.com/huawei-developers/how-to-use-recyclerview-with-databinding-mvvm-211f6b69a81a
    //https://www.digitalocean.com/community/tutorials/android-recyclerview-data-binding
    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {

        val dataModel: Book = mList[position]
        holder.bind(dataModel)
    }

    class BookViewHolder(private val itemRowBinding: ItemBookBinding) :
        RecyclerView.ViewHolder(itemRowBinding.root) {

        fun bind(obj: Book?) {
            itemRowBinding.setVariable(BR.model, obj)
            itemRowBinding.executePendingBindings()
        }
    }
}