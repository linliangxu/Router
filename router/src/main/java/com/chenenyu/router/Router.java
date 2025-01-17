package com.chenenyu.router;

import android.net.Uri;

import com.chenenyu.router.matcher.AbsMatcher;
import com.chenenyu.router.template.InterceptorTable;
import com.chenenyu.router.template.RouteTable;
import com.chenenyu.router.template.TargetInterceptorsTable;

import java.util.ArrayList;
import java.util.List;

/**
 * Entry class.
 * <p>
 * Created by chenenyu on 2016/12/20.
 */
public class Router {
    /**
     * You can get the raw uri in target page by call <code>intent.getStringExtra(Router.RAW_URI)</code>.
     */
    public static final String RAW_URI = "raw_uri";

    private static final List<RouteInterceptor> sGlobalInterceptors = new ArrayList<>();


    public static IRouter build(String path) {
        return build(path == null ? null : Uri.parse(path));
    }

    public static IRouter build(Uri uri) {
        return RealRouter.getInstance().build(uri);
    }

    public static IRouter build(RouteRequest request) {
        return RealRouter.getInstance().build(request);
    }

    public static SystemRouter build(int action) {
        return SystemRouter.getInstance().build(action);
    }

    /**
     * Custom route table.
     */
    public static void handleRouteTable(RouteTable routeTable) {
        if (routeTable != null) routeTable.handle(AptHub.routeTable);
    }

    public static void handleInterceptorTable(InterceptorTable interceptorTable) {
        if (interceptorTable != null) interceptorTable.handle(AptHub.interceptorTable);
    }

    public static void handleTargetInterceptorsTable(TargetInterceptorsTable targetInterceptorsTable) {
        if (targetInterceptorsTable != null) targetInterceptorsTable.handle(AptHub.targetInterceptorsTable);
    }




    /**
     * Auto inject params from bundle.
     *
     * @param obj Instance of Activity or Fragment.
     */
    public static void injectParams(Object obj) {
        AptHub.injectParams(obj);
    }

    /**
     * Global interceptor.
     */
    public static void addGlobalInterceptor(RouteInterceptor routeInterceptor) {
        sGlobalInterceptors.add(routeInterceptor);
    }

    public static List<RouteInterceptor> getGlobalInterceptors() {
        return sGlobalInterceptors;
    }

    /**
     * Register your own matcher.
     *
     * @see com.chenenyu.router.matcher.AbsExplicitMatcher
     * @see com.chenenyu.router.matcher.AbsImplicitMatcher
     */
    public static void registerMatcher(AbsMatcher matcher) {
        MatcherRegistry.register(matcher);
    }

    public static void clearMatcher() {
        MatcherRegistry.clear();
    }
}
