package com.example.appium_sample.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.example.appium_sample.R;
import com.example.appium_sample.adapter.FruitAdapter;
import com.example.appium_sample.model.FruitModel;
import com.example.appium_sample.utils.DataProvider;

import java.util.List;

public class SelectFruitAct extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_fruit);
        setTitle(getString(R.string.lbl_select_fruit));

        RecyclerView rvFruit = findViewById(R.id.rvFruit);
        rvFruit.setLayoutManager(new GridLayoutManager(this, 2));

        List<FruitModel> list = DataProvider.getData();

        FruitAdapter mAdapter = new FruitAdapter();
        mAdapter.setItems(list);
        rvFruit.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new FruitAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(FruitModel fruit) {
                Intent intent = new Intent(SelectFruitAct.this, FruitDetailAct.class);
                intent.putExtra("_Fruit", fruit);
                startActivity(intent);
            }

            @Override
            public void onItemLongClick(FruitModel fruit) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.save_as_recent_fruit, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_open_cart:
                startActivity(new Intent(SelectFruitAct.this, FruitCartAct.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
