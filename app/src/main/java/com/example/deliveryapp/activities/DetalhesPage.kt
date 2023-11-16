package com.example.deliveryapp.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.deliveryapp.R
import com.example.deliveryapp.databinding.ActivityDetalhesPageBinding
import java.text.DecimalFormat

class DetalhesPage : AppCompatActivity() {
    private var quantidade = 1
    private lateinit var binding: ActivityDetalhesPageBinding
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetalhesPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.statusBarColor = Color.parseColor("#FF000000")

        val nome = intent.extras?.getString("nome")
        val img = intent.extras?.getInt("img")
        val valor = intent.extras?.getString("valor")?.toDouble()
        val detalhe = intent.extras?.getString("detalhe")


        binding.buttonAdicionar.setOnClickListener {
            adicionarQuantidade()
        }

        binding.buttonRemover.setOnClickListener {
            diminuirQuantidade()
        }

        binding.arrowBackHomePage.setOnClickListener {
            voltarHomePage()
        }
        binding.containerToPagamentoPage.setOnClickListener {
            val intent = Intent(this, PagamentoPage::class.java)
            intent.putExtra("valor", binding.textValorTotal.text)
            startActivity(intent)
        }


        binding.imgProduto.setBackgroundResource(img!!)
        binding.nomeProduto.text = nome
        binding.detalheProduto.text = detalhe
        val decimalFormat = DecimalFormat("##.00")
        binding.textValorTotal.text ="R$ ${decimalFormat.format(valor)}"
        binding.textValorFixo.text = "R$ ${decimalFormat.format(valor)}"

    }

    @SuppressLint("SetTextI18n")
    private fun adicionarQuantidade(){
        val decimalFormat = DecimalFormat("##.00")
        val valor = intent.extras?.getString("valor")?.toDouble()
        if(quantidade >= 1) {
            quantidade++
            binding.txtQuantidade.text = quantidade.toString()
           val novoValor = quantidade.toDouble() * valor!!
            binding.textValorTotal.text = "R$ ${decimalFormat.format(novoValor)}"

        }
    }
    @SuppressLint("SetTextI18n")
    private fun diminuirQuantidade(){
        val decimalFormat = DecimalFormat("##.00")
        val valor = intent.extras?.getString("valor")?.toDouble()
        if(quantidade > 1) {
            quantidade--
            binding.txtQuantidade.text = quantidade.toString()
            val novoValor = quantidade.toDouble() * valor!!
            binding.textValorTotal.text = "R$ ${decimalFormat.format(novoValor)}"
        }
    }
    private fun voltarHomePage(){
        val intent = Intent(this, HomePage::class.java)
        finish()
    }
}