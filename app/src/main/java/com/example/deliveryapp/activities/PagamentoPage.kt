package com.example.deliveryapp.activities

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.deliveryapp.R
import com.example.deliveryapp.databinding.ActivityPagamentoPageBinding

class PagamentoPage : AppCompatActivity() {
    private lateinit var binding: ActivityPagamentoPageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPagamentoPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.statusBarColor = Color.parseColor("#FF000000")

val valor = intent.extras?.getString("valor")
        binding.textValor.text = valor
    }
}