package com.example.mycalculator;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    TextView textView1;
    TextView textView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView1 = findViewById(R.id.textView1);
        textView2 = findViewById(R.id.textView2);

        Button button0 = findViewById(R.id.button0);
        Button button1 = findViewById(R.id.button1);
        Button button2 = findViewById(R.id.button2);
        Button button3 = findViewById(R.id.button3);
        Button button4 = findViewById(R.id.button4);
        Button button5 = findViewById(R.id.button5);
        Button button6 = findViewById(R.id.button6);
        Button button7 = findViewById(R.id.button7);
        Button button8 = findViewById(R.id.button8);
        Button button9 = findViewById(R.id.button9);

        Button buttonAD = findViewById(R.id.buttonAD);
        Button buttonC = findViewById(R.id.buttonC);
        Button buttonDelete = findViewById(R.id.buttonDelete);
        Button buttonDivide = findViewById(R.id.buttonDivide);
        Button buttonMultiply = findViewById(R.id.buttonMultiply);
        Button buttonMinus = findViewById(R.id.buttonMinus);
        Button buttonPlus = findViewById(R.id.buttonPlus);
        Button buttonEqual = findViewById(R.id.buttonEqual);
        Button buttonDot = findViewById(R.id.buttonDot);

        button0.setOnClickListener(v -> {
            String current = textView2.getText().toString();
            if (!current.equals("0")) {
                textView2.append("0");
            }
        });
        button1.setOnClickListener(v -> appendDigit("1"));
        button2.setOnClickListener(v -> appendDigit("2"));
        button3.setOnClickListener(v -> appendDigit("3"));
        button4.setOnClickListener(v -> appendDigit("4"));
        button5.setOnClickListener(v -> appendDigit("5"));
        button6.setOnClickListener(v -> appendDigit("6"));
        button7.setOnClickListener(v -> appendDigit("7"));
        button8.setOnClickListener(v -> appendDigit("8"));
        button9.setOnClickListener(v -> appendDigit("9"));

        buttonAD.setOnClickListener(v -> {
            textView1.setText("");
            textView2.setText("0");
        });
        buttonC.setOnClickListener(v -> textView2.setText("0"));
        buttonDelete.setOnClickListener(v -> {
            String current = textView2.getText().toString();
            if (current.length() > 1) {
                current = current.substring(0, current.length() - 1);
                textView2.setText(current);
            } else {
                textView2.setText("0");
            }
        });
        buttonDivide.setOnClickListener(v -> textView2.append("÷"));
        buttonMultiply.setOnClickListener(v -> textView2.append("×"));
        buttonMinus.setOnClickListener(v -> textView2.append("-"));
        buttonPlus.setOnClickListener(v -> textView2.append("+"));
        buttonEqual.setOnClickListener(v -> {
            textView1.setText(textView2.getText().toString());
            textView2.setText("0");
        });
        buttonDot.setOnClickListener(v -> textView2.append("."));
    }

    private void appendDigit(String digit) {
        String current = textView2.getText().toString();
        if (current.equals("0")) {
            textView2.setText(digit);
        } else {
            textView2.append(digit);
        }
    }
}