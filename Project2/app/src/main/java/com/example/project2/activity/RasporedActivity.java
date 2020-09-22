package com.example.project2.activity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.project2.R;
import com.example.project2.adapter.RasporedAdapter;
import com.example.project2.model.Raspored;
import com.example.project2.repository.db.entity.RasporedEntity;
import com.example.project2.viewModel.MainViewModel;

import java.util.List;

public class RasporedActivity extends AppCompatActivity {



    public static final String RASPORED_KEY = "rasporedKey";
    private MainViewModel mainViewModel;
    private RasporedAdapter mAdapter;
    private String mRasporedId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.raspored_activity);

        init();
    }

    private void init() {

        parseIntent();

        mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        mAdapter = new RasporedAdapter();
        RecyclerView recyclerView = findViewById(R.id.rv_2);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(mAdapter);

        mainViewModel.getFilteredRasporeds1().observe(this,
                new Observer<List<RasporedEntity>>() {
                    @Override
                    public void onChanged(List<RasporedEntity> rasporedEntities) {
                        // Observer notified when filter changes
                        mAdapter.setData(rasporedEntities);
                    }
                });

        mainViewModel.setFilter1(mRasporedId);

    }

    private void parseIntent() {
        Intent intent = getIntent();
        if (intent != null) {
            mRasporedId = intent.getStringExtra(RASPORED_KEY);
            Toast.makeText(this, mRasporedId, Toast.LENGTH_LONG).show();
        }
    }



}
