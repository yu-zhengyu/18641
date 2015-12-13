package com.example.zhengyu.partb.ui;

import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.zhengyu.partb.R;

/**
 * Created by zhengyu on 15/11/12.
 * This class si for Button fragment
 */
public class ContactFragment extends Fragment {
    private Button Mainpage_Button, FaceBook_Button;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.contactinfo, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        Mainpage_Button = (Button) getActivity().findViewById(R.id.mainwebpage_button);
        FaceBook_Button = (Button) getActivity().findViewById(R.id.facebook_button);
        Mainpage_Button.setOnClickListener(go_MainPageButtonClick);
        FaceBook_Button.setOnClickListener(go_FaceBookButtonClick);
    }

    // when user click it, it would jump to browser
    View.OnClickListener go_MainPageButtonClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_VIEW);
            intent.addCategory(Intent.CATEGORY_BROWSABLE);
            intent.setData(Uri.parse("http://jaychoustudio.com"));
            startActivity(intent);
        }
    };

    // when user click it, it would jump to browser
    View.OnClickListener go_FaceBookButtonClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_VIEW);
            intent.addCategory(Intent.CATEGORY_BROWSABLE);
            intent.setData(Uri.parse("https://www.facebook.com/jay?sk=wall"));
            startActivity(intent);
        }
    };
}
