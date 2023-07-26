package com.omersarikaya.booklibrary.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.omersarikaya.booklibrary.R
import com.omersarikaya.booklibrary.database.AppDatabase
import com.omersarikaya.booklibrary.databinding.ActivityButtonListBinding
import com.omersarikaya.booklibrary.model.Book
import com.omersarikaya.booklibrary.viewModel.ListButtonViewModel

class ListButtonActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityButtonListBinding.inflate(layoutInflater)
        val viewModel = ViewModelProvider(this)[ListButtonViewModel::class.java]
        setContentView(R.layout.activity_button_list)
        binding.lifecycleOwner = this
        binding.model = viewModel
        setContentView(binding.root)
        viewModel.bookLiveData.value = showBookList()
    }

    private fun showBookList(): List<Book> {
        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "Book"
        ).allowMainThreadQueries().build()
        val listDao = db.bookDao()
        return listDao.getAll()
    }
}