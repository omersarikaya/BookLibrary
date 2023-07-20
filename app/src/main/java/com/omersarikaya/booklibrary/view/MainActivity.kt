package com.omersarikaya.booklibrary.view

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.omersarikaya.booklibrary.viewModel.BookViewModel
import com.omersarikaya.booklibrary.database.AppDatabase
import com.omersarikaya.booklibrary.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        val viewModel = ViewModelProvider(this)[BookViewModel::class.java]
        binding.lifecycleOwner = this
        binding.model = viewModel
        setContentView(binding.root)


        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "Book"
        ).allowMainThreadQueries().build()

        val userDao = db.bookDao()

        binding.btnSave.setOnClickListener {

            viewModel.saveBook(userDao,null,binding.editTitle.text.toString(),
                binding.editAuthor.text.toString(),
                binding.editPages.text.toString(),binding.editPublisher.text.toString())
        }

        binding.btnList.setOnClickListener {
            val bookList = userDao.getAll()
            bookList.forEach { val message = "Kitap kaydedildi: ${it.title} - ${it.author}"
                Toast.makeText(this, message, Toast.LENGTH_SHORT).show() }
        }
    }


}
