package com.example.appium_sample.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.appium_sample.R;
import com.example.appium_sample.adapter.FruitAdapter;
import com.example.appium_sample.model.FruitModel;
import com.example.appium_sample.utils.DataIntent;
import com.example.appium_sample.utils.DataProvider;

import java.util.List;
import java.util.Locale;

public class SelectFruitAct extends AppCompatActivity {

    private TextView tvCartItemCount;

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

        MenuItem menuItem = menu.findItem(R.id.action_open_cart);
        View actionView = menuItem.getActionView();
        tvCartItemCount = actionView.findViewById(R.id.cart_badge);
        setupBadge();
        actionView.setOnClickListener(v -> onOptionsItemSelected(menuItem));

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onResume() {
        super.onResume();
        setupBadge();
    }

    private void setupBadge() {
        int mCartItemCount = DataIntent.getInstance().getCartList().size();
        if (tvCartItemCount != null) {
            if (mCartItemCount == 0) {
                if (tvCartItemCount.getVisibility() != View.GONE) {
                    tvCartItemCount.setVisibility(View.GONE);
                }
            } else {
                tvCartItemCount.setText(String.format(Locale.ENGLISH, "%d", mCartItemCount));
                if (tvCartItemCount.getVisibility() != View.VISIBLE) {
                    tvCartItemCount.setVisibility(View.VISIBLE);
                }
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_open_cart) {
            startActivity(new Intent(SelectFruitAct.this, FruitCartAct.class));
        }
        return super.onOptionsItemSelected(item);
    }
}
