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
import com.example.deliveryapp.databinding.ActivityCadastroPageBinding
import com.example.deliveryapp.databinding.ActivityLoginPageBinding

class CadastroPage : AppCompatActivity() {
    private lateinit var binding:ActivityCadastroPageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCadastroPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.statusBarColor = Color.parseColor("#FF000000")

        binding.icRetornarLogin.setOnClickListener {
            val intent = Intent(this, LoginPage::class.java)
            startActivity(intent)
        }

        binding.buttonCadastrar.setOnClickListener {
           cadastroNovoUsuario()
        }
    }
    private fun cadastroNovoUsuario(){
        binding.buttonCadastrar.visibility = View.INVISIBLE
        binding.containerProgressBar.visibility = View.VISIBLE
        Handler(Looper.getMainLooper()).postDelayed({
            binding.buttonCadastrar.visibility = View.VISIBLE
            binding.containerProgressBar.visibility = View.GONE
            Toast.makeText(this, "Cadastrado com Sucesso!", Toast.LENGTH_LONG).show()
        }, 1500)
    }
}