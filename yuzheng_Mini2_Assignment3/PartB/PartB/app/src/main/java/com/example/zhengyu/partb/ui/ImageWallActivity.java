package com.example.zhengyu.partb.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.zhengyu.partb.R;

/**
 * Created by zhengyu on 15/11/12.
 * This class is Image wall activity, user can click button to choose which image
 * they want to visit
 */
public class ImageWallActivity extends Activity implements View.OnClickListener {

    private Button ImageButton1, ImageButton2, ImageButton3;
    private ImageView Imageview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_wall);

        // Get UI
        ImageButton1 = (Button) findViewById(R.id.imagebutton1);
        ImageButton2 = (Button) findViewById(R.id.imagebutton2);
        ImageButton3 = (Button) findViewById(R.id.imagebutton3);
        Imageview = (ImageView) findViewById(R.id.imagewallview);
        ImageButton1.setOnClickListener(this);
        ImageButton2.setOnClickListener(this);
        ImageButton3.setOnClickListener(this);
        Imageview.setImageResource(R.drawable.image1);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_image_wall, menu);
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
     * choose image through click button
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imagebutton1:
                Imageview.setImageResource(R.drawable.image1);
                break;
            case R.id.imagebutton2:
                Imageview.setImageResource(R.drawable.image2);
                break;
            case R.id.imagebutton3:
                Imageview.setImageResource(R.drawable.image3);
                break;
            default:
                break;
        }
    }
}
