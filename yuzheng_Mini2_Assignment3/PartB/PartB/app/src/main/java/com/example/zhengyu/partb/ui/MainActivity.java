package com.example.zhengyu.partb.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.zhengyu.partb.R;

/**
 * Created by zhengyu on 15/11/12.
 * This class is main activity
 */
public class MainActivity extends Activity implements View.OnClickListener {
    private Button Music_Button, Video_Button, Image_Button, Email_Button;
    private Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Find the UI Button
        Music_Button = (Button)findViewById(R.id.musicbutton);
        Video_Button = (Button)findViewById(R.id.videobutton);
        Image_Button = (Button)findViewById(R.id.wallbutton);
        Email_Button = (Button)findViewById(R.id.emailbutton);

        Music_Button.setOnClickListener(this);
        Video_Button.setOnClickListener(this);
        Image_Button.setOnClickListener(this);
        Email_Button.setOnClickListener(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    /**
     * click button would jump to different activity
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.musicbutton:
                intent = new Intent(this, MusicActivity.class);
                break;
            case R.id.videobutton:
                intent = new Intent(this, VideoActivity.class);
                break;
            case R.id.wallbutton:
                intent = new Intent(this, ImageWallActivity.class);
                break;
            case R.id.emailbutton:
                intent = new Intent(this, EmailActivity.class);
                break;
            default:
                break;
        }

        // start the activity
        startActivity(intent);
    }
}
