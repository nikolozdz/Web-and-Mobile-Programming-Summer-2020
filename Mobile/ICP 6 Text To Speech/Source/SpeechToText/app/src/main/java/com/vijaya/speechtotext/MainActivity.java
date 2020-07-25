package com.vijaya.speechtotext;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private final int CHECK_CODE = 0x1;

    private static final int REQ_CODE_SPEECH_INPUT = 100;
    private TextView mVoiceInputTv;
    private ImageButton mSpeakBtn;
    private TextToSpeech mtts;
    private boolean ready = false;

    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    private static final String PREFS = "prefs";
    private static String NAME = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        preferences = getSharedPreferences(PREFS,0);
        editor = preferences.edit();

        mtts=new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status != TextToSpeech.ERROR) {
                    mtts.setLanguage(Locale.US);
                    mtts.speak("Hello", TextToSpeech.QUEUE_FLUSH, null);
                }
            }
        });

        mVoiceInputTv = (TextView) findViewById(R.id.voiceInput);
        mSpeakBtn = (ImageButton) findViewById(R.id.btnSpeak);
        mSpeakBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startVoiceInput();
            }
        });
    }

    private void startVoiceInput() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Hello, How can I help you?");
        try {
            startActivityForResult(intent, REQ_CODE_SPEECH_INPUT);
        } catch (ActivityNotFoundException a) {

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQ_CODE_SPEECH_INPUT: {
                if (resultCode == RESULT_OK && null != data) {
                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    answer(result.get(0));
                }
                break;
            }

        }
    }
    public void answer(String text){
        mtts.setLanguage(Locale.ENGLISH);

        mVoiceInputTv.append("You: "+text+"\n");

        if (text.contains("hello") || text.contains("hi")){
            mtts.speak("What is your name", TextToSpeech.QUEUE_FLUSH, null);
            mVoiceInputTv.append("Bot: What is your name?\n");
        }
        if (text.contains("time")){
            SimpleDateFormat sdfDate = new SimpleDateFormat("HH:mm");//dd/MM/yyyy
            Date now = new Date();
            String[] strDate = sdfDate.format(now).split(":");
            if(strDate[1].contains("00")) {
                strDate[1] = "o'clock";
            }
            mtts.speak("The time is " + sdfDate.format(now), TextToSpeech.QUEUE_FLUSH, null);
            mVoiceInputTv.append("Bot: The time is " + sdfDate.format(now)+"\n");
        }
        if (text.contains("my")&&text.contains("name")){
            String[] speech = text.split(" ");
            NAME = speech[speech.length-1];
            mtts.speak("Nice to meet you "+NAME+". How can I help you?", TextToSpeech.QUEUE_FLUSH, null);
            mVoiceInputTv.append("\nBot: Nice to meet you "+NAME+". How can I help you?\n");
        }
        if (text.contains("not")&&text.contains("feeling")&&text.contains("good")){
            mtts.speak("I can understand. Please tell your symptoms in short.", TextToSpeech.QUEUE_FLUSH, null);
            mVoiceInputTv.append("Bot: I can understand. Please tell your symptoms in short.\n");
        }
        if(text.contains("thank")) {
            if (NAME != "") {
                mtts.speak("Thank you too " + NAME +". Take care", TextToSpeech.QUEUE_FLUSH, null);
                mVoiceInputTv.append("Bot: Thank you too " + NAME +". Take care.\n");
            }
            else{
                mtts.speak("Thank you too. Take care", TextToSpeech.QUEUE_FLUSH, null);
                mVoiceInputTv.append("Bot: Thank you too. Take care.\n");
            }
        }
        if(text.contains("clear")){
            mVoiceInputTv.setText("");
        }


    }





}