package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ConfirmActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_confirm)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            val density = resources.displayMetrics.density
            val padding20dp = (20 * density).toInt()
            v.setPadding(
                systemBars.left + padding20dp,
                systemBars.top + padding20dp,
                systemBars.right + padding20dp,
                systemBars.bottom + padding20dp
            )
            insets
        }

        val main = intent.getStringExtra("EXTRA_MAIN") ?: ""
        val sides = intent.getStringExtra("EXTRA_SIDES") ?: ""
        val drink = intent.getStringExtra("EXTRA_DRINK") ?: ""

        val tvMain = findViewById<TextView>(R.id.textView2)
        val tvSides = findViewById<TextView>(R.id.textView3)
        val tvDrink = findViewById<TextView>(R.id.textView4)

        tvMain.text = "Main: ${if (main.isNotEmpty()) main else "Not selected"}"
        tvSides.text = "Sides: ${if (sides.isNotEmpty()) sides else "Not selected"}"
        tvDrink.text = "Drink: ${if (drink.isNotEmpty()) drink else "Not selected"}"

        val buttonConfirm = findViewById<Button>(R.id.buttonConfirm)
        buttonConfirm.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
        }
    }
}
