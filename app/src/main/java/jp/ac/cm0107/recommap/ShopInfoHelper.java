package jp.ac.cm0107.recommap;


import android.content.Context;
import android.util.Log;

import androidx.room.Room;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ShopInfoHelper {
    public static final String DB_NAME = "recom_map.db";
    private ShopInfoDao shopInfoDao;
    private List<ShopInfo>  shopInfoList;
    public ShopInfoHelper(Context context) {

        AppDatabase db  = Room.databaseBuilder(
                context,
                AppDatabase.class,
                DB_NAME
        ).build();
        shopInfoDao = db.shopInfoDao();
    }
    public void getAllShop(){
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(new Runnable() {
            @Override
            public void run() {
                shopInfoList = shopInfoDao.getAllShop();


            }
        });
    }

    public List<ShopInfo> getShopInfoList() {
        return shopInfoList;
    }

    public void insert(ShopInfo shopInfo){
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(new Runnable() {
            @Override
            public void run() {
                shopInfoDao.insertShop(shopInfo);

                Log.i("ShopInfoHelper get", shopInfo.getName());
            }
        });
    }
}