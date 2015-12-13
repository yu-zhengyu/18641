package com.example.zhengyu.parta.ui;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.database.Cursor;
import android.opengl.Visibility;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.zhengyu.parta.R;
import com.example.zhengyu.parta.model.Statistics;
import com.example.zhengyu.parta.util.DatabaseConnector;

import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by zhengyu on 15/11/10.
 * This is the main page, contain all Button and Fragment.
 */

public class MainActivity extends Activity implements OnClickListener{
    // ALL UI controler
    private DatabaseConnector database;
    private AddData addfragment;
    private PlaceholderFragment viewfragment;
    private Button Addbutton, ViewButton;
    public static Statistics students;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }

        // init all UI
        Addbutton = (Button)findViewById(R.id.add_button);
        ViewButton = (Button)findViewById(R.id.view_button);

        // set Listener
        Addbutton.setOnClickListener(this);
        ViewButton.setOnClickListener(this);
        database = new DatabaseConnector(this);
        students = new Statistics();
        selectDataBase();
        int i = 999;

    }


    /**
     * Help user get data from database, put it into arraylist
     */
    public void selectDataBase() {

        database.open();
        Cursor cur = database.getAllInformation();
        if (cur.moveToFirst()) {
            do {
                int id = cur.getInt(0);
                float[] qize = new float[5];
                for (int i = 1; i < 6; i++) {
                    qize[i - 1] = cur.getFloat(i);
                }
              students.addScore(id, qize);
            } while(cur.moveToNext());
        }
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
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        private List_Adapter listadapter;
        private ListView List_Score;
        private TextView High_Text, Low_Text, Ave_Text;

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }

        @Override
        public void onStart() {
            super.onStart();
            List_Score = (ListView)getActivity().findViewById(R.id.list_quiz);
            listadapter = new List_Adapter(getActivity(), students);
            List_Score.setAdapter(listadapter);
            High_Text = (TextView)getActivity().findViewById(R.id.High);
            Low_Text = (TextView)getActivity().findViewById(R.id.Low);
            Ave_Text = (TextView)getActivity().findViewById(R.id.Ave);
        }

        @Override
        public void onResume() {
            super.onResume();
            Log.e("DATA", "DATAresume");
            List_Score = (ListView)getActivity().findViewById(R.id.list_quiz);
            List_Score.setAdapter(new List_Adapter(getActivity(), students) );
            setStatics();
        }

        /**
         * this function is print the statics
         */
        public void setStatics() {
            String high, low, ave;
            high = "High Score\t\t";
            low = "Low Score\t\t";
            ave = "Average\t\t\t";
            float temphigh[] = students.findHigh();
            for(int i = 0; i < 5; i++) {
                high = high + Float.toString(temphigh[i]) + "\t\t";
            }
            float templow[] = students.findLow();
            float tempave[] = students.findAve();

            for(int i = 0; i < 5; i++) {
                low = low + Float.toString(templow[i]) + "\t\t";
                ave = ave + String.format("%.1f", tempave[i]) + "\t\t";
            }

            High_Text.setText(high);
            Low_Text.setText(low);
            Ave_Text.setText(ave);
            High_Text.setVisibility(View.VISIBLE);
            Low_Text.setVisibility(View.VISIBLE);
            Ave_Text.setVisibility(View.VISIBLE);
        }


    }


    /**
     * This functions is help user jump to other fragment
     * @param v
     */
    @Override
    public void onClick(View v)
    {
        FragmentManager fm = getFragmentManager();

        FragmentTransaction transaction = fm.beginTransaction();

        switch (v.getId())
        {
            case R.id.add_button:
                if (addfragment == null)
                {
                    addfragment = new AddData();
                }

                transaction.replace(R.id.container, addfragment);
                break;
            case R.id.view_button:
                if (viewfragment == null)
                {
                    viewfragment = new PlaceholderFragment();
                }
                transaction.replace(R.id.container, viewfragment);
                break;
        }
        // transaction.addToBackStack();
        transaction.commit();
    }

}
