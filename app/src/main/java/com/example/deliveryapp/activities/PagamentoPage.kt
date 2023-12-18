package com.example.deliveryapp.activities

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Toast
import com.example.deliveryapp.databinding.ActivityPagamentoPageBinding
import com.example.deliveryapp.dialog.DialogCarregando
import java.text.DecimalFormat

class PagamentoPage : AppCompatActivity() {
    private lateinit var binding: ActivityPagamentoPageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)




        binding = ActivityPagamentoPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.statusBarColor = Color.parseColor("#FF000000")


        val valor = intent.extras?.getString("valor")
        binding.textValor.text = valor
        binding.valorTotalComTaxa.text = "$valor"


        binding.buttonPagamento.setOnClickListener {
            selecionarFormaPagamento()
        }

        binding.icArrowBackDetalhesPage.setOnClickListener {
            val intent = Intent(this, HomePage::class.java)
            startActivity(intent)

        }
    }


    private fun selecionarFormaPagamento() {
        val dialogCarregando = DialogCarregando(this)
       if (binding.rbDinheiro.isChecked && binding.editTextDinheiro.text.isEmpty()){
           binding.editTextDinheiro.visibility = View.VISIBLE
           Toast.makeText(this, "Informe o troco", Toast.LENGTH_SHORT).show()
       }
       else {
           dialogCarregando.iniciarCarregamentoAlertDialog()
        Handler(Looper.getMainLooper()).postDelayed({
            dialogCarregando.liberarAlertDialog()
            val intent = Intent(this, FinalizadoPage::class.java)
            startActivity(intent)
            finish()
        }, 2000)
       }



    }


}







