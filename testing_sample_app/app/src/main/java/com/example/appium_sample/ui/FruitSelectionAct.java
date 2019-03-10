package com.example.appium_sample.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatTextView;

import com.example.appium_sample.R;

public class FruitSelectionAct extends AppCompatActivity {

    private AppCompatTextView tvFruit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_fruit_selection);
        setTitle(getString(R.string.lbl_fruit));
        initView();

        String fruit = getIntent().getStringExtra("_Fruit");
        tvFruit.setText(fruit);
    }

    private void initView() {
        tvFruit = findViewById(R.id.tvFruit);
    }
}
