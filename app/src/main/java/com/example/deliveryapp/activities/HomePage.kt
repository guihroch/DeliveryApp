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


        binding.MaterialCardPizzaDoce.setOnClickListener {
            configAdapterPizzaDoce()
            binding.MaterialCardPizzaDoce.setCardBackgroundColor(Color.parseColor("#85040F"))
            binding.textPizzaDoce.setTextColor(Color.parseColor("#FFFFFFFF"))

            binding.MaterialCardTradicional.setCardBackgroundColor(Color.parseColor("#FFFFFFFF"))
            binding.textPizzaTradicional.setTextColor(Color.parseColor("#FF000000"))
        }

        binding.MaterialCardTradicional.setOnClickListener {
            configProdutoAdapterPizzaSalgada()
            binding.MaterialCardTradicional.setCardBackgroundColor(Color.parseColor("#85040F"))
            binding.textPizzaTradicional.setTextColor(Color.parseColor("#FFFFFFFF"))

            binding.MaterialCardPizzaDoce.setCardBackgroundColor(Color.parseColor("#FFFFFFFF"))
            binding.textPizzaDoce.setTextColor(Color.parseColor("#FF000000"))



          //  binding.MaterialCardPizzaDoce.setCardBackgroundColor(Color.parseColor("#FFFFFFFF"))
           // binding.textPizzaDoce.setTextColor(Color.parseColor("#85040F"))

        }


    }




    fun configProdutoAdapterPizzaSalgada() {
        val listaProduto: MutableList<Produto> = mutableListOf(
            Produto(
                nome = "Pizza Calabresa",
                img = R.drawable.imagem_pizza_calabresa,
                valor = "34.50",
                detalhes = "Mussarela, calabresa e cebola."
            ),
            Produto(
                nome = "Pizza Mussarela",
                img = R.drawable.imagem_pizza_mussarela,
                valor = "35.00",
                detalhes = "Mussarela, frango e catupiry."
            ),
            Produto(
                nome = "Pizza Portuguesa",
                img = R.drawable.imagem_pizza_portuguesa,
                valor = "33.00",
                detalhes = "Mussarela, pimentão, tomate, palmito, azeitona, presunto e cebola."
            ),
            Produto(
                nome = "Pizza Frango Catupiry",
                img = R.drawable.imagem_pizza_frango,
                valor = "35.00",
                detalhes = "Mussarela, bacon, e azeitona."
            ),
            )
        binding.txtCategoriaSelecionada.text = "Tradicional"

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
                valor = "30.00",
                detalhes = "Chocolate ao leite, chocolate branco e cereja."
            ),
            Produto(
                nome = "Pizza Confete",
                img = R.drawable.imagem_pizza_confete,
                valor = "34.50",
                detalhes = "chocolate ao leite e confete."
            ),
            Produto(
                nome = "Pizza Chocolate com Morango",
                img = R.drawable.imagem_pizza_chocolate_morango,
                valor = "35.00",
                detalhes = "Chocolate ao leite, morango e pedaços de chocolate."
            ),
            Produto(
                nome = "Pizza Banana com Canela",
                img = R.drawable.imagem_pizza_banana_canela,
                valor = "33.50",
                detalhes = "Banana, leite condensado e canela."
            ),
            )
        binding.txtCategoriaSelecionada.text = "Doce"
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


    }
