package com.linliangxu.routerapp.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.chenenyu.router.RouteCallback;
import com.chenenyu.router.RouteStatus;
import com.chenenyu.router.Router;
import com.chenenyu.router.annotation.Route;
import com.linliangxu.routerapp.R;

@Route(value = "home", interceptors = "SignInterceptorKey")
public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }




    public void newTask(View view) {
        Router.build("home").newTask().go(this, new RouteCallback() {
            @Override
            public void callback(RouteStatus status, Uri uri, String message) {
                Log.e("Xu", message);
            }
        });
    }
}