package com.codingbydumbbell.texttospeechdemo;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private static final String REQUEST_CODE_TTS = "TextToSpeech";
    private static final String TAG = MainActivity.class.getSimpleName();
    private EditText editText;
    private TextToSpeech tts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = findViewById(R.id.editText);

        setTTS();
    }

    private void setTTS() {
        if (tts != null) return;

        // 建立 TTS 物件
        tts = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                if (i == TextToSpeech.SUCCESS) { // TTS 初始化成功

                    tts.setLanguage(Locale.CHINESE);
                    Log.d(TAG, "isLanguageAvailable(Locale.CHINESE): " + tts.isLanguageAvailable(Locale.CHINESE));

                    tts.setLanguage(Locale.US);
                    Log.d(TAG, "isLanguageAvailable(Locale.US): " + tts.isLanguageAvailable(Locale.US));

                    tts.setSpeechRate(1); // 設定語速
                    tts.setPitch(1); // 設定音調
                }
            }
        });
    }

@Override
protected void onDestroy() {
    // 釋放 TTS
    if (tts != null) tts.shutdown();

    super.onDestroy();
}

    public void onClick(View view) {
        String text = editText.getText().toString();
        if (text.isEmpty())
            text = "請輸入訊息";
        tts.speak(text, TextToSpeech.QUEUE_ADD, null, REQUEST_CODE_TTS);
    }
}
