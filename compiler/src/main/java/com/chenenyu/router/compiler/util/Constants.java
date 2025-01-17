package com.chenenyu.router.compiler.util;

/**
 * <p>
 * Created by Cheney on 2017/1/12.
 */
public class Constants {
    public static final String OPTION_MODULE_NAME = "moduleName";
    public static final String OPTION_LOGGABLE = "loggable";
    private static final String BASE_PACKAGE_NAME = "com.chenenyu.router";
    public static final String CLASS_JAVA_DOC = "Generated by Router. Do not edit it!\n";

    public static final String ACTIVITY_FULL_NAME = "android.app.Activity";
    public static final String FRAGMENT_X_FULL_NAME = "androidx.fragment.app.Fragment";
    public static final String SERVICE_FULL_NAME = "android.app.Service";

    public static final String ROUTE_ANNOTATION_TYPE = "com.chenenyu.router.annotation.Route";
    public static final String INTERCEPTOR_ANNOTATION_TYPE = "com.chenenyu.router.annotation.Interceptor";
    public static final String PARAM_ANNOTATION_TYPE = "com.chenenyu.router.annotation.InjectParam";

    public static final String APT_PACKAGE_NAME = "com.chenenyu.router";

    public static final String METHOD_HANDLE = "handle";
    public static final String METHOD_INJECT = "inject";

    public static final String ROUTE_TABLE = "RouteTable";
    public static final String ROUTE_TABLE_FULL_NAME = BASE_PACKAGE_NAME + ".template." + ROUTE_TABLE;

    public static final String INTERCEPTOR_FULL_NAME = BASE_PACKAGE_NAME + ".RouteInterceptor";

    public static final String INTERCEPTOR_TABLE = "InterceptorTable";
    public static final String INTERCEPTOR_TABLE_FULL_NAME = BASE_PACKAGE_NAME + ".template." + INTERCEPTOR_TABLE;

    public static final String TARGET_INTERCEPTORS_TABLE = "TargetInterceptorsTable";
    public static final String TARGET_INTERCEPTORS_FULL_NAME = BASE_PACKAGE_NAME + ".template." + TARGET_INTERCEPTORS_TABLE;

    private static final String PARAM_INJECTOR = "ParamInjector";
    public static final String PARAM_INJECTOR_FULL_NAME = BASE_PACKAGE_NAME + ".template." + PARAM_INJECTOR;
    public static final String PARAM_CLASS_SUFFIX = "$$Router$$" + PARAM_INJECTOR; // XXXActivity$$Router$$Params
}
