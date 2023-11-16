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

        binding.linearToCadastro.setOnClickListener {
            val intent = Intent(this, CadastroPage::class.java)
            startActivity(intent)
        }

        binding.buttonLogin.setOnClickListener {
            autenticacaoLogin()

        }


    }
    private fun autenticacaoLogin(){
        binding.buttonLogin.visibility = View.INVISIBLE
        binding.containerProgressBar.visibility = View.VISIBLE
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, HomePage::class.java)
            startActivity(intent)
            binding.buttonLogin.visibility = View.VISIBLE
            binding.containerProgressBar.visibility = View.GONE
            Toast.makeText(this, "Logado com Sucesso!", Toast.LENGTH_LONG).show()
        }, 1500)
    }
}