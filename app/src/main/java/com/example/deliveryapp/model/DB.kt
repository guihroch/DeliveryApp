package com.example.deliveryapp.model

import android.annotation.SuppressLint
import com.example.deliveryapp.adapter.ProdutoAdapter
import com.google.firebase.firestore.FirebaseFirestore

class DB {


    @SuppressLint("NotifyDataSetChanged")
    fun recuperarPizzaSalgada(listaPizzaSalgada: MutableList<Produto>, adapter_produto:ProdutoAdapter){
        val db = FirebaseFirestore.getInstance()
        db.collection("Pizza").get()
            .addOnCompleteListener { tarefa ->
                if (tarefa.isSuccessful) {
                    for (documento in tarefa.result){
                        val pizzaSalgada = documento.toObject<Produto>(Produto::class.java)
                        listaPizzaSalgada.add(pizzaSalgada)
                        adapter_produto.notifyDataSetChanged()

                    }                    }

            }
    }

    fun recuperarPizzaDoce(listaPizzaSalgada: MutableList<Produto>, adapter_produto:ProdutoAdapter){
        val db = FirebaseFirestore.getInstance()
        db.collection("PizzaDoce").get()
            .addOnCompleteListener { tarefa ->
                if (tarefa.isSuccessful) {
                    for (documento in tarefa.result){
                        val pizzaDoce = documento.toObject<Produto>(Produto::class.java)
                        listaPizzaSalgada.add(pizzaDoce)
                        adapter_produto.notifyDataSetChanged()

                    }                    }

            }
    }
}