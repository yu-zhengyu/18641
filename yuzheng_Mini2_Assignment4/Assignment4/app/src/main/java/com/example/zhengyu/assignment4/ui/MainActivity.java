package com.example.zhengyu.assignment4.ui;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zhengyu.assignment4.R;
import com.example.zhengyu.assignment4.exceptions.UserException;

/**
 * Created by zhengyu on 15/11/4.
 * This class is main class, it would print the location in main page
 * and send massage to someone's phone
 */

public class MainActivity extends Activity implements View.OnClickListener, LocationListener {

    // init UI property
    Button sendButton;
    TextView LongTextView, LatiTextView, AltiTextView;

    // Location manager
    private LocationManager locationManager;
    private Location location;
    private double Longitude, Latitude, Altitude;

    // Sms massage manager
    private SmsManager smsManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get UI
        sendButton = (Button) findViewById(R.id.sendingButton);
        LongTextView = (TextView) findViewById(R.id.longvalue);
        LatiTextView = (TextView) findViewById(R.id.latitudevalue);
        AltiTextView = (TextView) findViewById(R.id.altitudevalue);
        sendButton.setOnClickListener(this);

        // deal with the location
        // check the location is available or not
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (locationManager.isProviderEnabled(locationManager.GPS_PROVIDER)) {
            getLocation();

        } else { // if not, mention user
            Toast.makeText(MainActivity.this, "The GPS Server is unable", Toast.LENGTH_LONG).show();
        }

        try {
            locationManager.requestLocationUpdates(locationManager.GPS_PROVIDER, 0, 0, this);
        } catch (Exception e) {
            Log.e("ERRROR", e.toString());
            Toast.makeText(MainActivity.this, "No GPS server", Toast.LENGTH_LONG).show();
        }

    }

    // update the text view, about the location
    public void getLocation() {
        try {

            location = locationManager.getLastKnownLocation(locationManager.GPS_PROVIDER);
            if (location != null) {
                Longitude = location.getLongitude();
                Latitude = location.getLatitude();
                Altitude = location.getAltitude();

                LongTextView.setText(Double.toString(Longitude));
                LatiTextView.setText(Double.toString(Latitude));
                AltiTextView.setText(Double.toString(Altitude));
            } else {
                throw new UserException("NO GPS SERVER");
            }
        } catch (UserException e) {
            Log.e("ERRROR", e.getMsg());
            Toast.makeText(MainActivity.this, e.getMsg(), Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            Log.e("ERRROR", e.toString());
            Toast.makeText(MainActivity.this, "GPS No Permission", Toast.LENGTH_LONG).show();
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

    public String getLocationString() {
        return null;
    }

    // Implement button action
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            // send massage to phone
            case R.id.sendingButton:
                smsManager = SmsManager.getDefault();
                try {
                    // if get the available location
                    if (location != null) {
                        String msg = "Longitude: " + Double.toString(Longitude) +
                                "\n" + "Latitude: " + Double.toString(Latitude) +
                                "\n" + "Altitude: " + Double.toString(Altitude);
                        smsManager.sendTextMessage("4123535957", null, msg, null, null);
                        Toast.makeText(MainActivity.this, "SMS message has been sent to 4123535957", Toast.LENGTH_LONG).show();
                    } else {
                        throw new UserException("SMS send fail");
                    }
                } catch (UserException e) {
                    Log.e("ERRROR", e.getMsg());
                    Toast.makeText(MainActivity.this, e.getMsg(), Toast.LENGTH_LONG).show();
                }
                break;
            default:
                break;
        }
    }

    // Update the last location
    @Override
    public void onLocationChanged(Location location) {
        System.out.println("ccccccccc");
        getLocation();
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}
