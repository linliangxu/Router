package com.linliangxu.routerapp.ui.home;

import com.chenenyu.router.RouteInterceptor;
import com.chenenyu.router.RouteResponse;
import com.chenenyu.router.Router;
import com.chenenyu.router.annotation.Interceptor;

@Interceptor("SampleInterceptor")
public class SampleInterceptor implements RouteInterceptor {
    @Override
    public RouteResponse intercept(Chain chain) {
        return Router.build("interceptor").getFragmentChain(chain.getSource()).process();
    }
}