package com.omersarikaya.booklibrary.adaptor

import android.app.Dialog
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.omersarikaya.booklibrary.databinding.ItemBookBinding
import com.omersarikaya.booklibrary.model.Book
import com.omersarikaya.booklibrary.BR
import com.omersarikaya.booklibrary.R
import com.omersarikaya.booklibrary.databinding.DialogBookDetailBinding
import com.omersarikaya.booklibrary.view.ListButtonActivity
import com.omersarikaya.booklibrary.view.dialog.FullScreenDialog



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

        fun bind(book: Book?) {
            itemRowBinding.setVariable(BR.model, book)
            itemRowBinding.executePendingBindings()
            itemRowBinding.test.setOnClickListener {
                val alertDialogBuilder = AlertDialog.Builder(itemRowBinding.root.context)
                alertDialogBuilder.setTitle(book?.title)
                //val binding: ItemBookBinding = DataBindingUtil.inflate(
                    //LayoutInflater.from(parent.context),
                  //  com.omersarikaya.booklibrary.R.layout.item_book, parent, false
                //)
               /* alertDialogBuilder.setTitle(book?.title)
                val binding:DialogBookDetailBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context),
                    com.omersarikaya.booklibrary.R.layout
                    .dialog_book_detail,null,false)

                binding.book = book
                 alertDialogBuilder.setView(binding.root)
                 */
                val inflater = LayoutInflater.from(itemView.context)
                val dialogView = inflater.inflate(R.layout.dialog_book_detail,null)
                alertDialogBuilder.setView(dialogView)





                alertDialogBuilder.setMessage("Yazar: ${book?.author}\nSayfa Sayısı: ${book?.pages}\nYayıncı: ${book?.publisher}")
                alertDialogBuilder.setPositiveButton("Tamam") { dialog, _ ->
                    dialog.dismiss()
                }
                val alertDialog = alertDialogBuilder.create()
                alertDialog.show()


                /*
                val alertDialogBuilder = AlertDialog.Builder(it.context)
                alertDialogBuilder.setTitle(book?.title)


                val dialogView = LayoutInflater.from(it.context).inflate(R.layout.dialog_book_detail, null)


                val binding: DialogBookDetailBinding = DialogBookDetailBinding.bind(dialogView)




                alertDialogBuilder.setView(binding.root)


                alertDialogBuilder.setMessage("Yazar: ${binding.author}\nSayfa Sayısı: ${binding.pages}\nYayıncı: ${binding.publisher}")
                alertDialogBuilder.setPositiveButton("Tamam") { dialog, _ ->
                    dialog.dismiss()
                }


                val alertDialog = alertDialogBuilder.create()
                alertDialog.show()*/

            }
        }
    }
}