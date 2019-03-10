package com.example.appium_sample.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;

import com.example.appium_sample.R;
import com.example.appium_sample.utils.Utils;

public class LoginAct extends AppCompatActivity {

    private TextInputEditText edEmail;
    private TextInputLayout edEmailLayout;
    private TextInputEditText edPwd;
    private TextInputLayout edPwdLayout;
    private AppCompatButton btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_login);
        setTitle(getString(R.string.lbl_login));
        initView();

        btnLogin.setOnClickListener(view -> {

            String email = edEmail.getText() != null ? edEmail.getText().toString() : null;
            if (email != null && !email.isEmpty()) {
                //noinspection StatementWithEmptyBody
                if (Utils.isValidateEmail(email)) {
                    // save into model
                } else {
                    edEmailLayout.setError("Please Enter Valid Email");
                    return;
                }
            } else {
                edEmailLayout.setError("Please Enter Email");
                return;
            }

            String pwd = edPwd.getText() != null ? edPwd.getText().toString() : null;
            //noinspection StatementWithEmptyBody
            if (pwd != null && !pwd.isEmpty()) {
                // save to model
            } else {
                edPwdLayout.setError("Please Enter Password");
                return;
            }

            startActivity(new Intent(LoginAct.this, DashboardAct.class));
            finish();
        });
    }

    private void initView() {
        edEmail = findViewById(R.id.edEmail);
        edEmailLayout = findViewById(R.id.edEmailLayout);
        edPwd = findViewById(R.id.edPwd);
        edPwdLayout = findViewById(R.id.edPwdLayout);
        btnLogin = findViewById(R.id.btnLogin);
    }
}
