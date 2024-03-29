package com.example.deliveryapp.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.deliveryapp.activities.DetalhesPage
import com.example.deliveryapp.databinding.ProdutoItemBinding
import com.example.deliveryapp.model.Produto

class ProdutoAdapter(
    private val context: Context,
    private val listaProdutos: MutableList<Produto>
) :
    RecyclerView.Adapter<ProdutoAdapter.ProdutoViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProdutoViewHolder {
        val lista = ProdutoItemBinding.inflate(LayoutInflater.from(context), parent, false)
        return ProdutoViewHolder(lista)
    }

    override fun getItemCount(): Int {
        return listaProdutos.size
    }

    @SuppressLint("SuspiciousIndentation")
    override fun onBindViewHolder(holder: ProdutoViewHolder, position: Int) {

            holder.nome.visibility = View.VISIBLE
            holder.img.visibility = View.VISIBLE
            holder.linear_valor.visibility = View.VISIBLE
            holder.progressbar.visibility = View.INVISIBLE

        holder.nome.text = listaProdutos[position].nome
        Glide.with(context).load(listaProdutos[position].img).into(holder.img)
        holder.valor.text = listaProdutos[position].valor
        holder.detalhe.text = listaProdutos[position].detalhes
        holder.button.setOnClickListener {
            val intent = Intent(context, DetalhesPage::class.java)
            intent.putExtra("nome", listaProdutos[position].nome)
            intent.putExtra("img", listaProdutos[position].img)
            intent.putExtra("valor", listaProdutos[position].valor)
            intent.putExtra("detalhe", listaProdutos[position].detalhes)
            context.startActivity(intent)
        }

    }

    inner class ProdutoViewHolder(binding: ProdutoItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        var nome = binding.nomeProduto
        var img = binding.imagemProduto
        var valor = binding.valorProduto
        var button = binding.buttonAdicionarProduto
        var detalhe = binding.detalheProduto
        var linear_valor = binding.linearValor
        val progressbar = binding.progressBar

    }
}