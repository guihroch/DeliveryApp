package com.example.deliveryapp.dialog

import android.app.Activity
import android.app.AlertDialog
import android.service.voice.VoiceInteractionSession.ActivityId
import com.example.deliveryapp.R

class DialogCarregando(private val activity:Activity) {
lateinit var dialog:AlertDialog


    fun iniciarCarregamentoAlertDialog(){
        val builder = AlertDialog.Builder(activity)
        val layoutInflater = activity.layoutInflater
        builder.setView(layoutInflater.inflate(R.layout.dialog_carregando, null))
        builder.setCancelable(true)
        dialog = builder.create()
        dialog.show()
    }

    fun liberarAlertDialog(){
        dialog.dismiss()
    }
}