package com.omersarikaya.booklibrary.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
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
            intent = Intent(applicationContext, RegisterActivity::class.java)
            startActivity(intent)
        }

        binding.userNameInput.setOnFocusChangeListener { _, focused ->
            if (!focused) {
                binding.userName.error = validUsername()

            } else {
                binding.userName.error =null
            }
        }
        binding.passwordTitle.setOnFocusChangeListener { _, focused ->
            if (!focused) {
                binding.password.error = validPassword()

            } else {
                binding.password.error = null
            }
        }

        binding.passwordTitle.addTextChangedListener {
            binding.btnLogin.isEnabled =
                !binding.userNameInput.text.isNullOrEmpty() && !it.toString().isNullOrEmpty()
        }

        binding.userNameInput.addTextChangedListener {
            binding.btnLogin.isEnabled =
                !binding.passwordTitle.text.isNullOrEmpty() && !it.toString().isNullOrEmpty()
        }

        binding.btnLogin.setOnClickListener {
            // TODO Validate kontrolleri olacak.
            //TODO dbde username var mı kontrol edilecek yoksa kullanıcı bulunamadı diye pop-up çıkarılacak.
            //TODO dbde username ve password işleşmesi yapılıp login olunacak ve main activitye gidecek.

            val list = userDao.getAll()

            if (isFormValid()) {

                val userNameExist = binding.userNameInput.text.toString()
                val passwordNameExist = binding.passwordTitle.text.toString()

                if (!userDao.checkUserName(userNameExist)) {
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
                if (!userDao.checkPassword(passwordNameExist, userNameExist)) {

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
                } else {
                    val builder = AlertDialog.Builder(this)
                    builder.setTitle("Giriş Yapıldı ")
                    builder.setMessage("Giriş Yapıldı")
                    builder.setCancelable(false)
                    //builder.setPositiveButton("OK", DialogInterface.OnClickListener(function = x))

                    builder.setPositiveButton(android.R.string.yes) { dialog, which ->

                        intent = Intent(applicationContext, MainActivity::class.java)
                        startActivity(intent)
                    }
                    builder.show()

                }


            } else {

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
    /*private fun isFormValid():Boolean{

        val userExist = binding.userNameInput.toString()
        val passwordExist = binding.passwordTitle.toString()

        return userExist.isNotBlank() && passwordExist.isNotBlank()
    }*/

    private fun isFormValid(): Boolean {
        val password = binding.passwordTitle.text.toString()
        val userName = binding.userNameInput.text.toString()
        if (password.isEmpty() || userName.isEmpty()) {
            if (userName.isEmpty()) {
                binding.userName.error = " Kullanıcı adı boş bırakılamaz. "
            }
            if (password.isEmpty()) {
                binding.password.error = " Şifre boş bırakılamaz. "
            }

            return false
        } else {
            return true
        }
    }


    private fun validUsername(): String? {
        val userNameText = binding.userNameInput.text.toString()
        return if (userNameText.isBlank()) {
            "Fill in the blank"
        } else {
            null
        }
    }

    private fun validPassword():String?{
        val passwordText = binding.passwordTitle.text.toString()
        return if(passwordText.isBlank()){
            "Fill in the blank"
        }else{
            null
        }

    }

}








