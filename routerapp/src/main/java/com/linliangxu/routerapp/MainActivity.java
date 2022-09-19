package com.linliangxu.routerapp;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import com.chenenyu.router.RouteCallback;
import com.chenenyu.router.RouteStatus;
import com.chenenyu.router.Router;
import com.chenenyu.router.RouterInterceptorTable;
import com.chenenyu.router.RouterRouteTable;
import com.chenenyu.router.RouterTargetInterceptorsTable;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Router.handleRouteTable(new RouterRouteTable());
        Router.handleInterceptorTable(new RouterInterceptorTable());
        Router.handleTargetInterceptorsTable(new RouterTargetInterceptorsTable());

        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
            R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
            .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);

        Log.e("Xu", "message");
        Router.build("home").go(this, new RouteCallback() {
            @Override
            public void callback(RouteStatus status, Uri uri, String message) {
            }
        });
    }

}