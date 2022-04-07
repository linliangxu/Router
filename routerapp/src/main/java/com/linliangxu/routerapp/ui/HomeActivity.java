package com.linliangxu.routerapp.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.chenenyu.router.annotation.Route;
import com.linliangxu.routerapp.R;

@Route(value = "home")
public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }
}