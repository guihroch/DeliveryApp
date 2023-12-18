package com.example.deliveryapp.activities

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.TextView
import android.widget.Toast
import com.example.deliveryapp.R
import com.example.deliveryapp.databinding.ActivityFinalizadoPageBinding

class FinalizadoPage : AppCompatActivity() {
    private lateinit var binding: ActivityFinalizadoPageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFinalizadoPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.statusBarColor = Color.parseColor("#FFFFFFFF")

        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, HomePage::class.java)
            startActivity(intent)
            finish()
        }, 2000)


    }
}
/*
*  Toast toast = Toast.makeText(MainActivity.this, "Please Give Feedback...", Toast.LENGTH_LONG);
 View view = toast.getView();

 //To change the Background of Toast
 view.setBackgroundColor(Color.TRANSPARENT);
 TextView text = (TextView) view.findViewById(android.R.id.message);

 //Shadow of the Of the Text Color
 text.setShadowLayer(0, 0, 0, Color.TRANSPARENT);
 text.setTextColor(Color.BLACK);
 text.setTextSize(Integer.valueOf(getResources().getString(R.string.text_size)));
 toast.show();
 * */