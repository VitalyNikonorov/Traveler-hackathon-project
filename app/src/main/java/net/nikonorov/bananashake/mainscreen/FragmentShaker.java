package net.nikonorov.bananashake.mainscreen;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.nikonorov.bananashake.R;

/**
 * Created by vitaly on 25.03.16.
 */
public class FragmentShaker extends Fragment {
    public FragmentShaker() {
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_shaker, null);

        return view;
    }
}
