package net.nikonorov.bananashake;

/**
 * Created by vitaly on 25.03.16.
 */
public class City {

    public int id;
    public String name;
    public String photo;
    public String country;
    public String vr;
    public int rateCount;
    public int rateSum;

    public City(int id, String country, String name, String photo, String vr, int rateCount, int rateSum) {
        this.id = id;
        this.country = country;
        this.name = name;
        this.photo = photo;
        this.vr = vr;
        this.rateCount = rateCount;
        this.rateSum = rateSum;
    }

    public Float getRating(){
        return (float) rateSum / rateCount;
    }
}
