package net.nikonorov.bananashake.mainscreen;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v13.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import net.nikonorov.bananashake.ActivityPlace;
import net.nikonorov.bananashake.App;
import net.nikonorov.bananashake.FragmentSet;
import net.nikonorov.bananashake.R;
import net.nikonorov.bananashake.Values;

/**
 * Created by vitaly on 25.03.16.
 */
public class ActivityMain extends AppCompatActivity implements SensorEventListener {

    private ViewPager viewPager;
    private final int PAGE_COUNT = 2;
    private static final int SHAKE_THRESHOLD = 5000;

    private static final int X = 0;
    private static final int Y = 1;
    private static final int Z = 2;

    private float[] currentValues = new float[3];
    private float[] lastValues = new float[3];

    private Fragment[] fragments = new Fragment[PAGE_COUNT];
    private String[] titles = new String[PAGE_COUNT];
    private PagerAdapter pagerAdapter;
    private SensorManager sensorMgr;
    private long lastUpdate = 0;
    private boolean isChosen = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        sensorMgr = (SensorManager) getSystemService(SENSOR_SERVICE);

        sensorMgr.registerListener(this,
                sensorMgr.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_FASTEST);



        fragments[FragmentSet.SHAKER] = new FragmentShaker();
        fragments[FragmentSet.POPULAR] = new FragmentPopular();

        titles[FragmentSet.SHAKER] = "Мне повезет";
        titles[FragmentSet.POPULAR] = "Популярное";

        viewPager = (ViewPager) findViewById(R.id.pager);
        pagerAdapter = new MyFragmentPagerAdapter(getFragmentManager());
        viewPager.setAdapter(pagerAdapter);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                //Log.i("TAG", "Scrolled");
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {
                View view = ActivityMain.this.getCurrentFocus();
                InputMethodManager imm = (InputMethodManager) ActivityMain.this.getSystemService(Context.INPUT_METHOD_SERVICE);
                if (view != null) {
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                }
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        isChosen = false;
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER && viewPager.getCurrentItem() == FragmentSet.SHAKER) {
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
                    if(!isChosen) {
                        isChosen = true;

                        if (Values.worldPart != 0) {
                            Values.city = 4 * (Values.worldPart -1) + (int) speed % 4;
                        }else {
                            Values.city = (int) speed % App.cities.size(); //Math.abs(new Random(System.currentTimeMillis()).nextInt()) % 20 ;
                        }
                        Log.d("sensor", "shake detected w/ speed: " + speed);
                        //Toast.makeText(this, "shake detected, speed: " + speed, Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(ActivityMain.this, ActivityPlace.class));
                    }
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


    private class MyFragmentPagerAdapter extends FragmentPagerAdapter {

        public MyFragmentPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragments[position];
        }

        @Override
        public int getCount() {
            return PAGE_COUNT;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }
    }
}
