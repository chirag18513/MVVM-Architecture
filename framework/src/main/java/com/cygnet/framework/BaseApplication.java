package com.cygnet.framework;

import android.app.Application;

import com.cygnet.framework.utils.DeviceUuidFactory;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

/**
 * Name : BaseApplication
 *<br> Created by 1618 on 10/30/2017
 *<br> Modified by 1618 on 10/30/2017
 *<br> Purpose : Base application class which contains the leak canary as well as device
 * UDID factory instance.
 */
public class BaseApplication extends Application {

    private RefWatcher mRefWatcher;
    private DeviceUuidFactory mDeviceUuidFactory;
    private static BaseApplication mInstance;

    public static BaseApplication getInstance() {

        return mInstance;
    }

    @Override
    public void onCreate() {

        mInstance = this;
        if (BuildConfig.DEBUG)
            mRefWatcher = LeakCanary.install(this);
        super.onCreate();
        mDeviceUuidFactory = new DeviceUuidFactory(this);
    }

    public RefWatcher getRefWatcher() {
        return mRefWatcher;
    }

    public String getDeviceUuidFactory() {
        return mDeviceUuidFactory.getDeviceUuid().toString();
    }
}
