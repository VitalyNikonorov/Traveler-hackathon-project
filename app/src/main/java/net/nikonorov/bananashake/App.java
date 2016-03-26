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

        cities.add(new City(0, "Австралия", "Сидней", "sidney", "sidney", -33.7945148, 150.3619664, 1, 5));
        cities.add(new City(1, "Австралия", "Канберра", "canberra", "canberra",-35.306946, 149.195,  2, 9));

        cities.add(new City(2, "Новая Зеландия", "Веллингтон", "wellington", "wellington", -41.244027, 174.6217699, 3, 13));
        cities.add(new City(3, "Новая Зеландия", "Окленд", "auckland", "auckland", -37.008057, 174.79167, 2, 10));

        cities.add(new City(4, "Китай", "Пекин", "beijing", "beijing", 40.080112, 116.58456, 1, 4));
        cities.add(new City(5, "Китай", "Шанхай", "shanghai", "shanghai", 31.197874, 121.33632, 2, 8));

        cities.add(new City(6, "Япония", "Токио", "tokyo", "tokyo", 35.76472, 140.38638, 2, 10));
        cities.add(new City(7, "Япония", "Йокогама", "yokohama", "yokohama", 35.5075, 139.6175, 3, 14));

        cities.add(new City(8, "США", "Лос-Анджелес", "la", "la", -37.40173, -72.425446,  4, 20));
        cities.add(new City(9, "США", "Нью-Йорк", "nyc", "nyc", 40.777245, -73.872604, 3, 14));

        cities.add(new City(10, "Канада", "Брантфорд", "brantford", "brantford", 43.13139, -80.3425, 1, 5));
        cities.add(new City(11, "Канада", "Колингвуд ", "collingwood", "collingwood", 44.449165, -80.15833,  3, 15));

        cities.add(new City(12, "Египет", "Каир", "cairo", "caire", 30.121944, 31.405556, 2, 10));
        cities.add(new City(13, "Египет", "Hurghada", "hurghada", "hurghada", 27.178316, 33.799435, 2, 9));  ////////

        cities.add(new City(14, "Марокко", "Рабат", "rabat", "rabat", 34.051468, -6.751519, 3, 13));
        cities.add(new City(15, "Марокко", "Касабланка", "casablanca", "casablanca", 33.367466, -7.589967, 2, 7));

        cities.add(new City(16, "Россия", "Москва", "moscow", "moscow", 55.97264, 37.41459, 10, 50));
        cities.add(new City(17, "Россия", "Санкт-Петербург", "piter", "piter", 59.800293, 30.262503, 10, 50));

        cities.add(new City(18, "Франция", "Париж", "paris", "paris", 48.969444, 2.441389, 3, 9));
        cities.add(new City(19, "Франция", "Куршевель", "courcheval", "courcheval", 45.3967, 6.63472, 4, 19));

        VKSdk.initialize(getApplicationContext());

        VKUtil.getCertificateFingerprint(this, this.getPackageName());

    }
}
