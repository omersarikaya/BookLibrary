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
import com.omersarikaya.booklibrary.viewModel.RegisterViewModel
import com.omersarikaya.booklibrary.databinding.ActivityRegisterBinding
import com.omersarikaya.booklibrary.viewModel.LoginViewModel


class RegisterActivity: AppCompatActivity() {

    lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

      binding = ActivityRegisterBinding.inflate(layoutInflater)
        val viewModel = ViewModelProvider(this)[RegisterViewModel::class.java]
        binding.lifecycleOwner=this
        binding.model=viewModel
        setContentView(binding.root)
        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase2::class.java, "LoginModel"
        ).allowMainThreadQueries().build()

        val userDao = db.userDao()


        binding.passwordTitle.setOnFocusChangeListener { _, focused ->
            if (!focused) {
                binding.password.error = validPassword()
            }else{
                binding.password.error=null
            }

        }


        binding.userNameInput.setOnFocusChangeListener{_,focused ->
            if (!focused)
            {
                binding.userName.error = validName()
            }else{
                binding.userName.error=null
            }
        }

        binding.btnRegister.setOnClickListener {
            // TODO kullanıcı adı boş olamaz. validate et
            // TODO Şifre kısmı boş olamaz kontrol et.
            //TODO eğer girilen username var ise pop-up mevcutta var diye



            if(isFormValid()){

                val userNameExist= binding.userNameInput.text.toString()

                if(userDao.checkUserName(userNameExist)){
                    val builder = AlertDialog.Builder(this)
                    builder.setTitle("Kullanıcı Adı Zaten Kullanılıyor")
                    builder.setMessage("Girdiğiniz kullanıcı adı zaten başka bir kullanıcı tarafından kullanılmaktadır. Lütfen farklı bir kullanıcı adı seçin.")

                    // Kapatma butonu ekleyerek kullanıcının diyalogu kapatabilmesini sağlarız
                    builder.setPositiveButton("Tamam") { dialog, _ ->
                        dialog.dismiss()
                    }

                    // Diyalog kutusunu oluştur ve göster
                    val alertDialog = builder.create()
                    alertDialog.show()

                } else {
                    viewModel.registerUser(userDao,null,binding.userNameInput.text.toString(),
                        binding.passwordTitle.text.toString())

                    val builder = AlertDialog.Builder(this)
                    builder.setTitle("Androidly Alert")
                    builder.setMessage("We have a message")
                    //builder.setPositiveButton("OK", DialogInterface.OnClickListener(function = x))

                    builder.setPositiveButton(android.R.string.yes) { dialog, which ->

                        intent= Intent(applicationContext,LoginActivity::class.java)
                        startActivity(intent)
                    }
                    builder.show()
                }








            } else {
                // TODO error eklecek. Boş bırakılmaz diye
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



        // TODO validate true ise db kayıt atıp pop-up çıkacak işlem başarılı diye sonrasasında login ekranına atacak stack temizleyip


        }



        }


    private fun isFormValid(): Boolean{
        val password = binding.passwordTitle.text.toString()
        val userName= binding.userNameInput.text.toString()
        if (password.isEmpty() || userName.isEmpty()){
            if(userName.isEmpty()){
                binding.userName.error = " Kullanıcı adı boş bırakılamaz. "
            }
            if(password.isEmpty()){
                binding.password.error = " Şifre boş bırakılamaz. "
            }

            return false
        } else {
            return true
        }


       /* if ()
            //Db de user name varsa pop up çıkarılıcak kullanıcı adı zaten kullanılıyordur diye
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Kullanıcı Adı Zaten Kullanılıyor")
        builder.setMessage("Girdiğiniz kullanıcı adı zaten başka bir kullanıcı tarafından kullanılmaktadır. Lütfen farklı bir kullanıcı adı seçin.")

        // Kapatma butonu ekleyerek kullanıcının diyalogu kapatabilmesini sağlarız
        builder.setPositiveButton("Tamam") { dialog, _ ->
            dialog.dismiss()
        }

        // Diyalog kutusunu oluştur ve göster
        val alertDialog = builder.create()
        alertDialog.show()
    */
    }


    private fun validName(): String?{
        val nameText= binding.userNameInput.text.toString()
        return if(nameText.isBlank()){
            "Fill in the blank"
        }
        else{
            null
        }

    }


    private fun validPassword(): String?{
        val passwordText=binding.passwordTitle.text.toString()
        return if(passwordText.isBlank()){
            "Fill in the blank"
        }
        else{
            null
        }

    }



    }








