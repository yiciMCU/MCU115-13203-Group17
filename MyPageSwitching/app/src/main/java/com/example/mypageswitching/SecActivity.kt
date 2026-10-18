package com.example.mypageswitching

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SecActivity : AppCompatActivity() {

    private lateinit var textView2: TextView
    private lateinit var editText1: EditText
    private lateinit var button3: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_sec)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        textView2 = findViewById(R.id.textView2)
        editText1 = findViewById(R.id.editText1)
        button3 = findViewById(R.id.button3)

        // Get text sent from MainActivity
        val receivedText = intent.getStringExtra("input_text") ?: ""
        textView2.text = receivedText

        button3.setOnClickListener {
            val replyText = editText1.text.toString()
            val resultIntent = Intent().apply {
                putExtra("reply_text", replyText)
            }
            setResult(RESULT_OK, resultIntent)
            finish()
        }
    }
}
