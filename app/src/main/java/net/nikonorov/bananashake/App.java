package net.nikonorov.bananashake;

import android.app.Application;

import com.vk.sdk.VKSdk;
import com.vk.sdk.util.VKUtil;

import java.util.ArrayList;

/**
 * Created by vitaly on 25.03.16.
 */
public class App extends Application {

    public static ArrayList<City> cities = new ArrayList<>();

    @Override
    public void onCreate() {
        super.onCreate();

        cities.add(new City(0, "Australia", "Sidney", "sidney", "saber/saber.g3db", 1, 5));
        cities.add(new City(1, "Australia", "Canberra", "canberra", "saber/saber.g3db", 2, 9));

        cities.add(new City(2, "New Zealand", "Wellington", "wellington", "saber/saber.g3db", 3, 13));
        cities.add(new City(3, "New Zealand", "Auckland", "auckland", "saber/saber.g3db", 2, 10));

        cities.add(new City(4, "China", "Beijing", "beijing", "saber/saber.g3db", 1, 4));
        cities.add(new City(5, "China", "Shanghai", "shanghai", "saber/saber.g3db", 2, 8));

        cities.add(new City(6, "Japan", "Tokyo", "tokyo", "saber/saber.g3db", 2, 10));
        cities.add(new City(7, "Japan", "Yokohama", "yokohama", "saber/saber.g3db", 3, 14));

        cities.add(new City(8, "USA", "Los Angeles", "la", "saber/saber.g3db", 4, 20));
        cities.add(new City(9, "USA", "New York", "nyc", "saber/saber.g3db", 3, 14));

        cities.add(new City(10, "Canada", "Brantford", "brantford", "saber/saber.g3db", 1, 5));
        cities.add(new City(11, "Canada", "Collingwood", "collingwood", "saber/saber.g3db", 3, 15));
            
        cities.add(new City(12, "Egypt", "Cairo", "cairo", "saber/saber.g3db", 2, 10));
        cities.add(new City(13, "Egypt", "Hurghada", "hurghada", "saber/saber.g3db", 2, 9));

        cities.add(new City(14, "Marocco", "Rabat", "rabat", "saber/saber.g3db", 3, 13));
        cities.add(new City(15, "Marocco", "Casablanca", "casablanca", "saber/saber.g3db", 2, 7));

        cities.add(new City(16, "Russia", "Moscow", "moscow", "saber/saber.g3db", 10, 50));
        cities.add(new City(17, "Russia", "Saint Petersburg", "piter", "saber/saber.g3db", 10, 50));

        cities.add(new City(18, "France", "Paris", "paris", "saber/saber.g3db", 3, 9));
        cities.add(new City(19, "France", "Courcheval", "courcheval", "saber/saber.g3db", 4, 19));

        VKSdk.initialize(getApplicationContext());

    }
}
