package com.example.zhengyu.partb.ui;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.zhengyu.partb.R;

/**
 * Created by zhengyu on 15/11/12.
 * this is ICON fragment
 */
public class ImageFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.layoutimage, container, false);
    }
}
