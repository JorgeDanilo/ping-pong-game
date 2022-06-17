package jd.sistemas.android.pingpong;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    Boolean audioState;
    ImageButton ibAudio;

    private static final String AUDIO_STATE = "audioState";
    private static final String PREF_NAME = "my_pref";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        ibAudio = findViewById(R.id.ibAudio);
        sharedPreferences = getSharedPreferences(PREF_NAME, 0);
        audioState = sharedPreferences.getBoolean(AUDIO_STATE, true);
        if (audioState) {
            ibAudio.setImageResource(R.drawable.audio_on);
        } else {
            ibAudio.setImageResource(R.drawable.audio_off);
        }
    }

    public void startGame(View view) {
        GameView gameView = new GameView(this);
        setContentView(gameView);
    }

    public void audioPref(View view) {
        if (audioState) {
            audioState = false;
            ibAudio.setImageResource(R.drawable.audio_off);
        } else {
            audioState = true;
            ibAudio.setImageResource(R.drawable.audio_on);
        }

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(AUDIO_STATE, audioState);
        editor.commit();
    }
}