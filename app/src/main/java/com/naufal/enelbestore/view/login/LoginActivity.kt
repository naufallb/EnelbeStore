package com.naufal.enelbestore.view.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.TextUtils
import android.widget.Toast
import com.naufal.enelbestore.R
import com.naufal.enelbestore.databinding.ActivityLoginBinding
import com.naufal.enelbestore.helper.Constant.CREDENTIAL
import com.naufal.enelbestore.helper.Constant.EMAIL
import com.naufal.enelbestore.helper.Constant.LOAD_TIME
import com.naufal.enelbestore.helper.Constant.PASSWORD
import com.naufal.enelbestore.helper.SharedPreferencesHelper
import com.naufal.enelbestore.view.home.HomeActivity

class LoginActivity : AppCompatActivity() {
    private var _binding: ActivityLoginBinding? = null
    private lateinit var sharedPreferences: SharedPreferencesHelper
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedPreferences = SharedPreferencesHelper(this)
        _binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initUi()

    }

    private fun initUi() {
        binding.btnRegisLogin.setOnClickListener {
            startActivity(Intent(this@LoginActivity, RegisterActivity::class.java))
        }
        binding.btnLogin.setOnClickListener {
            when {
                TextUtils.isEmpty(binding.etEmail.text) ->{
                    binding.etEmail.error = resources.getString(R.string.enter_email_first)
                    binding.etEmail.requestFocus()
                }
                TextUtils.isEmpty(binding.etPassword.text) ->{
                    binding.etPassword.error = resources.getString(R.string.enter_password_first)
                    binding.etPassword.requestFocus()
                }
                (binding.etEmail.text.toString() != sharedPreferences.getString(EMAIL)
                        && binding.etPassword.text.toString() != sharedPreferences.getString(
                    PASSWORD)
                        ) -> {
                    Toast.makeText(this, resources.getString(R.string.check_account), Toast.LENGTH_SHORT).show()
                }
                else -> {
                    saveCredentialAndGoToHomePage()
                }
            }

        }
    }

    private fun saveCredentialAndGoToHomePage() {
        sharedPreferences.put(CREDENTIAL,true)
        Toast.makeText(this, resources.getString(R.string.login_success), Toast.LENGTH_SHORT).show().also {
            Handler(Looper.getMainLooper()).postDelayed({
                startActivity(
                    Intent(
                        this,
                        HomeActivity::class.java
                    ).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                )
                finish()
            }, LOAD_TIME.toLong()) }
    }
}
