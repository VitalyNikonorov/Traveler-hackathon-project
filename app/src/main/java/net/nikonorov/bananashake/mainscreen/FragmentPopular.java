package net.nikonorov.bananashake.mainscreen;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.nikonorov.bananashake.App;
import net.nikonorov.bananashake.R;

/**
 * Created by vitaly on 25.03.16.
 */
public class FragmentPopular extends Fragment {

    public FragmentPopular() {
    }

    RecyclerView recyclerView;
    RVAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_popular, null);

        recyclerView = (RecyclerView) view.findViewById(R.id.rv_popular);
        adapter = new RVAdapter( ((App)getActivity().getApplication()).cities, getActivity());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);


        return view;
    }
}
