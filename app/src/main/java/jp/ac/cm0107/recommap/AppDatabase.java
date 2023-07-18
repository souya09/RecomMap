package jp.ac.cm0107.recommap;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {ShopInfo.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract ShopInfoDao shopInfoDao();

}