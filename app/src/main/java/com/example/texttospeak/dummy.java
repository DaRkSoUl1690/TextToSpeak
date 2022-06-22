package com.example.texttospeak;

import android.os.Build;
import android.speech.tts.TextToSpeech;
import android.speech.tts.UtteranceProgressListener;
import android.util.Log;

import java.util.Locale;

public class dummy {


//

//    textToSpeech=new TextToSpeech(this, new TextToSpeech.OnInitListener() {
//        @Override
//        public void onInit(int status) {
//            if (status==TextToSpeech.SUCCESS){
//                int result=textToSpeech.setLanguage(Locale.ENGLISH);
//
//                if (result==TextToSpeech.LANG_MISSING_DATA||result==TextToSpeech.LANG_NOT_SUPPORTED){
//                    Log.i("TextToSpeech","Language Not Supported");
//                }
//
//                textToSpeech.setOnUtteranceProgressListener(new UtteranceProgressListener() {
//                    @Override
//                    public void onStart(String utteranceId) {
//                        Log.i("TextToSpeech","On Start");
//                    }
//
//                    @Override
//                    public void onDone(String utteranceId) {
//                        Log.i("TextToSpeech","On Done");
//                    }
//
//                    @Override
//                    public void onError(String utteranceId) {
//                        Log.i("TextToSpeech","On Error");
//                    }
//                });
//
//            }else {
//                Log.i("TextToSpeech","Initialization Failed");
//            }
//        }
//    });
//
//if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//        textToSpeech.speak(text,TextToSpeech.QUEUE_FLUSH,null,TextToSpeech.ACTION_TTS_QUEUE_PROCESSING_COMPLETED);
//    }


//
//
}


//
//                if(binding.answer.getText().toString().equals(""))
//                {
//                    Toast.makeText(MainActivity.this, "Please enter the answer", Toast.LENGTH_SHORT).show();
//                }
//                else {
//                    checkIt(opt);
//                    if (Integer.parseInt(binding.answer.getText().toString()) == ans)
//                        tts.speak("Correct", TextToSpeech.QUEUE_ADD, null, null);
//                    else tts.speak("InCorrect", TextToSpeech.QUEUE_ADD, null, null);
//                    ans = 0;
//                }