package com.example.deliveryapp.activities

import android.annotation.SuppressLint
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
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import com.google.firebase.auth.oAuthCredential

class CadastroPage : AppCompatActivity() {
    private lateinit var binding: ActivityCadastroPageBinding
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
            cadastroUsuario()
        }

    }
    private fun cadastroUsuario() {
        val nome = binding.editNome.text.toString()
        val email = binding.editEmail.text.toString()
        val senha = binding.editSenha.text.toString()
        val emailRegex = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}\$"

        when {
            nome.isEmpty() -> {
                binding.layoutEditTextNome.helperText = "Nome é obrigatório!"
                binding.layoutEditTextNome.boxStrokeColor = Color.parseColor("#DD4247")
                Handler(Looper.getMainLooper()).postDelayed({
                    binding.layoutEditTextNome.helperText = ""
                }, 1500)
            }

            email.isEmpty() -> {
                binding.layoutEditTextEmail.helperText = "Email é obrigatório!"
                binding.layoutEditTextEmail.boxStrokeColor = Color.parseColor("#DD4247")
                Handler(Looper.getMainLooper()).postDelayed({
                    binding.layoutEditTextEmail.helperText = ""
                }, 1500)
            }

            !email.matches(emailRegex.toRegex()) -> {
                binding.layoutEditTextEmail.helperText = "Digite um email válido!"
                binding.layoutEditTextEmail.boxStrokeColor = Color.parseColor("#DD4247")
                Handler(Looper.getMainLooper()).postDelayed({
                    binding.layoutEditTextEmail.helperText = ""
                }, 1500)
            }

            senha.isEmpty() -> {
                binding.layoutEditTextSenha.helperText = "Senha é obrigatório!"
                binding.layoutEditTextSenha.boxStrokeColor = Color.parseColor("#DD4247")
                Handler(Looper.getMainLooper()).postDelayed({
                    binding.layoutEditTextSenha.helperText = ""
                }, 1500)
            }

           else  -> {
               cadastroFirebase()
           }
        }
    }

    private fun cadastroFirebase(){
        val emailCadastro = binding.editEmail.text.toString()
        val senhaCadastro = binding.editSenha.text.toString()
        val auth = FirebaseAuth.getInstance()

        binding.buttonCadastrar.visibility = View.INVISIBLE
        binding.containerProgressBar.visibility = View.VISIBLE

        auth.createUserWithEmailAndPassword(emailCadastro, senhaCadastro).addOnCompleteListener { cadastro ->
            if (cadastro.isSuccessful) {
                Handler(Looper.getMainLooper()).postDelayed({
                    binding.buttonCadastrar.visibility = View.VISIBLE
                    binding.containerProgressBar.visibility = View.GONE
                    Toast.makeText(this, "Cadastro realizado com sucesso!", Toast.LENGTH_LONG).show()
                }, 2000)
            }

        }.addOnFailureListener {
           val erro = it
            when {
                erro is FirebaseAuthWeakPasswordException -> {
                    binding.layoutEditTextSenha.helperText =
                        "A senha deve ter pelo menos 6 caracteres!"
                    binding.layoutEditTextSenha.boxStrokeColor = Color.parseColor("#DD4247")
                    binding.containerProgressBar.visibility = View.GONE
                    binding.buttonCadastrar.visibility = View.VISIBLE
                    Handler(Looper.getMainLooper()).postDelayed({
                        binding.layoutEditTextSenha.helperText = ""
                        binding.layoutEditTextSenha.boxStrokeColor = Color.parseColor("#171515")
                    }, 2000)
                }

                erro is FirebaseAuthUserCollisionException -> {
                    binding.layoutEditTextEmail.helperText = "Este usuário ja foi cadastrado!"
                    binding.layoutEditTextEmail.boxStrokeColor = Color.parseColor("#DD4247")
                    binding.containerProgressBar.visibility = View.GONE
                    binding.buttonCadastrar.visibility = View.VISIBLE
                    Handler(Looper.getMainLooper()).postDelayed({
                        binding.layoutEditTextEmail.helperText = ""
                        binding.layoutEditTextEmail.boxStrokeColor = Color.parseColor("#171515")
                    }, 2000)
                }
                erro is FirebaseNetworkException -> {
                    Toast.makeText(this, "Sem conexão com a internet!", Toast.LENGTH_SHORT)
                        .show()
                }


            }
        }


    }
}
  /*  private fun cadastroNovoUsuario(){
        binding.buttonCadastrar.visibility = View.INVISIBLE
        binding.containerProgressBar.visibility = View.VISIBLE
        Handler(Looper.getMainLooper()).postDelayed({
            binding.buttonCadastrar.visibility = View.VISIBLE
            binding.containerProgressBar.visibility = View.GONE
            Toast.makeText(this, "Cadastrado com Sucesso!", Toast.LENGTH_LONG).show()
        }, 1500)
    }
    @SuppressLint("SetTextI18n")
    private fun logicaValidarEmailCadastro() {
        val email = binding.editEmail.text.toString()
        val emailRegex = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}\$"

        when {
            email.isEmpty() -> {
                binding.layoutEditTextNome.helperText = "Email é obrigatório!"
                binding.layoutEditTextNome.boxStrokeColor = Color.parseColor("#DD4247")
                Handler(Looper.getMainLooper()).postDelayed({
                    binding.layoutEditTextNome.helperText = ""
                    binding.layoutEditTextNome.boxStrokeColor = Color.parseColor("#171515")
                }, 2000)
            }

            !email.matches(emailRegex.toRegex()) -> {
                binding.layoutEditTextNome.helperText = "Digite um email válido!"
                binding.layoutEditTextNome.boxStrokeColor = Color.parseColor("#DD4247")
                Handler(Looper.getMainLooper()).postDelayed({
                    binding.layoutEditTextNome.helperText = ""
                    binding.layoutEditTextNome.boxStrokeColor = Color.parseColor("#171515")
                }, 2000)

            }
            else -> {


                binding.textSenha.visibility = View.VISIBLE
                binding.editSenha.visibility = View.VISIBLE
                binding.buttonCadastrar.visibility = View.VISIBLE
            }
        }


    }

    private fun cadastroFirebase() {
        val emailCadastro = binding.editEmail.text.toString()
        val senhaCadastro = binding.editSenha.text.toString()
        auth = FirebaseAuth.getInstance()
        if(senhaCadastro.isEmpty()) {
            binding.editTextSenha.helperText =
                "Senha é obrigatório!"
            binding.editTextSenha.boxStrokeColor = Color.parseColor("#DD4247")
            Handler(Looper.getMainLooper()).postDelayed({
                binding.editTextSenha.helperText = ""
                binding.editTextSenha.boxStrokeColor = Color.parseColor("#171515")
            }, 2000)
        } else {
            auth.createUserWithEmailAndPassword(emailCadastro, senhaCadastro)
                .addOnCompleteListener { cadastro ->
                    if (cadastro.isSuccessful) {
                        Handler(Looper.getMainLooper()).postDelayed({
                            binding.containerProgressbar.visibility = View.GONE
                            binding.buttonCadastrar.visibility = View.VISIBLE
                            toastSucesso()
                        }, 2000)
                    }

                }.addOnFailureListener {
                    val erro = it
                    when {
                        erro is FirebaseAuthWeakPasswordException -> {
                            binding.editTextSenha.helperText =
                                "A senha deve ter pelo menos 6 caracteres!"
                            binding.editTextSenha.boxStrokeColor = Color.parseColor("#DD4247")
                            binding.containerProgressbar.visibility = View.GONE
                            binding.buttonCadastrar.visibility = View.VISIBLE
                            Handler(Looper.getMainLooper()).postDelayed({
                                binding.editTextSenha.helperText = ""
                                binding.editTextSenha.boxStrokeColor = Color.parseColor("#171515")
                            }, 2000)
                        }

                        erro is FirebaseAuthUserCollisionException -> {
                            binding.editTextEmail.helperText = "Este usuário ja foi cadastrado!"
                            binding.editTextEmail.boxStrokeColor = Color.parseColor("#DD4247")
                            binding.containerProgressbar.visibility = View.GONE
                            binding.buttonCadastrar.visibility = View.VISIBLE
                            Handler(Looper.getMainLooper()).postDelayed({
                                binding.editTextEmail.helperText = ""
                                binding.editTextEmail.boxStrokeColor = Color.parseColor("#171515")
                            }, 2000)
                        }
                        erro is FirebaseNetworkException -> {
                            Toast.makeText(this, "Sem conexão com a internet!", Toast.LENGTH_SHORT)
                                .show()
                        }
                        else -> {
                            customToastError()
                        }
                    }
                }
        }
  */


