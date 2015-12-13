package com.example.zhengyu.partb.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.zhengyu.partb.R;
import com.example.zhengyu.partb.exception.UserException;

/**
 * Created by zhengyu on 15/11/12.
 * This class would help user send email automatically
 */

public class EmailActivity extends Activity implements View.OnClickListener {
    // UI
    Button send;
    EditText SubjectEdit, ContentEdit;

    // Mail List
    private static String[] EmailList = {"yuzheng@andrew.cmu.edu", "zhengyu_sysu@126.com"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email);
        send = (Button) findViewById(R.id.sendemailButton);
        SubjectEdit = (EditText) findViewById(R.id.subjectEdit);
        ContentEdit = (EditText) findViewById(R.id.contentEdit);
        send.setOnClickListener(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_email, menu);
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

    // check user input is legal or not
    public boolean islegal() {
        String subject, content;
        subject = SubjectEdit.getText().toString();
        content = ContentEdit.getText().toString();
        if (subject.length() == 0 || content.length() == 0)
            return false;
        return true;
    }

    // Click function
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sendemailButton:
                if (!islegal()) {
                    try {
                        throw new UserException("No Input!!");
                    } catch (UserException e) {
                        e.printStackTrace();
                        Log.e("Email", e.getMsg());
                        Toast.makeText(this, "Please input subject and Content", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    try {
                        Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
                        emailIntent.setType("plain/text");
                        emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, EmailList);
                        emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, ContentEdit.getText().toString());
                        emailIntent.putExtra(Intent.EXTRA_TEXT, SubjectEdit.getText().toString());
                        startActivity(emailIntent);

                    } catch (android.content.ActivityNotFoundException ex) {
                        Toast.makeText(this, "Unknown Error!!!", Toast.LENGTH_SHORT).show();
                        Log.e("Email", ex.toString());
                    }
                }
                break;
            default:
                break;
        }
    }
}
