package com.example.appium_sample.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.appium_sample.R;
import com.example.appium_sample.adapter.FruitAdapter;

import java.util.ArrayList;
import java.util.List;

public class DashboardAct extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        setTitle(getString(R.string.lbl_dashboard));

        RecyclerView rvFruit = findViewById(R.id.rvFruit);
        rvFruit.setLayoutManager(new LinearLayoutManager(this));
        rvFruit.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        List<String> list = new ArrayList<>();
        list.add("Grapes");
        list.add("Lime");
        list.add("Lemon");
        list.add("Cherry");
        list.add("Blueberry");
        list.add("Banana");
        list.add("Apple");
        list.add("Watermelon");
        list.add("Peach");
        list.add("Pineapple");
        list.add("Strawberry");
        list.add("Orange");

        FruitAdapter mAdapter = new FruitAdapter(list);
        rvFruit.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(fruit -> {
            Intent intent = new Intent(DashboardAct.this, FruitSelectionAct.class);
            intent.putExtra("_Fruit", fruit);
            startActivity(intent);
        });
    }
}
