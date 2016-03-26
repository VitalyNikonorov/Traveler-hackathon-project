package net.nikonorov.bananashake.mainscreen;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatSpinner;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import net.nikonorov.bananashake.City;
import net.nikonorov.bananashake.R;
import net.nikonorov.bananashake.Values;

import java.util.Random;


/**
 * Created by vitaly on 25.03.16.
 */
public class FragmentShaker extends Fragment {
    public FragmentShaker() {
    }

    String[] data = {"Весь Мир", "Австралия", "Азия", "Америка", "Африка", "Европа"};

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_shaker, null);

        // адаптер
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, data);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        AppCompatSpinner spinner = (AppCompatSpinner) view.findViewById(R.id.world_part);
        spinner.setAdapter(adapter);
        // заголовок
        spinner.setPrompt("Title");
        // выделяем элемент
        spinner.setSelection(0);
        // устанавливаем обработчик нажатия
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                // показываем позиция нажатого элемента
                Values.worldPart = position;
                // Toast.makeText(getActivity().getBaseContext(), "Position = " + position, Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });
        return view;
    }
}
