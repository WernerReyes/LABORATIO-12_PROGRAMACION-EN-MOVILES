package com.reyes.werner.poketinder

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.reyes.werner.poketinder.databinding.ActivityLoginBinding
import com.reyes.werner.poketinder.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {
    private lateinit var registerViewModel: RegisterViewModel
    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        registerViewModel = RegisterViewModel(this)
        observeValues()
    }

    private fun observeValues() {
        registerViewModel.inputsError.observe(this) {
            Toast.makeText(this, "Ingrese los datos completos", Toast.LENGTH_SHORT).show()
        }

        registerViewModel.passwordMatch.observe(this) {
            Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show()
        }

        registerViewModel.registerSuccess.observe(this) {
            startActivity(Intent(this, MainActivity::class.java))
            Toast.makeText(this, "Registro exitoso", Toast.LENGTH_SHORT).show()
        }

        binding.btnRegister.setOnClickListener {
            registerViewModel.validateInputs(
                email = binding.edtEmail.text.toString(),
                password = binding.edtPassword.text.toString(),
                confirmPassword = binding.edtPassword2.text.toString()
            )
        }

        binding.btnLogin.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }

        binding.btnBackClose.setOnClickListener {
            finish()
        }

    }




}