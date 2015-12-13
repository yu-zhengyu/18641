package com.example.zhengyu.mortgage.UI;

/**
 * Created by zhengyu on 15/11/4.
 * This is main Activity, user can click button go into the Add data activity
 *
 */

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.content.Intent;
import android.widget.Button;
import com.example.zhengyu.mortgage.R;



public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button addButton =
                (Button) findViewById(R.id.addbutton);
        addButton.setOnClickListener(addMortgageOnclickListener);
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
        return super.onOptionsItemSelected(item);
    }

    OnClickListener addMortgageOnclickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent addMortgage = new Intent(MainActivity.this, Add_Mortgage.class);
            startActivity(addMortgage);
        }
    };

}
