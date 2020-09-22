package com.example.project2.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.project2.R;
import com.example.project2.activity.RasporedActivity;
import com.example.project2.adapter.RasporedAdapter;
import com.example.project2.model.Raspored;
import com.example.project2.repository.db.entity.RasporedEntity;
import com.example.project2.repository.web.model.Resource;
import com.example.project2.viewModel.MainViewModel;

import java.util.ArrayList;
import java.util.List;

public class Fragment1 extends Fragment {

    private static final String TAG = "MainActivity";

    private MainViewModel mViewModel;
    private RasporedAdapter mAdapter;
    private EditText filter;
    Spinner spinner;
    Spinner spinner2;

    public static Fragment1 newInstance() {
        return new Fragment1();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment1, container, false);
        spinner = view.findViewById(R.id.spinnerfrag1_dan);
        spinner2 = view.findViewById(R.id.spinnerfrag1_grupa);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(view.getContext(), android.R.layout.simple_list_item_1, initList());
        spinner.setAdapter(adapter);

        RecyclerView recyclerView = view.findViewById(R.id.frag1_rv_main_list);
        GridLayoutManager layoutManager = new GridLayoutManager(view.getContext(),1);
        recyclerView.setLayoutManager(layoutManager);
        mAdapter = new RasporedAdapter();
        recyclerView.setAdapter(mAdapter);

        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(view.getContext(), android.R.layout.simple_list_item_1, initList2());
        spinner2.setAdapter(adapter2);
        Button button = view.findViewById(R.id.btn_frag1);

        Intent intent = new Intent(this.getActivity(), RasporedActivity.class);
        mAdapter.setOnItemClickedListener(new RasporedAdapter.OnItemClickedListener() {
            @Override
            public void onItemClicked(RasporedEntity rasporedEntity) {
                intent.putExtra( RasporedActivity.RASPORED_KEY,rasporedEntity.getPredmet());
                startActivity(intent);
            }
        });

        mAdapter.setOnItemClickedListener1(new RasporedAdapter.OnItemClickedListener1() {
            @Override
            public void onItemClicked1(RasporedEntity rasporedEntity) {
                intent.putExtra( RasporedActivity.RASPORED_KEY,rasporedEntity.getNastavnik());
                startActivity(intent);
            }
        });

        mAdapter.setOnItemClickedListener2(new RasporedAdapter.OnItemClickedListener2() {
            @Override
            public void onItemClicked2(RasporedEntity rasporedEntity) {
                intent.putExtra( RasporedActivity.RASPORED_KEY,rasporedEntity.getUcionica());
                startActivity(intent);
            }
        });



        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                filter = view.findViewById(R.id.frag1_et);
                String dan = spinner.getSelectedItem().toString();
                String grupa = spinner2.getSelectedItem().toString();
                String pred = filter.getText().toString();
                String prof = filter.getText().toString();
                Raspored raspored = new Raspored(pred, dan, grupa, prof);
                mViewModel.setFilter(raspored);


            }
        });

        return view;
    }

    public void onActivityCreated(@Nullable Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);

        mViewModel = ViewModelProviders.of(this).get(MainViewModel.class);


        mViewModel.getRasporeds().observe(this, new Observer<Resource<Void>>() {
            @Override
            public void onChanged(Resource<Void> resource) {

                showToast(resource.isSuccessful());

            }
        });


        mViewModel.getAllRasporeds().observe(this,
                new Observer<List<RasporedEntity>>() {
                    @Override
                    public void onChanged(List<RasporedEntity> rasporedEntities) {
                        // Observer notified when movies are inserted into db
                        // after successful fetch from the server
                        mAdapter.setData(rasporedEntities);
                    }
                });

        mViewModel.getFilteredRasporeds().observe(this,
                new Observer<List<RasporedEntity>>() {
                    @Override
                    public void onChanged(List<RasporedEntity> rasporedEntities) {
                        // Observer notified when filter changes
                        mAdapter.setData(rasporedEntities);
                    }
                });


    }


    private void showToast(boolean isSuccess) {
        String message = isSuccess ? getString(R.string.fetch_success_message) : getString(R.string.fetch_fail_message);
        Toast.makeText(Fragment1.this.getContext() ,message, Toast.LENGTH_LONG).show();
    }




    private List<String> initList() {

        List<String>  stringList = new ArrayList<>();
        String dan = "dan";
        String dan7 = "";
        String dan1 = "PON";
        String dan2 = "UTO";
        String dan3 = "SRE";
        String dan4 = "?ET";
        String dan5 = "PET";
        String dan6 = "SUB";
        stringList.add(dan);
        stringList.add(dan7);
        stringList.add(dan1);
        stringList.add(dan2);
        stringList.add(dan3);
        stringList.add(dan4);
        stringList.add(dan5);
        stringList.add(dan6);


        return  stringList;
    }

    private List<String> initList2() {

        List<String>  stringList = new ArrayList<>();

       stringList.add("grupa");
       stringList.add("");
       stringList.add("101");
       stringList.add("102");
       stringList.add("103");
       stringList.add("104");
       stringList.add("105");
       stringList.add("106");
       stringList.add("107");
       stringList.add("1s1");
       stringList.add("1s2");
       stringList.add("1d1");
       stringList.add("1d2");
       stringList.add("1d3");
       stringList.add("201");
       stringList.add("202");
       stringList.add("203");
        stringList.add("204");
        stringList.add("205");
        stringList.add("210");
        stringList.add("211");
        stringList.add("2s1");
        stringList.add("2s2");
        stringList.add("301");
        stringList.add("302");
        stringList.add("303");
        stringList.add("304");
        stringList.add("305");
        stringList.add("306");
        stringList.add("307");
        stringList.add("308");
        stringList.add("309");
        stringList.add("309a");
        stringList.add("313");
        stringList.add("314");
        stringList.add("319");
        stringList.add("322");
        stringList.add("323");
        stringList.add("324");
        stringList.add("3s1");
        stringList.add("3s2");
        stringList.add("2d1");
        stringList.add("3d1");
        stringList.add("401");
        stringList.add("402");
        stringList.add("405");



       return  stringList;
    }

}
