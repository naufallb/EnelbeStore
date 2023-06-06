package com.naufal.enelbestore.view.splashscreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.naufal.enelbestore.R
import com.naufal.enelbestore.helper.Constant.CREDENTIAL
import com.naufal.enelbestore.helper.Constant.LOAD_TIME
import com.naufal.enelbestore.helper.SharedPreferencesHelper
import com.naufal.enelbestore.view.home.HomeActivity
import com.naufal.enelbestore.view.login.LoginActivity

class SplashScreenActivity : AppCompatActivity() {
    private lateinit var sharedPreferences: SharedPreferencesHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        sharedPreferences = SharedPreferencesHelper(this)
        initUi()
    }
    private fun initUi(){
        if (sharedPreferences.getBoolean(CREDENTIAL)) {
            Handler(Looper.getMainLooper()).postDelayed({
                startActivity(
                    Intent(
                        this,
                        HomeActivity::class.java
                    ).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                )
                finish()
            }, LOAD_TIME.toLong())
        } else {
            Handler(Looper.getMainLooper()).postDelayed({
                startActivity(
                    Intent(
                        this,
                        LoginActivity::class.java
                    ).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                )
                finish()
            }, LOAD_TIME.toLong())
        }
    }
}