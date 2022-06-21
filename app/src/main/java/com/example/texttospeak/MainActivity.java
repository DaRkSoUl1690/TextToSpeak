package com.example.texttospeak;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.texttospeak.databinding.ActivityMainBinding;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    TextToSpeech tts;
    ActivityMainBinding binding;
    String opt = "", text = "", text2 = "";
    int ans = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        tts = new TextToSpeech(getApplicationContext(), status -> {
            if (status != TextToSpeech.ERROR) {
                tts.setLanguage(Locale.UK);
                tts.setSpeechRate(1.0f);
            }
        });


        binding.speakBtn.setOnClickListener(v -> {
            checkIt(opt);
            tts.speak(binding.firstNumber.getText().toString() + text2 + binding.secondNumber.getText().toString() + "", TextToSpeech.QUEUE_ADD, null, null);

        });


            binding.verifyBtn.setOnClickListener(v -> {
                if(binding.answer.getText().toString().equals(""))
                {
                    Toast.makeText(MainActivity.this, "Please enter the answer", Toast.LENGTH_SHORT).show();
                }
                else {
                    checkIt(opt);
                    if (Integer.parseInt(binding.answer.getText().toString()) == ans)
                        tts.speak("Correct", TextToSpeech.QUEUE_ADD, null, null);
                    else tts.speak("InCorrect", TextToSpeech.QUEUE_ADD, null, null);
                    ans = 0;
                }
            });
        }



    public void onClick(@NonNull View v) {

        if (v.getId() == R.id.add) {
            updateText(v);
        } else if (v.getId() == R.id.minus) {
            updateText(v);
        } else if (v.getId() == R.id.multiply) {
            updateText(v);
        } else if (v.getId() == R.id.divide) {
            updateText(v);
        }
    }


    public void updateText(View v) {
        Button btn = findViewById(v.getId());
        text = btn.getText().toString();
        binding.operator.setText(text);
        opt = text;
    }

    public void checkIt(String op) {
        if (binding.firstNumber.getText().toString().equals("") || binding.secondNumber.getText().toString().equals("")) {
            Toast.makeText(MainActivity.this, "Enter Your values Please", Toast.LENGTH_SHORT).show();
            return;
        }
        if (op.equals("+")) {
            ans = Integer.parseInt(binding.firstNumber.getText().toString()) + Integer.parseInt(binding.secondNumber.getText().toString());
        }
        if (op.equals("*")) {
            ans = Integer.parseInt(binding.firstNumber.getText().toString()) * Integer.parseInt(binding.secondNumber.getText().toString());
            text2 = "Multiply";
        }
        if (op.equals("-")) {
            ans = Integer.parseInt(binding.firstNumber.getText().toString()) - Integer.parseInt(binding.secondNumber.getText().toString());
            text2 = "Minus";
        }
        if (op.equals("/")) {
            ans = Integer.parseInt(binding.firstNumber.getText().toString()) / Integer.parseInt(binding.secondNumber.getText().toString());

            text2 = "divide";
        }

    }
}