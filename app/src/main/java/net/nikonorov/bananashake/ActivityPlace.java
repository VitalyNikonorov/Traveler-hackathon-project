package net.nikonorov.bananashake;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.vk.sdk.VKAccessToken;
import com.vk.sdk.VKCallback;
import com.vk.sdk.VKScope;
import com.vk.sdk.VKSdk;
import com.vk.sdk.api.VKError;
import com.vk.sdk.api.photo.VKImageParameters;
import com.vk.sdk.api.photo.VKUploadImage;
import com.vk.sdk.dialogs.VKShareDialog;
import com.vk.sdk.dialogs.VKShareDialogBuilder;

import net.nikonorov.bananashake.utils.AmazingPicture;

/**
 * Created by vitaly on 25.03.16.
 */
public class ActivityPlace extends AppCompatActivity implements OnMapReadyCallback {
    private static final String LOG_TAG = "MyActivityPlace";
    private City city = null;
    private AmazingPicture photo;
    private TextView cityName;
    private TextView countryName;
    private TextView cityRating;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_place);

        getSupportActionBar().hide();

        Button transportBtn = (Button) findViewById(R.id.transport_btn);

        transportBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ActivityPlace.this, ActivityTransport.class));
            }
        });

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        photo = (AmazingPicture) findViewById(R.id.city_image);
        cityName = (TextView) findViewById(R.id.city_name);
        countryName = (TextView) findViewById(R.id.country_name);
        cityRating = (TextView) findViewById(R.id.city_rating);


        Button vrBtn = (Button) findViewById(R.id.vr_btn);

        vrBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ActivityPlace.this, ActivityVR.class));
            }
        });
        findViewById(R.id.share).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        vkLogin();
                    }
                }
        );
    }

    private void vkLogin() {
        VKSdk.login(this, VKScope.FRIENDS,
                VKScope.WALL,
                VKScope.PHOTOS,
                VKScope.NOHTTPS,
                VKScope.MESSAGES,
                VKScope.DOCS);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (!VKSdk.onActivityResult(requestCode, resultCode, data, new VKCallback<VKAccessToken>() {
            @Override
            public void onResult(VKAccessToken res) {
                shareVK();
                // User passed Authorization
            }

            @Override
            public void onError(VKError error) {
                // User didn't pass Authorization
            }
        })) {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    private void shareVK() {

        final Bitmap bitmap = ((BitmapDrawable) photo.getDrawable()).getBitmap();
        VKShareDialogBuilder vkShareDialogBuilder = new VKShareDialogBuilder();
        vkShareDialogBuilder
                .setText(String.format("Побывал в городе %s", cityName.getText()))
                .setAttachmentImages(new VKUploadImage[]{
                        new VKUploadImage(bitmap, VKImageParameters.jpgImage(1))
                })
                .setAttachmentLink("Зацени приложение BananaShake", "http://bananashake.ru")
                .setShareDialogListener(new VKShareDialog.VKShareDialogListener() {
                    @Override
                    public void onVkShareComplete(int postId) {
                        //контент отправлен
                        Log.d(LOG_TAG, "onVkShareComplete");
                    }

                    @Override
                    public void onVkShareCancel() {
                        //отмена
                        Log.d(LOG_TAG, "onVkShareCancel");
                    }

                    @Override
                    public void onVkShareError(VKError error) {
                        Log.d(LOG_TAG, "onVkShareError");
                        Log.d(LOG_TAG, error.toString());
                    }
                }).show(getFragmentManager(), "VK_SHARE_DIALOG");
    }

    @Override
    protected void onResume() {
        super.onResume();

        city = App.cities.get(Values.city);

        Drawable drawable = getResources().getDrawable(getResources()
                .getIdentifier(city.photo, "drawable", getPackageName()));
        photo.setImageDrawable(drawable);

        cityName.setText(city.name);
        countryName.setText(city.country);
        //System.out.format("%.3f%n", pi);     // -->  "3.142"
        cityRating.setText(String.format("%.3f%n", city.getRating()));

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        LatLng latLng = new LatLng(city.latitude, city.longitude);
        googleMap.addMarker(new MarkerOptions().position(latLng).title(String.format("Путешествуем в город %s", city.name)));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));

    }
}
