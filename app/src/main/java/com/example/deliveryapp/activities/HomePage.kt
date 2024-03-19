package com.example.deliveryapp.activities

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.deliveryapp.adapter.ProdutoAdapter
import com.example.deliveryapp.databinding.ActivityHomePageBinding
import com.example.deliveryapp.model.DB
import com.example.deliveryapp.model.Produto

class HomePage : AppCompatActivity() {
    private lateinit var binding: ActivityHomePageBinding
    private lateinit var produtoAdapter: ProdutoAdapter
    private val listaPizzaSalgada: MutableList<Produto> = mutableListOf()
    private val listaPizzaDoce:MutableList<Produto> = mutableListOf()


    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomePageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.statusBarColor = Color.parseColor("#FF000000")


        val recyclerView = binding.recyclerView
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        produtoAdapter = ProdutoAdapter(this, listaPizzaSalgada)
        recyclerView.adapter = produtoAdapter
        val db = DB()
        db.recuperarPizzaSalgada(listaPizzaSalgada, produtoAdapter)




        binding.MaterialCardPizzaDoce.setOnClickListener {
            val recyclerView = binding.recyclerView
            recyclerView.setHasFixedSize(true)
            recyclerView.layoutManager =
                LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
            produtoAdapter = ProdutoAdapter(this, listaPizzaDoce)
            if (listaPizzaDoce.isEmpty()) {
                recyclerView.adapter = produtoAdapter
                db.recuperarPizzaDoce(listaPizzaDoce, produtoAdapter)
            } else {

            }


            binding.MaterialCardPizzaDoce.setCardBackgroundColor(Color.parseColor("#85040F"))
            binding.textPizzaDoce.setTextColor(Color.parseColor("#FFFFFFFF"))

            binding.MaterialCardTradicional.setCardBackgroundColor(Color.parseColor("#FFFFFFFF"))
            binding.textPizzaTradicional.setTextColor(Color.parseColor("#FF000000"))
        }

        binding.MaterialCardTradicional.setOnClickListener {
            binding.MaterialCardTradicional.setCardBackgroundColor(Color.parseColor("#85040F"))
            binding.textPizzaTradicional.setTextColor(Color.parseColor("#FFFFFFFF"))

            binding.MaterialCardPizzaDoce.setCardBackgroundColor(Color.parseColor("#FFFFFFFF"))
            binding.textPizzaDoce.setTextColor(Color.parseColor("#FF000000"))

        }

        binding.icLogout.setOnClickListener {
            alertDialogLogout()
        }


    }

    private fun alertDialogLogout() {
        AlertDialog.Builder(this)
            .setTitle("Logout")
            .setMessage("Deseja realmente sair?")
            .setNegativeButton("Não", DialogInterface.OnClickListener { dialogInterface, i ->
            })
            .setPositiveButton("Sim", DialogInterface.OnClickListener { dialogInterface, i ->
                val intent = Intent(this, LoginPage::class.java)
                startActivity(intent)
            })
            .create()
            .show()
    }


}
