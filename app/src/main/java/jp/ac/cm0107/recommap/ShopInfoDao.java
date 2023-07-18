package jp.ac.cm0107.recommap;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ShopInfoDao {
    @Query("SELECT * FROM SHOPINFO")
    List<ShopInfo> getAllShop();

    @Insert
    void insertShop(ShopInfo shopInfo);
}