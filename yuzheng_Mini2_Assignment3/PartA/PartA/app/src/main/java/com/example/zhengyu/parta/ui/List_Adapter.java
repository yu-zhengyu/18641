package com.example.zhengyu.parta.ui;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.zhengyu.parta.R;
import com.example.zhengyu.parta.model.Statistics;

/**
 * Created by zhengyu on 15/11/11.
 * This class can help MainActivity show the list of score.
 */
public class List_Adapter extends BaseAdapter {
    private Context context;
    private Statistics s;
    private LinearLayout linearlayout;

    public List_Adapter(Context context, Statistics s){
        this.s = s;
        this.context=context;
    }

    @Override
    public int getCount() {
        return MainActivity.students.getAllInfo().size();
    }

    @Override
    public Object getItem(int position) {
        return MainActivity.students.getAllInfo().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        linearlayout=(LinearLayout)inflater.inflate(R.layout.list_layout, null);

        TextView Stud = (TextView)linearlayout.findViewById(R.id.list_stud);
        TextView q1 = (TextView)linearlayout.findViewById(R.id.list_q1);
        TextView q2 = (TextView)linearlayout.findViewById(R.id.list_q2);
        TextView q3 = (TextView)linearlayout.findViewById(R.id.list_q3);
        TextView q4 = (TextView)linearlayout.findViewById(R.id.list_q4);
        TextView q5 = (TextView)linearlayout.findViewById(R.id.list_q5);

        int id = s.getAllInfo().get(position).getStud();
        String id_string = Integer.toString(id);
        while(id_string.length() < 4){
            id_string = "0" + id_string;
        }
        Stud.setText(id_string);
        q1.setText(Float.toString(s.getAllInfo().get(position).getQ()[0]));
        q2.setText(Float.toString(s.getAllInfo().get(position).getQ()[1]));
        q3.setText(Float.toString(s.getAllInfo().get(position).getQ()[2]));
        q4.setText(Float.toString(s.getAllInfo().get(position).getQ()[3]));
        q5.setText(Float.toString(s.getAllInfo().get(position).getQ()[4]));


        return linearlayout;
    }
}
