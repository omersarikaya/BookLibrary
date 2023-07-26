package com.omersarikaya.booklibrary.view

import android.content.Intent
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

    lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
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

            if(isFormValid()){
                viewModel.saveBook(userDao,null,binding.editTitle.text.toString(),
                    binding.editAuthor.text.toString(),
                    binding.editPages.text.toString(),binding.editPublisher.text.toString())

                val toast = Toast.makeText(applicationContext,"Saved", Toast.LENGTH_SHORT)
                toast.show()
                clearEditText()

            }
            else{
                val toast = Toast.makeText(applicationContext,"Please fill all fields",Toast.LENGTH_SHORT)
                toast.show()
            }


        }

        binding.btnList.setOnClickListener {
            //val bookList = userDao.getAll()
            //viewModelin içindeki kital listesini butona tıklanınca veritabınından dolduruyoruz.
            //viewModel.bookLiveData.value = bookList
            val intent = Intent(this,ListButtonActivity::class.java)
            startActivity(intent)
        }
    }

    private fun clearEditText(){
        binding.editTitle.text = null
        binding.editAuthor.text=null
        binding.editPages.text=null
        binding.editPublisher.text=null

    }

    private fun isFormValid(): Boolean{
        val title = binding.editTitle.text.toString()
        val author = binding.editAuthor.text.toString()
        val pages = binding.editPages.text.toString()
        val publisher = binding.editPublisher.text.toString()

        return title.isNotBlank() && author.isNotBlank() && pages.isNotBlank() && publisher.isNotBlank()
    }



}
