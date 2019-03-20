package com.example.appium_sample.ui;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import com.example.appium_sample.R;
import com.example.appium_sample.adapter.FruitAdapter;
import com.example.appium_sample.model.FruitModel;
import com.example.appium_sample.utils.DataIntent;

public class FruitCartAct extends AppCompatActivity {

    private RecyclerView rvFruit;
    private FruitAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.act_fruit_cart);
        initView();

        mAdapter = new FruitAdapter(DataIntent.getInstance().getCartList());
        rvFruit.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new FruitAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(FruitModel fruit) {

            }

            @Override
            public void onItemLongClick(FruitModel fruit) {
                deleteConformationDialog(fruit);
            }
        });
    }

    private void initView() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        rvFruit = findViewById(R.id.rvFruit);
        rvFruit.setLayoutManager(new GridLayoutManager(this, 2));
        setTitle(getString(R.string.lbl_recent_fruit));
    }

    private void deleteConformationDialog(FruitModel fruit) {
        new AlertDialog.Builder(FruitCartAct.this)
                .setCancelable(false)
                .setTitle("Delete")
                .setMessage("Do you want to remove from the cart?")
                .setPositiveButton(android.R.string.ok, (dialogInterface, i) -> {
                    dialogInterface.cancel();
                    DataIntent.getInstance().removeItemCart(fruit);
                    mAdapter.setItems(DataIntent.getInstance().getCartList());
                })
                .setNegativeButton(android.R.string.cancel, (dialogInterface, i) -> {
                    dialogInterface.cancel();
                })
                .show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
