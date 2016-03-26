package net.nikonorov.bananashake;

import android.app.Fragment;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatSpinner;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by vitaly on 26.03.16.
 */
public class ActivityTransport extends AppCompatActivity implements SensorEventListener {
    private static final String planeUrl = "https://www.onetwotrip.com/ru/";
    private static final String trainUrl = "https://www.onetwotrip.com/ru/railways/";
    String[] data = {"plane", "train"};

    private static final int SHAKE_THRESHOLD = 5000;

    private static final int X = 0;
    private static final int Y = 1;
    private static final int Z = 2;

    private float[] currentValues = new float[3];
    private float[] lastValues = new float[3];

    private SensorManager sensorMgr;
    private long lastUpdate = 0;

    AppCompatSpinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transport);

        sensorMgr = (SensorManager) getSystemService(SENSOR_SERVICE);

        sensorMgr.registerListener(this,
                sensorMgr.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_FASTEST);


        // адаптер
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(ActivityTransport.this, android.R.layout.simple_spinner_item, data);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner = (AppCompatSpinner) findViewById(R.id.transport_choise);
        findViewById(R.id.ok_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String chosenItem = (String)spinner.getSelectedItem();
                String url;
                switch (chosenItem) {
                    case "plane":
                        url = planeUrl;
                        break;
                    default:
                        url = trainUrl;
                        break;
                }
                Uri uriUrl = Uri.parse(url);
                Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
                startActivity(launchBrowser);
            }
        });
        spinner.setAdapter(adapter);
        // заголовок
        spinner.setPrompt("Title");
        // выделяем элемент
        spinner.setSelection(0);
        // устанавливаем обработчик нажатия
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                // показываем позиция нажатого элемента
                Values.worldPart = position;
                Toast.makeText(getBaseContext(), "Position = " + position, Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });



    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            long curTime = System.currentTimeMillis();
            // only allow one update every 100ms.
            if ((curTime - lastUpdate) > 100) {
                long diffTime = (curTime - lastUpdate);
                lastUpdate = curTime;

                currentValues[X] = event.values[X];
                currentValues[Y] = event.values[Y];
                currentValues[Z] = event.values[Z];

                float speed = Math.abs(currentValues[X]
                        + currentValues[Y]
                        + currentValues[Z]
                        - lastValues[X]
                        - lastValues[Y]
                        - lastValues[Z]) / diffTime * 10000;

                //String[] data = {"All world", "Australia", "Asia", "Americas", "Africa", "Europe"};

                if (speed > SHAKE_THRESHOLD) {

                    if (Values.worldPart != 0) {
                        Values.city = 4 * (Values.worldPart -1) + (int) speed % 4;
                    }else {
                        Values.city = (int) speed % 20; //Math.abs(new Random(System.currentTimeMillis()).nextInt()) % 20 ;
                    }
                    Log.d("sensor", "shake detected w/ speed: " + speed);
                    Toast.makeText(this, "shake detected, speed: " + speed, Toast.LENGTH_SHORT).show();
                    //startActivity(new Intent(ActivityTransport.this, ActivityPlace.class));
                    spinner.setSelection((int) (speed%3));
                }
                lastValues[X] = currentValues[X];
                lastValues[Y] = currentValues[Y];
                lastValues[Z] = currentValues[Z];
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
