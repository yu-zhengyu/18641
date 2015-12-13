package com.example.zhengyu.partb.ui;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.VideoView;

import com.example.zhengyu.partb.R;

/**
 * Created by zhengyu on 15/11/12.
 * ThIS Class server for Video part, user can play and pause video in this activity
 */

public class VideoActivity extends Activity implements View.OnClickListener {

    private Button Video1Button, Video2Button, VideoPauseButton;
    private VideoView Vdeoview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        Vdeoview = (VideoView) findViewById(R.id.videoview);
        Video1Button = (Button) findViewById(R.id.video1button);
        Video2Button = (Button) findViewById(R.id.video2buton);
        VideoPauseButton = (Button) findViewById(R.id.videopausebutton);
        Video1Button.setOnClickListener(this);
        Video2Button.setOnClickListener(this);
        VideoPauseButton.setOnClickListener(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_video, menu);
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

    @Override
    protected void onPause() {
        super.onPause();
        Vdeoview.stopPlayback();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Vdeoview.stopPlayback();
    }

    @Override
    public void onClick(View v) {
        try {
            switch (v.getId()) {
                case R.id.video1button:
                    Vdeoview.stopPlayback();
                    Vdeoview.setVideoPath("android.resource://" + getPackageName() + "/" + R.raw.video1);
                    Vdeoview.start();
                    break;
                case R.id.video2buton:
                    Vdeoview.stopPlayback();
                    Vdeoview.setVideoPath("android.resource://" + getPackageName() + "/" + R.raw.video2);
                    Vdeoview.start();
                    break;
                case R.id.videopausebutton:
                    if (Vdeoview.isPlaying())
                        Vdeoview.stopPlayback();
                default:
                    break;
            }
        } catch (Exception e) {
            Log.e("VIDEOERROR", e.toString());
        }
    }
}
