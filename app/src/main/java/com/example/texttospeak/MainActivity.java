package com.example.texttospeak;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.texttospeak.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    TextToSpeech tts;
    ActivityMainBinding binding;
    String opt = "", text = "", text2 = "";
    int ans = 0, count = 0;
    Intent intent;
    SpeechRecognizer speechRecognizer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        speechRecognizer = SpeechRecognizer.createSpeechRecognizer(this);
        intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);


//
        tts = new TextToSpeech(this, status -> {
            tts.setSpeechRate(1.0f);
            tts.setLanguage(Locale.UK);
            tts.setOnUtteranceCompletedListener(utteranceId -> runOnUiThread(this::speaking));
        });

        HashMap<String, String> params = new HashMap<>();
        params.put(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID, "stringId");

        binding.speakBtn.setOnClickListener(v -> {
            checkIt(opt);
            tts.speak(binding.firstNumber.getText().toString() + text2 + binding.secondNumber.getText().toString() + "", TextToSpeech.QUEUE_ADD, params);

        });


        if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECORD_AUDIO}, 1);
        }

        speechRecognizer.setRecognitionListener(new RecognitionListener() {
            @Override
            public void onReadyForSpeech(Bundle params) {
            }

            @Override
            public void onBeginningOfSpeech() {
            }

            @Override
            public void onRmsChanged(float rmsdB) {

            }

            @Override
            public void onBufferReceived(byte[] buffer) {

            }

            @Override
            public void onEndOfSpeech() {
                tts.speak(binding.firstNumber.getText().toString() + text2 + binding.secondNumber.getText().toString() + "", TextToSpeech.QUEUE_ADD, params);

            }

            @Override
            public void onError(int error) {

            }

            @Override
            public void onResults(Bundle results) {
                ArrayList<String> data = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);

                binding.answer.setText(data.get(0));
                checkIt(opt);
                Toast.makeText(MainActivity.this, "You spoke:" + data.get(0), Toast.LENGTH_SHORT).show();
                try {
                    if (Integer.parseInt(data.get(0)) == ans) {
                        tts.speak("Correct", TextToSpeech.QUEUE_ADD, params);
                    } else {
                        tts.speak("InCorrect", TextToSpeech.QUEUE_ADD, params);
                    }
                    ans = 0;
                } catch (Exception e) {
                    Toast.makeText(MainActivity.this, e.getMessage() + "", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onPartialResults(Bundle partialResults) {
            }

            @Override
            public void onEvent(int eventType, Bundle params) {

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
            text2 = "add";
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


    void speaking() {
        speechRecognizer.startListening(intent);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1) {
            Toast.makeText(MainActivity.this, "Permission Granted", Toast.LENGTH_SHORT).show();
        }

    }


    @Override
    public void onDestroy() {
        if (tts != null) {
            tts.stop();
            tts.shutdown();
        }
        super.onDestroy();
    }
}