package com.example.mypageswitching

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private lateinit var editText1: EditText
    private lateinit var button3: Button
    private lateinit var textView2: TextView

    private val secActivityResultLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            val replyText = result.data?.getStringExtra("reply_text") ?: ""
            textView2.text = replyText
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        editText1 = findViewById(R.id.editText1)
        button3 = findViewById(R.id.button3)
        textView2 = findViewById(R.id.textView2)

        button3.setOnClickListener {
            val textToSend = editText1.text.toString()
            val intent = Intent(this, SecActivity::class.java).apply {
                putExtra("input_text", textToSend)
            }
            secActivityResultLauncher.launch(intent)
        }
    }
}
