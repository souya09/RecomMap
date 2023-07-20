package jp.ac.cm0107.recommap;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.android.gms.maps.model.LatLng;
@Entity(tableName = "SHOPINFO")
public class ShopInfo {
    @PrimaryKey(autoGenerate = true)
    private int _id;

    private int category;
    private String name;
//    private LatLng position;
    private double lat;
    private double lng;
    private String information;
    private double star;

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LatLng getPosition() {
        return new LatLng(lat,lng);
    }

    public void setPosition(LatLng position) {
        this.lat = position.latitude;
        this.lng = position.longitude;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public double getStar() {
        return star;
    }

    public void setStar(double star) {
        this.star = star;
    }

}
