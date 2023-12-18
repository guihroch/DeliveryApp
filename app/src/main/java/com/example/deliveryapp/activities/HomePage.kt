package com.example.deliveryapp.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Layout
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.deliveryapp.R
import com.example.deliveryapp.adapter.ProdutoAdapter
import com.example.deliveryapp.databinding.ActivityHomePageBinding
import com.example.deliveryapp.model.Produto

class HomePage : AppCompatActivity() {
    private lateinit var binding: ActivityHomePageBinding
    private lateinit var produtoAdapter: ProdutoAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomePageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.statusBarColor = Color.parseColor("#FF000000")

        configProdutoAdapterPizzaSalgada()
        configToolBar()



        binding.linearIcBuscar.setOnClickListener {
            buscarSelecionado()
        }
        binding.linearIcHome.setOnClickListener {
            homeSelecionado()
        }
        binding.linearIcConfig.setOnClickListener {
            configSelecionado()
        }

        binding.MaterialCardPizzaDoce.setOnClickListener {
            configAdapterPizzaDoce()
        }

        binding.MaterialCardPizza.setOnClickListener {
            configProdutoAdapterPizzaSalgada()
        }


    }




    fun configProdutoAdapterPizzaSalgada() {
        val listaProduto: MutableList<Produto> = mutableListOf(
            Produto(
                nome = "Pizza Calabresa",
                img = R.drawable.imagem_pizza_calabresa,
                valor = "22.50",
                detalhes = "Mussarela, calabresa e cebola."
            ),
            Produto(
                nome = "Pizza Frango Catupiry",
                img = R.drawable.imagem_pizza_frango,
                valor = "25.00",
                detalhes = "Mussarela, frango e catupiry."
            ),
            Produto(
                nome = "Pizza Portuguesa",
                img = R.drawable.imagem_pizza_portuguesa,
                valor = "22.50",
                detalhes = "Mussarela, pimentão, tomate, palmito, azeitona, presunto e cebola."
            ),
            Produto(
                nome = "Pizza Mussarela",
                img = R.drawable.imagem_pizza_mussarela,
                valor = "22.50",
                detalhes = "Mussarela, bacon, e azeitona."
            ),
            )

        val recyclerView = binding.recyclerView
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        produtoAdapter = ProdutoAdapter(this, listaProduto)
        recyclerView.adapter = produtoAdapter
    }

    private fun configAdapterPizzaDoce(){
        val listaProduto: MutableList<Produto> = mutableListOf(
            Produto(
                nome = "Pizza Chocolate Preto e Branco",
                img = R.drawable.imagem_pizza_chocolate_preto_e_branco,
                valor = "22.50",
                detalhes = "Chocolate ao leite, chocolate branco e cereja."
            ),
            Produto(
                nome = "Pizza Confete",
                img = R.drawable.imagem_pizza_confete,
                valor = "25.00",
                detalhes = "chocolate ao leite e confete."
            ),
            Produto(
                nome = "Pizza Chocolate com Morango",
                img = R.drawable.imagem_pizza_chocolate_morango,
                valor = "22.50",
                detalhes = "Chocolate ao leite, morango e pedaços de chocolate."
            ),
            Produto(
                nome = "Pizza Banana com Canela",
                img = R.drawable.imagem_pizza_banana_canela,
                valor = "22.50",
                detalhes = "Banana, leite condensado e canela."
            ),
            )
        val recyclerView = binding.recyclerView
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        produtoAdapter = ProdutoAdapter(this, listaProduto)
        recyclerView.adapter = produtoAdapter
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.top_menu, menu)
        return super.onCreateOptionsMenu(menu)

    }

    private fun configToolBar() {
        setSupportActionBar(binding.customToolbar)
        supportActionBar?.title = null
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.ic_logout -> {
                val intent = Intent(applicationContext, LoginPage::class.java)
                startActivity(intent)
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun configSelecionado() {
        binding.icSearch.visibility = View.VISIBLE
        binding.icSearchSelected.visibility = View.GONE
        binding.textIconBuscar.setTextColor(Color.parseColor("#AAA9A9"))

        binding.icSettingsSelected.visibility = View.VISIBLE
        binding.icSettings.visibility = View.GONE
        binding.textIconConfiguracao.setTextColor(Color.parseColor("#85040F"))

        binding.icHome.visibility = View.VISIBLE
        binding.icHomeSelected.visibility = View.GONE
        binding.textIconHome.setTextColor(Color.parseColor("#AAA9A9"))
    }

    private fun homeSelecionado() {
        binding.icSearch.visibility = View.VISIBLE
        binding.icSearchSelected.visibility = View.GONE
        binding.textIconBuscar.setTextColor(Color.parseColor("#AAA9A9"))

        binding.icHomeSelected.visibility = View.VISIBLE
        binding.icHome.visibility = View.GONE
        binding.textIconHome.setTextColor(Color.parseColor("#85040F"))

        binding.icSettings.visibility = View.VISIBLE
        binding.icSettingsSelected.visibility = View.GONE
        binding.textIconConfiguracao.setTextColor(Color.parseColor("#AAA9A9"))
    }

    private fun buscarSelecionado() {
        binding.icSearch.visibility = View.GONE
        binding.icSearchSelected.visibility = View.VISIBLE
        binding.textIconBuscar.setTextColor(Color.parseColor("#85040F"))

        binding.icHomeSelected.visibility = View.GONE
        binding.icHome.visibility = View.VISIBLE
        binding.textIconHome.setTextColor(Color.parseColor("#AAA9A9"))

        binding.icSettings.visibility = View.VISIBLE
        binding.icSettingsSelected.visibility = View.GONE
        binding.textIconConfiguracao.setTextColor(Color.parseColor("#AAA9A9"))
    }
}