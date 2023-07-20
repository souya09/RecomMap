package jp.ac.cm0107.recommap;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.room.Room;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import jp.ac.cm0107.recommap.databinding.ActivityMapsBinding;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;
    public static final String DB_NAME = "recom_map.db";
    private ShopInfoDao shopInfoDao;
    private List<ShopInfo> shopInfoList;

    private TextView txt;
    private Handler handler;
    private ArrayList<ShopInfo> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        handler = new Handler();
        AppDatabase db = Room.databaseBuilder(this,AppDatabase.class,DB_NAME).build();
        shopInfoDao = db.shopInfoDao();

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
        mMap.setInfoWindowAdapter(new CustomInfoAdapter());
    }
    private ArrayList<ShopInfo> getShopInfoes(){
        ArrayList<ShopInfo> ary = new ArrayList<ShopInfo>();

        ShopInfo tmp1 = new ShopInfo();
        tmp1.setCategory(1);
        tmp1.setPosition(new LatLng(35.696346,139.698336));
        tmp1.setName("ラーメン二郎");
        tmp1.setInformation("最強");
        tmp1.setStar(4);
        ary.add(tmp1);

        ShopInfo tmp2 = new ShopInfo();
        tmp2.setCategory(1);
        tmp2.setPosition(new LatLng(35.695339,139.696587));
        tmp2.setName("風来居");
        tmp2.setInformation("塩とんこつらーめん");
        tmp2.setStar(4.5);
        ary.add(tmp2);

        ShopInfo tmp3 = new ShopInfo();
        tmp3.setCategory(1);
        tmp3.setPosition(new LatLng(35.69564861808428, 139.6986493171861));
        tmp3.setName("麺屋武蔵");
        tmp3.setInformation("ちゃーしゅう旨い");
        tmp3.setStar(5);
        ary.add(tmp3);

        ShopInfo tmp4 = new ShopInfo();
        tmp4.setCategory(2);
        tmp4.setPosition(new LatLng(35.69973709422558, 139.6977228826832));
        tmp4.setName("ファミマ");
        tmp4.setInformation("駅前");
        ary.add(tmp4);

        ShopInfo tmp5 = new ShopInfo();
        tmp5.setCategory(2);
        tmp5.setPosition(new LatLng(35.69854046918707, 139.6969742834574));
        tmp5.setName("ファミマ");
        tmp5.setInformation("学校近く");
        ary.add(tmp5);

        ShopInfo tmp6 = new ShopInfo();
        tmp6.setCategory(2);
        tmp6.setPosition(new LatLng(35.69931385269536, 139.6974670323149));
        tmp6.setName("セブンイレブン");
        tmp6.setInformation("学校近く");
        ary.add(tmp6);

        ShopInfo tmp7 = new ShopInfo();
        tmp7.setCategory(3);
        tmp7.setPosition(new LatLng(35.69848660139962, 139.69809244432633));
        tmp7.setName("本館");
        tmp7.setInformation("Main");
        ary.add(tmp7);

        ShopInfo tmp8 = new ShopInfo();
        tmp8.setCategory(3);
        tmp8.setPosition(new LatLng(35.6989175426804, 139.69669474324013));
        tmp8.setName("７号館");
        tmp8.setInformation("CM");
        ary.add(tmp8);

        //35.69850861596886, 139.69835103011386
        ShopInfo tmp9 = new ShopInfo();
        tmp9.setCategory(3);
        tmp9.setPosition(new LatLng(35.69850861596886, 139.69835103011386));
        tmp9.setName("3号館");
        tmp9.setInformation("喫煙所");
        ary.add(tmp9);

        return ary;
    }
    private void markerSet() {
//        MarkerOptions options = new MarkerOptions();
//        ArrayList<ShopInfo> list = getShopInfoes();
//        ArrayList<ShopInfo> list = loadDB();
//        ArrayList<ShopInfo> tmpList = new ArrayList<ShopInfo>();
//        Intent intent = getIntent();
//        int selectType = intent.getIntExtra("type",-1);
//        if (selectType == -1){
//            Toast.makeText(MapsActivity.this,"タイプが不正",Toast.LENGTH_LONG).show();
//            return;
//        }
//        if (selectType == 0){
//            tmpList = list;
//        } else if (selectType ==1) {
//            for (ShopInfo shop: list){
//                if (shop.getCategory() == 1){
//                    tmpList.add(shop);
//                }
//            }
//        }  else if (selectType ==2) {
//            for (ShopInfo shop: list){
//                if (shop.getCategory() == 2){
//                    tmpList.add(shop);
//                }
//            }
//        } else if (selectType ==3) {
//            for (ShopInfo shop: list){
//                if (shop.getCategory() == 3){
//                    tmpList.add(shop);
//                }
//            }
//        }
//        for (ShopInfo shop: tmpList){
//            options.position(shop.getPosition());
//            options.title(shop.getName());
//            options.snippet(shop.getInformation());
//            BitmapDescriptor icon = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE);
//            if (shop.getCategory()==2){
//                icon = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE);
//            }else if (shop.getCategory()==3){
//                icon = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN);
//            }
//            options.icon(icon);
//
//            mMap.addMarker(options);
//        }
        loadDB();

    }

    private  void loadDB() {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(() -> {
            shopInfoList = shopInfoDao.getAllShop();
            //TODO　ここでエラー
            handler.post(() -> setMarker(shopInfoList));
        });
    }


    private void setMarker(List<ShopInfo> shopInfoList){
        MarkerOptions options = new MarkerOptions();
        ArrayList<ShopInfo> tmpList = new ArrayList<ShopInfo>();
        for(ShopInfo shopInfo: shopInfoList){
            // TODO ここでエラー
            list.add(shopInfo);
        }
        Intent intent = getIntent();
        int selectType = intent.getIntExtra("type",-1);
        if (selectType == -1){
            Toast.makeText(MapsActivity.this,"タイプが不正",Toast.LENGTH_LONG).show();
            return;
        }
        if (selectType == 0){
            tmpList = list;
        } else if (selectType ==1) {
            for (ShopInfo shop: list){
                if (shop.getCategory() == 1){
                    tmpList.add(shop);
                }
            }
        }  else if (selectType ==2) {
            for (ShopInfo shop: list){
                if (shop.getCategory() == 2){
                    tmpList.add(shop);
                }
            }
        } else if (selectType ==3) {
            for (ShopInfo shop: list){
                if (shop.getCategory() == 3){
                    tmpList.add(shop);
                }
            }
        }
        for (ShopInfo shop: tmpList){
            options.position(shop.getPosition());
            options.title(shop.getName());
            options.snippet(shop.getInformation());
            BitmapDescriptor icon = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE);
            if (shop.getCategory()==2){
                icon = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE);
            }else if (shop.getCategory()==3){
                icon = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN);
            }
            options.icon(icon);

            mMap.addMarker(options);
        }
    }

    private class CustomInfoAdapter implements GoogleMap.InfoWindowAdapter{
        private final View mWindow;
        public CustomInfoAdapter(){
            mWindow = getLayoutInflater().inflate(R.layout.my_indo_window,null);
        }
        @Override
        public View getInfoContents(@NonNull Marker marker) {
            render(marker,mWindow);
            return mWindow;
        }

        @Override
        public View getInfoWindow(@NonNull Marker marker) {
            return null;
        }
        private void render(Marker marker,View view){
            TextView title = view.findViewById(R.id.title);
            TextView snippet = view.findViewById(R.id.snippet);
            RatingBar ratingBar = view.findViewById(R.id.info_rating_bar);
            title.setText(marker.getTitle());
            snippet.setText(marker.getSnippet());

        }
    }
}