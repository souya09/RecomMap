package jp.ac.cm0107.recommap;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import jp.ac.cm0107.recommap.databinding.ActivityRecomEditBinding;

public class RecomEditActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private LatLng currLatLng = null;
    private ActivityRecomEditBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityRecomEditBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.editMap);
        mapFragment.getMapAsync(this);
        RatingBar ratingBar = findViewById(R.id.ratingBar);
        ratingBar.setNumStars(5);
        ratingBar.setRating(2);
        ratingBar.setProgressTintList(ColorStateList.valueOf(Color.rgb(217, 194, 0)));
        Button btn = findViewById(R.id.btnAdd);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText edt = findViewById(R.id.edtName);
                Toast.makeText(getApplicationContext(),edt.getText().toString()+"を登録します",Toast.LENGTH_LONG).show();
                Toast.makeText(getApplicationContext(),currLatLng.latitude+"/"+currLatLng.longitude,Toast.LENGTH_LONG).show();

            }
        });
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        mapInit();
    }

    private void mapInit() {
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        LatLng location = new LatLng(35.690921,139.700258);
        CameraPosition cameraPos = new CameraPosition.Builder().target(location).zoom(15.5f).build();
        mMap.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPos));

        currLatLng = location;
        MarkerOptions marker = new MarkerOptions();
        marker.position(currLatLng);
        marker.draggable(true);
        marker.title("登録したい場所");
        mMap.addMarker(marker);


        mMap.setOnMarkerDragListener(new GoogleMap.OnMarkerDragListener() {
            @Override
            public void onMarkerDrag(@NonNull Marker marker) {

            }

            @Override
            public void onMarkerDragEnd(@NonNull Marker marker) {

            }

            @Override
            public void onMarkerDragStart(@NonNull Marker marker) {
                currLatLng = marker.getPosition();
            }
        });

        UiSettings ui = mMap.getUiSettings();
        ui.setCompassEnabled(true);
        ui.setRotateGesturesEnabled(true);
        ui.setScrollGesturesEnabled(true);
        ui.setTiltGesturesEnabled(true);
        ui.setZoomControlsEnabled(true);
        ui.setZoomGesturesEnabled(true);
    }
}