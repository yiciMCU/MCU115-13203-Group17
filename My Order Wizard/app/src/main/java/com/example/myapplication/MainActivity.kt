package com.example.myapplication

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.content.Intent
import android.widget.Button
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts

class MainActivity : AppCompatActivity() {
    private var selectedMain: String = ""
    private var selectedSides: String = ""
    private var selectedDrink: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
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

        val tvMain = findViewById<TextView>(R.id.textView2)
        val tvSides = findViewById<TextView>(R.id.textView3)
        val tvDrink = findViewById<TextView>(R.id.textView4)

        val startMainMealForResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                selectedMain = result.data?.getStringExtra("SELECTED_MAIN") ?: ""
                if (selectedMain.isNotEmpty()) {
                    tvMain.text = "Main: $selectedMain"
                }
            }
        }

        val startSideDishesForResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                selectedSides = result.data?.getStringExtra("SELECTED_SIDES") ?: ""
                if (selectedSides.isNotEmpty()) {
                    tvSides.text = "Sides: $selectedSides"
                }
            }
        }

        val startDrinkForResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                selectedDrink = result.data?.getStringExtra("SELECTED_DRINK") ?: ""
                if (selectedDrink.isNullOrEmpty().not()) {
                    tvDrink.text = "Drink: $selectedDrink"
                }
            }
        }

        val button1 = findViewById<Button>(R.id.button1)
        button1.setOnClickListener {
            val intent = Intent(this, MainMealActivity::class.java)
            startMainMealForResult.launch(intent)
        }

        val button2 = findViewById<Button>(R.id.button2)
        button2.setOnClickListener {
            val intent = Intent(this, SideDishesActivity::class.java)
            startSideDishesForResult.launch(intent)
        }

        val button3 = findViewById<Button>(R.id.button3)
        button3.setOnClickListener {
            val intent = Intent(this, DrinkActivity::class.java)
            startDrinkForResult.launch(intent)
        }

        val button4 = findViewById<Button>(R.id.button4)
        button4.setOnClickListener {
            val intent = Intent(this, ConfirmActivity::class.java).apply {
                putExtra("EXTRA_MAIN", selectedMain)
                putExtra("EXTRA_SIDES", selectedSides)
                putExtra("EXTRA_DRINK", selectedDrink)
            }
            startActivity(intent)
        }
    }
}
