package com.example.deliveryapp.activities

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Toast
import com.example.deliveryapp.R
import com.example.deliveryapp.databinding.ActivityLoginPageBinding

class LoginPage : AppCompatActivity() {
    private lateinit var binding: ActivityLoginPageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.statusBarColor = Color.parseColor("#FF000000")

        binding.textToCadastroPage.setOnClickListener {
            val intent = Intent(this, CadastroPage::class.java)
            startActivity(intent)
        }

        binding.buttonLogin.setOnClickListener {
            verificarCamposPreenchidos()


        }


    }

    private fun autenticacaoLogin() {
        binding.buttonLogin.visibility = View.INVISIBLE
        binding.containerProgressBar.visibility = View.VISIBLE
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, HomePage::class.java)
            startActivity(intent)
            binding.buttonLogin.visibility = View.VISIBLE
            binding.containerProgressBar.visibility = View.GONE
            Toast.makeText(this, "Logado com Sucesso!", Toast.LENGTH_SHORT).show()
        }, 1500)
    }

    private fun verificarCamposPreenchidos() {
        val email = binding.editTextEmail.text.toString()
        val senha = binding.editTextSenha.text.toString()
        val emailRegex = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}\$"
        when {
            email.isEmpty() -> {
                binding.layoutEditEmail.helperText = "Email é obrigatório"
                Handler(Looper.getMainLooper()).postDelayed({
                    binding.layoutEditEmail.helperText = ""
                }, 2000)
            }
            !email.matches(emailRegex.toRegex()) -> {
                binding.layoutEditEmail.helperText = "Digite um email válido"
                Handler(Looper.getMainLooper()).postDelayed({
                    binding.layoutEditEmail.helperText = ""
                }, 2000)
            }
            senha.isEmpty() -> {
                binding.layoutEditSenha.helperText = "Digite uma senha válida"
                Handler(Looper.getMainLooper()).postDelayed({
                    binding.layoutEditSenha.helperText = ""
                }, 2000)
            }
            senha.length < 6 -> {
                binding.layoutEditSenha.helperText = "Digite uma senha com pelo menos 6 caracteres"
                Handler(Looper.getMainLooper()).postDelayed({
                    binding.layoutEditSenha.helperText = ""
                }, 2000)
            }
            else -> {
                autenticacaoLogin()
            }
        }
    }
}