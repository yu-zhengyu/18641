package com.example.zhengyu.parta.ui;

import android.app.AlertDialog;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.zhengyu.parta.R;
import com.example.zhengyu.parta.exception.UserException;
import com.example.zhengyu.parta.util.DatabaseConnector;

/**
 * Created by zhengyu on 15/11/11.
 * This class is a fragment, help user add data into database
 */


public class AddData extends Fragment {
    private TextView High_Text, Low_Text, Ave_Text;
    private Button Save_button;
    private EditText stud_edit, q1_edit, q2_edit, q3_edit, q4_edit, q5_edit;
    private int stud;
    private float[] q;

    @Override
    public void onStart() {
        super.onStart();
        stud_edit = (EditText) getActivity().findViewById(R.id.stud_edit);
        q1_edit = (EditText) getActivity().findViewById(R.id.q1_edit);
        q2_edit = (EditText) getActivity().findViewById(R.id.q2_edit);
        q3_edit = (EditText) getActivity().findViewById(R.id.q3_edit);
        q4_edit = (EditText) getActivity().findViewById(R.id.q4_edit);
        q5_edit = (EditText) getActivity().findViewById(R.id.q5_edit);
        Save_button = (Button) getActivity().findViewById(R.id.save_button);
        Save_button.setOnClickListener(saveButtonClick);
        High_Text = (TextView) getActivity().findViewById(R.id.High);
        Low_Text = (TextView) getActivity().findViewById(R.id.Low);
        Ave_Text = (TextView) getActivity().findViewById(R.id.Ave);
        High_Text.setVisibility(View.INVISIBLE);
        Low_Text.setVisibility(View.INVISIBLE);
        Ave_Text.setVisibility(View.INVISIBLE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_add_data, container, false);
    }

    View.OnClickListener saveButtonClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            try {
                // check if the input is legal
                if (is_legal()) {
                    // check if the student is exist or not
                    if (MainActivity.students.addScore(stud, q)) {

                        addIntoDataBase();

                        AlertDialog.Builder builder =
                                new AlertDialog.Builder(getActivity());
                        // set dialog title & message, and provide Button to dismiss
                        builder.setTitle("Add success");
                        builder.setMessage("You have add data successful");
                        builder.setPositiveButton("Back", null);
                        builder.show(); // display the Dialog
                    } else {
                        throw new UserException("Student Exist");
                    }
                } else {
                    throw new UserException("Input ERROR");

                }
            } catch (UserException e) {
                AlertDialog.Builder builder =
                        new AlertDialog.Builder(getActivity());
                Log.e("Error", e.getMsg());

                // set dialog title & message, and provide Button to dismiss
                builder.setTitle("Error");
                builder.setMessage(e.getMsg());
                builder.setPositiveButton("Back", null);
                builder.show(); // display the Dialog
            }
        }
    };

    // check the input is legal or not
    public boolean is_legal() {
        q = new float[5];
        if (stud_edit.getText().toString().length() != 4
                || q1_edit.getText().toString().length() == 0
                || q2_edit.getText().toString().length() == 0
                || q3_edit.getText().toString().length() == 0
                || q4_edit.getText().toString().length() == 0
                || q5_edit.getText().toString().length() == 0)
            return false;
        else {
            stud = Integer.parseInt(stud_edit.getText().toString());
            q[0] = Float.parseFloat(q1_edit.getText().toString());
            q[1] = Float.parseFloat(q2_edit.getText().toString());
            q[2] = Float.parseFloat(q3_edit.getText().toString());
            q[3] = Float.parseFloat(q4_edit.getText().toString());
            q[4] = Float.parseFloat(q5_edit.getText().toString());

            if (q[0] > 100 || q[0] < 0
                    || q[1] > 100 || q[1] < 0
                    || q[2] > 100 || q[2] < 0
                    || q[3] > 100 || q[3] < 0
                    || q[4] > 100 || q[4] < 0)
                return false;

        }
        return true;
    }

    @Override
    public void onResume() {
        super.onResume();
        stud_edit.setText("");
        q1_edit.setText("");
        q2_edit.setText("");
        q3_edit.setText("");
        q4_edit.setText("");
        q5_edit.setText("");
    }

    // add data into database
    public void addIntoDataBase() {
        DatabaseConnector database = new DatabaseConnector(getActivity());
        database.insertContact(stud, q[0], q[1], q[2], q[3], q[4]);
        Log.d("DataBase", "Insert data into Databese");
    }
}
