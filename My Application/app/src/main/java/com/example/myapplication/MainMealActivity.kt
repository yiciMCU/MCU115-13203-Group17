package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainMealActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main_meal)
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

        val radioGroup = findViewById<RadioGroup>(R.id.radioGroup)
        val buttonDone = findViewById<Button>(R.id.buttonDone)

        buttonDone.setOnClickListener {
            val selectedId = radioGroup.checkedRadioButtonId
            if (selectedId != -1) {
                val radioButton = findViewById<RadioButton>(selectedId)
                val mealName = radioButton.text.toString()

                val intent = Intent().apply {
                    putExtra("SELECTED_MAIN", mealName)
                }
                setResult(RESULT_OK, intent)
                finish()
            }
        }
    }
}
