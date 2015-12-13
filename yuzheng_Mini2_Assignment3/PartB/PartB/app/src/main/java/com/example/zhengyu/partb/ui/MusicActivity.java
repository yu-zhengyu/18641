package com.example.zhengyu.partb.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.media.MediaPlayer;

import com.example.zhengyu.partb.R;

/**
 * Created by zhengyu on 15/11/12.
 * ThIS Class server for music part, user can play and pause music in this activity
 */

public class MusicActivity extends Activity implements View.OnClickListener {

    private Button play1, play2, pause1, pause2;
    private MediaPlayer music1, music2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);

        // get the UI
        play1 = (Button) findViewById(R.id.music1playbutton);
        play2 = (Button) findViewById(R.id.music1playbutton2);
        pause1 = (Button) findViewById(R.id.mp3pausebutton);
        pause2 = (Button) findViewById(R.id.mp3pausebutton2);
        play1.setOnClickListener(this);
        play2.setOnClickListener(this);
        pause1.setOnClickListener(this);
        pause2.setOnClickListener(this);
        music1 = MediaPlayer.create(this, R.raw.music1);
        music2 = MediaPlayer.create(this, R.raw.music2);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_music, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    // have to override the onPause method, avoid the appear error with music
    @Override
    protected void onPause() {
        super.onPause();
        music1.stop();
        music2.stop();
    }

    // have to override the onStop method, avoid the appear error with music
    @Override
    protected void onStop() {
        super.onStop();
        music1.stop();
        music2.stop();
    }

    // Onclick function, each button has their own reflection
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            // play the music1
            case R.id.music1playbutton:
                if (!music1.isPlaying()) {
                    music1.start();
                    Toast.makeText(MusicActivity.this, "Music1 is playing", Toast.LENGTH_SHORT).show();
                }

                break;
            // play the music2
            case R.id.music1playbutton2:
                if (!music2.isPlaying()) {
                    music2.start();
                    Toast.makeText(MusicActivity.this, "Music2 is playing", Toast.LENGTH_SHORT).show();
                }
                break;
            // pause the music1
            case R.id.mp3pausebutton:
                if (music1.isPlaying()) {
                    music1.pause();
                    Toast.makeText(MusicActivity.this, "Music1 has pause", Toast.LENGTH_SHORT).show();
                }
                break;
            // pause the music2
            case R.id.mp3pausebutton2:
                if (music2.isPlaying()) {
                    music2.pause();
                    Toast.makeText(MusicActivity.this, "Music2 has pause", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                break;
        }
    }
}
