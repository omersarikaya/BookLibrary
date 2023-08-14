package com.omersarikaya.booklibrary.view

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
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


        binding.btnList.setOnClickListener {
            //val bookList = userDao.getAll()
            //viewModelin içindeki kital listesini butona tıklanınca veritabınından dolduruyoruz.
            //viewModel.bookLiveData.value = bookList
            val intent = Intent(this,ListButtonActivity::class.java)
            startActivity(intent)
        }

        binding.editTitle.setOnFocusChangeListener { _, focused ->
            if (!focused) {
                binding.title.error = validTitle()
            } else {
                binding.title.error=null
            }
        }



        binding.editAuthor.setOnFocusChangeListener{_,focused ->
            if (!focused)
            {
                binding.author.error = validAuthor()
            }else{
                binding.author.error=null
            }
        }

        binding.editPages.setOnFocusChangeListener{_,focused ->
            if (!focused)
            {
                binding.pages.error = validPages()
            }else{
                binding.pages.error=null
            }
        }

        binding.editPublisher.setOnFocusChangeListener{_,focused ->
            if (!focused)
            {
                binding.publisher.error= validPublisher()
            }else{
                binding.publisher.error=null
            }
        }





        binding.editTitle.addTextChangedListener{
            binding.btnSave.isEnabled=
                !binding.editAuthor.text.isNullOrEmpty()&&!binding.editPages.text.isNullOrEmpty()&&!binding.editPublisher.text.isNullOrEmpty()&&!it.toString().isNullOrEmpty()
        }
        binding.editAuthor.addTextChangedListener{
            binding.btnSave.isEnabled=
                !binding.editTitle.text.isNullOrEmpty()&&!binding.editPages.text.isNullOrEmpty()&&!binding.editPublisher.text.isNullOrEmpty()&&!it.toString().isNullOrEmpty()
        }
        binding.editPublisher.addTextChangedListener{
            binding.btnSave.isEnabled=
                !binding.editAuthor.text.isNullOrEmpty()&&!binding.editPages.text.isNullOrEmpty()&&!binding.editTitle.text.isNullOrEmpty()&&!it.toString().isNullOrEmpty()
        }
        binding.editPages.addTextChangedListener{
            binding.btnSave.isEnabled=
                !binding.editAuthor.text.isNullOrEmpty()&&!binding.editTitle.text.isNullOrEmpty()&&!binding.editPublisher.text.isNullOrEmpty()&&!it.toString().isNullOrEmpty()
        }


        binding.btnSave.setOnClickListener {
             // TODO kitap adı ve yazar ismi aynı ise pop-up göster bu kitap zzaten kütüphanede mevcuttur diye.
            if(isFormValid()){

                val book = binding.editTitle.text.toString()

                val author = binding.editAuthor.text.toString()
                if(userDao.checkBook(book,author)){
                    val builder = AlertDialog.Builder(this)
                    builder.setTitle("Girdiğiniz kitap kütüphanede mevcuttur")
                    builder.setMessage("Girdiğiniz kitap kütüphanede mevcuttur. Lütfen farklı bir kitap girin.")

                    // Kapatma butonu ekleyerek kullanıcının diyalogu kapatabilmesini sağlarız
                    builder.setPositiveButton("Tamam") { dialog, _ ->
                        dialog.dismiss()
                    }

                    // Diyalog kutusunu oluştur ve göster
                    val alertDialog = builder.create()
                    alertDialog.show()

                }else{

                    viewModel.saveBook(userDao,null,binding.editTitle.text.toString(),
                        binding.editAuthor.text.toString(),
                        binding.editPages.text.toString(),binding.editPublisher.text.toString())
                    val toast = Toast.makeText(applicationContext,"Saved", Toast.LENGTH_SHORT)
                    toast.show()
                    clearEditText()

                }


                /*if(!userDao.checktitleName(book)){

                    val builder = AlertDialog.Builder(this)
                    builder.setTitle("lütfen bütün boşlukları doldurun")
                    builder.setMessage("Lütfen kitap adınız giriniz.")

                    // Kapatma butonu ekleyerek kullanıcının diyalogu kapatabilmesini sağlarız
                    builder.setPositiveButton("Tamam") { dialog, _ ->
                        dialog.dismiss()
                    }

                    // Diyalog kutusunu oluştur ve göster
                    val alertDialog = builder.create()
                    alertDialog.show()

                }

                 */




            }
            else{
                val builder = AlertDialog.Builder(this)
                builder.setTitle("Lütfen Boşlukları Doldurun")
                builder.setMessage("Lütfen boşlukları doldurunuz.")

                // Kapatma butonu ekleyerek kullanıcının diyalogu kapatabilmesini sağlarız
                builder.setPositiveButton("Tamam") { dialog, _ ->
                    dialog.dismiss()
                }

                // Diyalog kutusunu oluştur ve göster
                val alertDialog = builder.create()
                alertDialog.show()





            }



        }










    }

    private fun clearEditText(){
        binding.editTitle.text = null
        binding.editAuthor.text=null
        binding.editPages.text=null
        binding.editPublisher.text=null

    }

    /*private fun isFormValid(): Boolean{
        val title = binding.editTitle.text.toString()
        val author = binding.editAuthor.text.toString()
        val pages = binding.editPages.text.toString()
        val publisher = binding.editPublisher.text.toString()

        return title.isNotBlank() && author.isNotBlank() && pages.isNotBlank() && publisher.isNotBlank()
    }*/

    private fun isFormValid(): Boolean{
        val title = binding.editTitle.text.toString()
        val author = binding.editAuthor.text.toString()
        val pages = binding.editPages.text.toString()
        val publisher = binding.editPublisher.text.toString()
        if (title.isEmpty() || author.isEmpty()||pages.isEmpty()||publisher.isEmpty()){
            if(title.isEmpty()){
                binding.title.error = " Kitap adı boş bırakılamaz. "
            }
            if(author.isEmpty()){
                binding.author.error = " Yazar boş bırakılamaz. "
            }
            if(pages.isEmpty()){
                binding.pages.error= "Sayfa sayısı boş bırakılamaz"
            }
            if(publisher.isEmpty()){
                binding.publisher.error= "Yayıncı boş bırakılamaz"
            }


            return false
        } else {
            return true
        }
    }




    private fun validTitle(): String?{
        val titleText=binding.editTitle.text.toString()
        return if(titleText.isBlank()){
            "Fill in the blank"
        }
        else{
            null
        }

    }


    private fun validAuthor(): String?{
        val authorText=binding.editAuthor.text.toString()
        return if(authorText.isBlank()){
            "Fill in the blank"
        }
        else{
            null
        }

    }




    private fun validPages(): String?{
        val pagesText=binding.editTitle.text.toString()
        return if(pagesText.isBlank()){
            "Fill in the blank"
        }
        else{
            null
        }

    }




    private fun validPublisher(): String?{
        val publisherText=binding.editPublisher.text.toString()
        return if(publisherText.isBlank()){
            "Fill in the blank"
        }
        else{
            null
        }

    }



}
