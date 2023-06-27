package jp.ac.cm0107.recommap;

import android.os.Bundle;

import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

import jp.ac.cm0107.recommap.databinding.ActivityMapsBinding;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
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
        mapInit();

        // Add a marker in Sydney and move the camera
//        LatLng sydney = new LatLng(-34, 151);
//        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }

    private void mapInit() {
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        LatLng location  = new LatLng(35.698303, 139.698116);
        CameraPosition cameraPos = new CameraPosition.Builder().target(location).zoom(15.5f).build();
        mMap.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPos));
        UiSettings ui = mMap.getUiSettings();
        ui.setCompassEnabled(true);
        ui.setRotateGesturesEnabled(true);
        ui.setScrollGesturesEnabled(true);
        ui.setTiltGesturesEnabled(true);
        ui.setZoomControlsEnabled(true);
        ui.setZoomGesturesEnabled(true);

        markerSet();
    }
    private ArrayList<ShopInfo> getShopInfoes(){
        ArrayList<ShopInfo> ary = new ArrayList<ShopInfo>();

        ShopInfo tmp1 = new ShopInfo();
        tmp1.setPosition(new LatLng(35.696346,139.698336));
        tmp1.setName("ラーメン二郎");
        tmp1.setInformation("最強");
        ary.add(tmp1);

        ShopInfo tmp2 = new ShopInfo();
        tmp2.setPosition(new LatLng(35.695339,139.696587));
        tmp2.setName("風来居");
        tmp2.setInformation("塩とんこつらーめん");
        ary.add(tmp2);

        return ary;
    }
    private void markerSet() {
        MarkerOptions options = new MarkerOptions();
        ArrayList<ShopInfo> list = getShopInfoes();
        for (ShopInfo shop: list){
            options.position(shop.getPosition());
            options.title(shop.getName());
            options.snippet(shop.getInformation());

            mMap.addMarker(options);
        }
    }
}