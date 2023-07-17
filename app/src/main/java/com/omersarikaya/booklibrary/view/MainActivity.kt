package com.omersarikaya.booklibrary.view

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.omersarikaya.booklibrary.viewModel.BookViewModel
import com.omersarikaya.booklibrary.R
import com.omersarikaya.booklibrary.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        val viewModel = ViewModelProvider(this)[BookViewModel::class.java]
        binding.lifecycleOwner = this
        binding.model = viewModel
        setContentView(binding.root)

        binding.btnSave.setOnClickListener {
            val title = findViewById<EditText>(R.id.editTitle).text.toString()
            val author = findViewById<EditText>(R.id.editAuthor).text.toString()
            val pages = findViewById<EditText>(R.id.editPages).text.toString().toInt()
            val publisher = findViewById<EditText>(R.id.editPublisher).text.toString()

            viewModel.saveBook(binding.editTitle.text.toString(),
                binding.editAuthor.text.toString(),
                binding.editPages.text.toString(),binding.editPublisher.text.toString())
        }

        viewModel.bookLiveData.observe(this, Observer { book ->
            val message = "Kitap kaydedildi: ${book.title} - ${book.author}"
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        })
    }
}
