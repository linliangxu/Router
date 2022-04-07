package com.chenenyu.router;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.Settings;

/**
 * Help to construct a {@link RouteRequest}.
 * <p>
 * Created by chenenyu on 2017/3/31.
 */
public class SystemRouter {

    public static final int APPLICATION_DETAILS_SETTINGS = 1;
    public static final int ACTION_SETTINGS = 2;

    private int action;

    private static SystemRouter INSTANCE = new SystemRouter();

    private SystemRouter() {}

    public static SystemRouter getInstance() {
        return INSTANCE;
    }

    public SystemRouter build(int action) {
        this.action = action;
        return this;
    }

    public void go(Context context) {
        Intent intent = new Intent();
        switch (action) {
            case APPLICATION_DETAILS_SETTINGS:
                intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                intent.addCategory(Intent.CATEGORY_DEFAULT);
                intent.setData(Uri.parse("package:" + context.getPackageName()));
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                intent.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
                break;
            case ACTION_SETTINGS:
                intent.setAction(Settings.ACTION_SETTINGS);
                break;
        }

        context.startActivity(intent);
    }
}
