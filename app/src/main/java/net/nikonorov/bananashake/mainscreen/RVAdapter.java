package net.nikonorov.bananashake.mainscreen;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import net.nikonorov.bananashake.ActivityPlace;
import net.nikonorov.bananashake.City;
import net.nikonorov.bananashake.R;
import net.nikonorov.bananashake.utils.AmazingPicture;

import java.util.ArrayList;

/**
 * Created by vitaly on 25.03.16.
 */
public class RVAdapter extends RecyclerView.Adapter<RVAdapter.ItemViewHolder> {

    ArrayList<City> data = null;
    Activity activity = null;

    public RVAdapter(ArrayList<City> data, Activity activity) {
        this.data = data;
        this.activity = activity;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_item, parent, false);
        ItemViewHolder holder = new ItemViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        City city = data.get(position);
        Drawable drawable = activity.getResources().getDrawable(activity.getResources()
                .getIdentifier(city.photo, "drawable", activity.getPackageName()));
        holder.photo.setImageDrawable(drawable);

        holder.cityName.setText(city.name);
        holder.countryName.setText(city.country);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder {

        AmazingPicture photo;
        TextView cityName;
        TextView countryName;

        ItemViewHolder(View itemView){
            super(itemView);

            photo = (AmazingPicture) itemView.findViewById(R.id.item_city_image);
            cityName = (TextView) itemView.findViewById(R.id.item_city_name);
            countryName = (TextView) itemView.findViewById(R.id.item_country_name);

        }

    }
}
