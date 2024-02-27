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
import com.google.firebase.auth.FirebaseAuth

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
        val auth = FirebaseAuth.getInstance()
        val email = binding.editTextEmail.text.toString()
        val senha = binding.editTextSenha.text.toString()

        binding.buttonLogin.visibility = View.INVISIBLE
        binding.containerProgressBar.visibility = View.VISIBLE

        auth.signInWithEmailAndPassword(email, senha).addOnCompleteListener { autenticacao ->
            if (autenticacao.isSuccessful) {
               Handler(Looper.getMainLooper()).postDelayed({
                   binding.buttonLogin.visibility = View.VISIBLE
                   binding.containerProgressBar.visibility = View.INVISIBLE
                   Toast.makeText(this, "Login realizado com sucesso!", Toast.LENGTH_SHORT).show()
                   val intent = Intent(this, HomePage::class.java)
                   startActivity(intent)
               },1500)
            }
        }.addOnFailureListener {
            binding.buttonLogin.visibility = View.VISIBLE
            binding.containerProgressBar.visibility = View.GONE
            Toast.makeText(this, "Email ou senha inválidos!", Toast.LENGTH_SHORT).show()
        }
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

            else -> {
                autenticacaoLogin()
            }
        }
    }
}