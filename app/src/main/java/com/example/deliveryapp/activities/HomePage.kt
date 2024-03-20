package com.example.deliveryapp.activities

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
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
    private val listaPizzaDoce: MutableList<Produto> = mutableListOf()
    val db = DB()


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
            db.recuperarPizzaSalgada(listaPizzaSalgada, produtoAdapter)
            produtoAdapter.notifyDataSetChanged()
        Handler(Looper.getMainLooper()).postDelayed({
            binding.recyclerView.visibility = View.VISIBLE
            binding.progressRecyclerview.visibility = View.INVISIBLE
        }, 1000)











        binding.MaterialCardPizzaDoce.setOnClickListener {
            val recyclerView = binding.recyclerView
            recyclerView.setHasFixedSize(true)
            recyclerView.layoutManager =
                LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
            produtoAdapter = ProdutoAdapter(this, listaPizzaDoce)
            recyclerView.adapter = produtoAdapter
            binding.recyclerView.visibility = View.INVISIBLE
            binding.progressRecyclerview.visibility = View.VISIBLE
            Handler(Looper.getMainLooper()).postDelayed({
                binding.recyclerView.visibility = View.VISIBLE
                binding.progressRecyclerview.visibility = View.INVISIBLE

            }, 500)
            if (listaPizzaDoce.isEmpty()) {
                recyclerView.adapter = produtoAdapter
                db.recuperarPizzaDoce(listaPizzaDoce, produtoAdapter)
                produtoAdapter.notifyDataSetChanged()
                listaPizzaSalgada.clear()
            }

            binding.MaterialCardPizzaDoce.setCardBackgroundColor(Color.parseColor("#85040F"))
            binding.textPizzaDoce.setTextColor(Color.parseColor("#FFFFFFFF"))
            binding.txtCategoriaSelecionada.text = "Pizza Doce"

            binding.MaterialCardTradicional.setCardBackgroundColor(Color.parseColor("#FFFFFFFF"))
            binding.textPizzaTradicional.setTextColor(Color.parseColor("#FF000000"))
        }

        binding.MaterialCardTradicional.setOnClickListener {
            val recyclerView = binding.recyclerView
            recyclerView.setHasFixedSize(true)
            recyclerView.layoutManager =
                LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
            produtoAdapter = ProdutoAdapter(this, listaPizzaSalgada)
            recyclerView.adapter = produtoAdapter
            binding.recyclerView.visibility = View.INVISIBLE
            binding.progressRecyclerview.visibility = View.VISIBLE
            Handler(Looper.getMainLooper()).postDelayed({
                binding.recyclerView.visibility = View.VISIBLE
                binding.progressRecyclerview.visibility = View.INVISIBLE
            }, 500)

            if (listaPizzaSalgada.isEmpty()) {
                recyclerView.adapter = produtoAdapter
                db.recuperarPizzaSalgada(listaPizzaSalgada, produtoAdapter)
                produtoAdapter.notifyDataSetChanged()
                listaPizzaDoce.clear()
            }

            binding.MaterialCardTradicional.setCardBackgroundColor(Color.parseColor("#85040F"))
            binding.textPizzaTradicional.setTextColor(Color.parseColor("#FFFFFFFF"))
            binding.txtCategoriaSelecionada.text = "Pizza Tradicional"

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
