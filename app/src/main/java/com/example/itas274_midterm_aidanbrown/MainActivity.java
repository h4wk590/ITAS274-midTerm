package com.example.itas274_midterm_aidanbrown;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;
import java.util.Random;

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.StringTokenizer;

/**
 * Itas 274 Mid term
 *
 * Aidan Brown
 * 04 Feb 2020
 *
 * This Android app translates English speech to Pirate speech using an onclick button
 *
 * It adds random words to the textView
 * and Uses text-to-speech engine as well.
 */

public class MainActivity extends AppCompatActivity {




    private TextToSpeech tts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tts = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                Toast.makeText(MainActivity.this, "Text To Speech Engine Initialized.", Toast.LENGTH_SHORT).show();
                if (status == TextToSpeech.SUCCESS) {
                    int result = tts.setLanguage(Locale.US);
                    if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                        Log.e("TTS", "This Language is not supported");
                    }

                } else {
                    Toast.makeText(MainActivity.this, "Initilization Failed!", Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    private void speak(String text){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            tts.speak(text, TextToSpeech.QUEUE_FLUSH, null, null);
        }else{
            tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
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


    // The translate method takes users input from the editText field and translates it to 'pirate' language
    // Works when more than one word is put in the editText view.
    public void translate(View view) {

        EditText inputText = findViewById(R.id.editText2);
        String paragraph1 = inputText.getText().toString() + "\n";
        StringBuffer paragraph2 = new StringBuffer();
        HashMap<String, String> words = new HashMap<String, String>();

        words.put("Hello", "Ahoy");
        words.put("Hi", "Ahoy");
        words.put("yes", "Aye");
        words.put("ok", "Aye");
        words.put("no", "Nay");
        words.put("Treasure", "Booty");
        words.put("money", "Loot");
        words.put("cash", "gold");

        words.put("everyone", "all hands");
        words.put("friend", "bucko");
        words.put("rob", "pillage");
        words.put("sea", "bring");
        words.put("see", "spye");
        words.put("beer", "grog");
        words.put("friends", "me hearties");
        words.put("jerk", "scallywag");
        words.put("pirate", "buccaneer");
        words.put("bag", "duffle");
        words.put("your", "yer");
        words.put("me", "my");
        words.put("telescope", "spyglass");
        words.put("kitchen", "galley");
        words.put("boy", "lad");
        words.put("girl", "lass");
        words.put("clean", "swab");
        words.put("wow", "blimey");
        words.put("room", "cabin");
        words.put("quickly", "smartly");
        words.put("bed", "cot");
        words.put("surprise", "sink me");
        words.put("food", "grub");
        words.put("cheat", "hornswaggle");
        words.put("sailor", "Jack Tar");
        words.put("the", "thee");
        words.put("store", "market");
        words.put("I", "eye");
        words.put("hungry", "starvin");
        words.put("bad", "vile");
        words.put("hit", "skewer");
        words.put("wind", "blow");
        words.put("windy", "good blow");

        StringTokenizer st = new StringTokenizer(paragraph1, " .,", true);
        speak(paragraph2.toString());

        // PART 4 attempts to add random words from an array list to the textView


        while (st.hasMoreTokens()) {
            String nextWord = st.nextToken();
            Log.d("midterm_log2", nextWord);

            if (words.containsKey(nextWord)) {
                paragraph2.append(words.get(nextWord));
            } else if (nextWord.equalsIgnoreCase("you")) {
                // PART 3
                String[] list = new String[]{"", "ye", "ye filthy", "ye yellow bellied"};
                Random rand = new Random();
                Log.d("midterm_log3", nextWord);
            } else {
                paragraph2.append(nextWord);
            }
        }

        // PART 5 attempts to show the translation in the bottom text view

        String[] list2 = new String[]{"ye son of a biscuit eater", "ye dog", "ye sea rat", "ye salty sea bass"};
        Random rand2 = new Random();
        TextView outputText = findViewById(R.id.textView4);
        outputText.setText(paragraph2);
        // PART 6
        // call the TTS to say the translation
        speak(paragraph2.toString());
    }
}


