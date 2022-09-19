package com.linliangxu.routerapp.ui;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.chenenyu.router.Router;
import com.chenenyu.router.annotation.Route;
import com.linliangxu.routerapp.R;

@Route(value = "interceptor")
public class InterceptorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interceptor);
    }




    public void newTask(View view) {
        Router.build("home").newTask().go(this);
    }
}