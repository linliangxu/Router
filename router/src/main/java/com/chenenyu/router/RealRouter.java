package com.chenenyu.router;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.chenenyu.router.chain.AppInterceptorsHandler;
import com.chenenyu.router.chain.AttrsProcessor;
import com.chenenyu.router.chain.BaseValidator;
import com.chenenyu.router.chain.FragmentProcessor;
import com.chenenyu.router.chain.FragmentValidator;
import com.chenenyu.router.chain.IntentProcessor;
import com.chenenyu.router.chain.IntentValidator;
import com.chenenyu.router.util.RLog;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Core of router.
 * <br>
 * Created by chenenyu on 2017/3/30.
 */
final class RealRouter extends AbsRouter {
    private final RouteInterceptor mBaseValidator = new BaseValidator();
    private final RouteInterceptor mIntentValidator = new IntentValidator();
    private final RouteInterceptor mFragmentValidator = new FragmentValidator();
    private final RouteInterceptor mIntentProcessor = new IntentProcessor();
    private final RouteInterceptor mFragmentProcessor = new FragmentProcessor();
    private final RouteInterceptor mAppInterceptorsHandler = new AppInterceptorsHandler();
    private final RouteInterceptor mAttrsProcessor = new AttrsProcessor();

    private static final ThreadLocal<RealRouter> mRouterThreadLocal = new ThreadLocal<RealRouter>() {
        @Override
        protected RealRouter initialValue() {
            return new RealRouter();
        }
    };

    private RealRouter() {
    }

    static RealRouter getInstance() {
        return mRouterThreadLocal.get();
    }

    private void callback(RouteResponse response) {
        if (response.getStatus() != RouteStatus.SUCCEED) {
            RLog.w(response.getMessage());
        }
        if (mRouteRequest.getRouteCallback() != null) {
            mRouteRequest.getRouteCallback().callback(
                    response.getStatus(), mRouteRequest.getUri(), response.getMessage());
        }
    }



    @Override
    public Fragment getFragment(@NonNull Object source) {
        RouteResponse response = getFragmentChain(source).process();
        callback(response);
        return (Fragment) response.getResult();
    }

    @Override
    public RouteInterceptor.Chain getFragmentChain(@NonNull Object source) {
        List<RouteInterceptor> interceptors = new LinkedList<>();
        Collections.addAll(interceptors, mBaseValidator, mFragmentValidator,
            mFragmentProcessor, mAppInterceptorsHandler, mAttrsProcessor);
        return new RealInterceptorChain(source, mRouteRequest, interceptors);
    }


    @Override
    public Intent getIntent(@NonNull Object source) {
        RouteResponse response = getIntentChain(source).process();
        callback(response);
        return (Intent) response.getResult();
    }

    @Override
    public RouteInterceptor.Chain getIntentChain(@NonNull Object source) {
        List<RouteInterceptor> interceptors = new LinkedList<>();
        Collections.addAll(interceptors, mBaseValidator, mIntentValidator,
            mIntentProcessor, mAppInterceptorsHandler, mAttrsProcessor);
        return new RealInterceptorChain(source, mRouteRequest, interceptors);
    }

    @Override
    public void go(Context context) {
        Intent intent = getIntent(context);
        if (intent != null) {
            Bundle options = mRouteRequest.getActivityOptionsBundle();
            if (context instanceof Activity) {
                ((Activity) context).startActivityForResult(intent, mRouteRequest.getRequestCode(), options);
                if (mRouteRequest.getEnterAnim() >= 0 && mRouteRequest.getExitAnim() >= 0) {
                    ((Activity) context).overridePendingTransition(
                            mRouteRequest.getEnterAnim(), mRouteRequest.getExitAnim());
                }
                if (mRouteRequest.isFinish()) ((Activity) context).finish();
            } else {
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent, options);
            }
        }
    }

    @Override
    public void go(Fragment fragment) {
        Intent intent = getIntent(fragment);
        if (intent != null) {
            Bundle options = mRouteRequest.getActivityOptionsBundle();
            if (mRouteRequest.getRequestCode() < 0) {
                fragment.startActivity(intent, options);
            } else {
                fragment.startActivityForResult(intent, mRouteRequest.getRequestCode(), options);
            }
            if (mRouteRequest.getEnterAnim() >= 0 && mRouteRequest.getExitAnim() >= 0
                    && fragment.getActivity() != null) {
                // Add transition animation.
                fragment.getActivity().overridePendingTransition(
                        mRouteRequest.getEnterAnim(), mRouteRequest.getExitAnim());
            }
        }
    }

    @Override
    public void start(Context context) {
        Intent intent = getIntent(context);
        if (intent != null) {
            Bundle options = mRouteRequest.getExtras();
            if (options != null) intent.putExtras(options);
            context.startService(intent);
        }
    }

    @Override
    public void stop(Context context) {
        Intent intent = getIntent(context);
        if (intent != null) {
            context.stopService(intent);
        }
    }

    /*
     * 判断服务是否启动,context上下文对象 ，className服务的name
     */
    @Override
    public boolean running(Context context) {

        //String className = AptHub.routeTable.get(Routes.MEDIA_PLAYER).getName();

        Intent intent = getIntent(context);

        if (intent != null) {
            String className = intent.getComponent().getClassName();
            ActivityManager activityManager = (ActivityManager) context
                .getSystemService(Context.ACTIVITY_SERVICE);
            List<ActivityManager.RunningServiceInfo> serviceList = activityManager
                .getRunningServices(60);

            if (!(serviceList.size() > 0)) {
                return false;
            }

            for (int i = 0; i < serviceList.size(); i++) {
                if (serviceList.get(i).service.getClassName().contains(className) == true) {
                    return true;
                }
            }
        }

        return false;
    }
}
