package net.nikonorov.bananashake;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by vitaly on 25.03.16.
 */
public class ActivityPlace extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_place);

        Button vrBtn = (Button) findViewById(R.id.vr_btn);

        vrBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ActivityPlace.this, ActivityVR.class));
            }
        });

    }
}
