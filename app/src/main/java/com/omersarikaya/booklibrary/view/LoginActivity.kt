package com.omersarikaya.booklibrary.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.omersarikaya.booklibrary.database.AppDatabase
import com.omersarikaya.booklibrary.database.AppDatabase2
import com.omersarikaya.booklibrary.databinding.ActivityLoginBinding
import com.omersarikaya.booklibrary.databinding.ActivityMainBinding
import com.omersarikaya.booklibrary.viewModel.BookViewModel
import com.omersarikaya.booklibrary.viewModel.LoginViewModel


class LoginActivity : AppCompatActivity() {


    lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        val viewModel = ViewModelProvider(this)[LoginViewModel::class.java]
        binding.lifecycleOwner = this
        binding.model = viewModel
        setContentView(binding.root)

        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase2::class.java, "LoginModel"
        ).allowMainThreadQueries().build()

        val userDao = db.userDao()

        binding.btnRegister.setOnClickListener {
            intent= Intent(applicationContext,RegisterActivity::class.java)
            startActivity(intent)
        }
        binding.btnLogin.setOnClickListener {
            // TODO Validate kontrolleri olacak.
           //TODO dbde username var mı kontrol edilecek yoksa kullanıcı bulunamadı diye pop-up çıkarılacak.
            //TODO dbde username ve password işleşmesi yapılıp login olunacak ve main activitye gidecek.

            if(isFormValid()){

                val userNameExist= binding.userNameInput.text.toString()
                val passwordNameExist = binding.passwordTitle.text.toString()

                if(!userDao.checkUserName(userNameExist)){
                    val builder = AlertDialog.Builder(this)
                    builder.setTitle("Geçersiz Kullanıcı adı ")
                    builder.setMessage("Girdiğiniz kullanıcı adı yoktur")

                    // Kapatma butonu ekleyerek kullanıcının diyalogu kapatabilmesini sağlarız
                    builder.setPositiveButton("Tamam") { dialog, _ ->
                        dialog.dismiss()
                    }

                    // Diyalog kutusunu oluştur ve göster
                    val alertDialog = builder.create()
                    alertDialog.show()

                }
                if(!userDao.checkPassword(passwordNameExist,userNameExist)){

                    val builder = AlertDialog.Builder(this)
                    builder.setTitle("Geçersiz Kullanıcı adı ve şifre ")
                    builder.setMessage("Girdiğiniz kullanıcı adı veya şifre yoktur")

                    // Kapatma butonu ekleyerek kullanıcının diyalogu kapatabilmesini sağlarız
                    builder.setPositiveButton("Tamam") { dialog, _ ->
                        dialog.dismiss()
                    }

                    // Diyalog kutusunu oluştur ve göster
                    val alertDialog = builder.create()
                    alertDialog.show()
                }else{
                    val builder = AlertDialog.Builder(this)
                    builder.setTitle("Giriş Yapıldı ")
                    builder.setMessage("Giriş Yapıldı")
                    //builder.setPositiveButton("OK", DialogInterface.OnClickListener(function = x))

                    builder.setPositiveButton(android.R.string.yes) { dialog, which ->

                        intent= Intent(applicationContext,MainActivity::class.java)
                        startActivity(intent)
                    }
                    builder.show()

                }






            }
            else {

                binding.userName.error = "User name and password Should not be blank"




            }






            }


        }
    private fun isFormValid():Boolean{

        val userExist = binding.userNameInput.toString()
        val passwordExist = binding.passwordTitle.toString()

        return userExist.isNotBlank() && passwordExist.isNotBlank()
    }


    }







