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
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.appium_sample.R;
import com.example.appium_sample.model.FruitModel;
import com.example.appium_sample.utils.DataIntent;

import java.util.List;
import java.util.Locale;

public class FruitDetailAct extends AppCompatActivity {

    private TextView tvFruitPrice;
    private CoordinatorLayout rootLayout;
    private ImageView ivFruit;
    private TextView tvFruitDescription;
    private FloatingActionButton fabAddToBucket;
    private FruitModel fruit;
    private TextView tvCartItemCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.act_fruit_detail);
        initView();

        fruit = getIntent().getParcelableExtra("_Fruit");
        setTitle(fruit.getFruit());
        ivFruit.setImageResource(fruit.getPhotoId());
        tvFruitDescription.setText(fruit.getDescription());
        tvFruitPrice.setText(String.format(Locale.ENGLISH, "Price of 12 Pic - $%.2f", fruit.getAmount()));

        fabAddToBucket.setOnClickListener(view -> addToCartDialog());

        List<FruitModel> models = DataIntent.getInstance().getCartList();
        for (FruitModel m : models) {
            if (fruit.getFruit().equals(m.getFruit())) {
                fabAddToBucket.hide();
                break;
            }
        }
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
                    setupBadge();
                    fabAddToBucket.hide();
                    Snackbar.make(rootLayout, "Fruits added in the cart", Snackbar.LENGTH_SHORT).show();
                })
                .setNegativeButton(android.R.string.cancel, (dialogInterface, i) -> dialogInterface.cancel())
                .show();
    }
}
