package com.linliangxu.routerapp.ui.home;

import android.util.Log;

import com.chenenyu.router.RouteInterceptor;
import com.chenenyu.router.RouteResponse;
import com.chenenyu.router.Router;
import com.chenenyu.router.annotation.Interceptor;

@Interceptor("SignInterceptorKey")
public class SignInterceptor implements RouteInterceptor {
    @Override
    public RouteResponse intercept(Chain chain) {
        return Router.build("interceptor").getIntentChain(chain.getSource()).process();
    }
}