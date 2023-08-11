package com.omersarikaya.booklibrary.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.omersarikaya.booklibrary.R
import com.omersarikaya.booklibrary.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {

     lateinit var binding: ActivitySplashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.apply {
            smile.alpha = 0f
            smile.animate().setDuration(1500).alpha(1f).withEndAction{
                val intent = Intent(this@SplashActivity, LoginActivity::class.java)
                startActivity(intent)
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
                finish()
            }
        }

    }
}