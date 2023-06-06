package com.naufal.enelbestore.view.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.TextUtils
import android.widget.Toast
import com.naufal.enelbestore.R
import com.naufal.enelbestore.databinding.ActivityRegisterBinding
import com.naufal.enelbestore.helper.Constant
import com.naufal.enelbestore.helper.Constant.EMAIL
import com.naufal.enelbestore.helper.Constant.PASSWORD
import com.naufal.enelbestore.helper.Constant.USERNAME
import com.naufal.enelbestore.helper.SharedPreferencesHelper

class RegisterActivity : AppCompatActivity() {
    private var _binding: ActivityRegisterBinding? = null
    private val binding get() = _binding!!
    private lateinit var sharedPreferences: SharedPreferencesHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        sharedPreferences = SharedPreferencesHelper(this)
        initUI()
    }

    private fun initUI() {
        binding.btnRegis.setOnClickListener {
            validateForm()

        }
    }

    private fun validateForm() {
        when {
            TextUtils.isEmpty(binding.etUsername.text) -> {
                binding.etUsername.error = resources.getString(R.string.enter_username_first)
                binding.etUsername.requestFocus()
            }
            TextUtils.isEmpty(binding.etEmail.text) -> {
                binding.etEmail.error = resources.getString(R.string.enter_email_first)
                binding.etEmail.requestFocus()
            }
            TextUtils.isEmpty(binding.etPassword.text) -> {
                binding.etPassword.error = resources.getString(R.string.enter_password_first)
                binding.etPassword.requestFocus()
            }
            TextUtils.isEmpty(binding.etConfirmationPassword.text) -> {
                binding.etConfirmationPassword.error =
                    resources.getString(R.string.enter_password_first)
                binding.etConfirmationPassword.requestFocus()
            }
            binding.etConfirmationPassword.text.toString() != binding.etPassword.text.toString() -> {
                binding.etConfirmationPassword.error =
                    resources.getString(R.string.password_confirmation_doesnt_match)
                binding.etConfirmationPassword.requestFocus()
            }
            else -> saveDataAndGoToLoginPage(
                binding.etUsername.text.toString(),
                binding.etEmail.text.toString(),
                binding.etPassword.text.toString()
            )
        }
    }

    private fun saveDataAndGoToLoginPage(username: String, email: String, password: String) {
        sharedPreferences.put(USERNAME, username)
        sharedPreferences.put(EMAIL, email)
        sharedPreferences.put(PASSWORD, password)
        Toast.makeText(this, resources.getString(R.string.registration_success), Toast.LENGTH_SHORT).show().also {
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