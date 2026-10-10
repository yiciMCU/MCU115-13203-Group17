package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SideDishesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_side_dishes)
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

        val cbFries = findViewById<CheckBox>(R.id.cbFries)
        val cbSalad = findViewById<CheckBox>(R.id.cbSalad)
        val cbCornCup = findViewById<CheckBox>(R.id.cbCornCup)
        val buttonDone = findViewById<Button>(R.id.buttonDone)

        buttonDone.setOnClickListener {
            val selectedSides = mutableListOf<String>()
            if (cbFries.isChecked) selectedSides.add(cbFries.text.toString())
            if (cbSalad.isChecked) selectedSides.add(cbSalad.text.toString())
            if (cbCornCup.isChecked) selectedSides.add(cbCornCup.text.toString())

            if (selectedSides.isNotEmpty()) {
                val sidesText = selectedSides.joinToString(", ")
                val intent = Intent().apply {
                    putExtra("SELECTED_SIDES", sidesText)
                }
                setResult(RESULT_OK, intent)
                finish()
            }
        }
    }
}
