package net.nikonorov.bananashake;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by vitaly on 25.03.16.
 */
public class FragmentPopular extends Fragment {

    public FragmentPopular() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_popular, null);

        return view;
    }
}
