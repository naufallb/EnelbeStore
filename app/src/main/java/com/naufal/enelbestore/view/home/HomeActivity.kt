package com.naufal.enelbestore.view.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import com.naufal.enelbestore.R
import com.naufal.enelbestore.databinding.ActivityHomeBinding
import com.naufal.enelbestore.helper.Constant
import com.naufal.enelbestore.helper.Constant.USERNAME
import com.naufal.enelbestore.helper.SharedPreferencesHelper
import com.naufal.enelbestore.view.login.LoginActivity

class HomeActivity : AppCompatActivity() {
    private var _binding: ActivityHomeBinding?=null
    private val binding get() = _binding!!
    private lateinit var sharedPreferences: SharedPreferencesHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        sharedPreferences = SharedPreferencesHelper(this)
        initUi()
    }

    private fun initUi() {
        binding.username.text = sharedPreferences.getString(USERNAME)
        binding.btnLogout.setOnClickListener {
            sharedPreferences.clear()
            Toast.makeText(this, resources.getString(R.string.logout_success), Toast.LENGTH_SHORT).show().also {
                Handler(Looper.getMainLooper()).postDelayed({
                    startActivity(
                        Intent(
                            this,
                            LoginActivity::class.java
                        ).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                    )
                    finish()
                }, Constant.LOAD_TIME.toLong()) }
        }
    }
}