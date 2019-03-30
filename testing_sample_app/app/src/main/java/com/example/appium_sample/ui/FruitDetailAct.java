package com.example.appium_sample.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.appium_sample.R;
import com.example.appium_sample.model.FruitModel;
import com.example.appium_sample.utils.DataIntent;

public class FruitDetailAct extends AppCompatActivity {

    private TextView tvFruitPrice;
    private CoordinatorLayout rootLayout;
    private ImageView ivFruit;
    private TextView tvFruitDescription;
    private FloatingActionButton fabAddToBucket;
    private FruitModel fruit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.act_fruit_detail);
        initView();

        fruit = getIntent().getParcelableExtra("_Fruit");
        setTitle(fruit.getFruit());
        ivFruit.setImageResource(fruit.getPhotoId());
        tvFruitDescription.setText(fruit.getDescription());
        tvFruitPrice.setText(String.valueOf("Price of 12 Pic - $" + fruit.getAmount()));

        fabAddToBucket.setOnClickListener(view -> addToCartDialog());
    }

    private void initView() {
        ivFruit = findViewById(R.id.ivFruit);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        tvFruitDescription = findViewById(R.id.tvFruitDescription);
        fabAddToBucket = findViewById(R.id.fabAddToBucket);
        rootLayout = findViewById(R.id.rootLayout);
        tvFruitPrice = findViewById(R.id.tvFruitPrice);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.save_as_recent_fruit, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            case R.id.action_open_cart:
                startActivity(new Intent(FruitDetailAct.this, FruitCartAct.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void addToCartDialog() {
        new AlertDialog.Builder(FruitDetailAct.this)
                .setCancelable(false)
                .setTitle("Add to Cart")
                .setMessage("This fruit do you want to add in cart?")
                .setPositiveButton(android.R.string.ok, (dialogInterface, i) -> {
                    DataIntent.getInstance().addItemInCart(fruit);
                    dialogInterface.cancel();
                    Snackbar.make(rootLayout, "Fruits added in the cart", Snackbar.LENGTH_SHORT).show();
                })
                .setNegativeButton(android.R.string.cancel, (dialogInterface, i) -> {
                    dialogInterface.cancel();
                })
                .show();
    }
}
