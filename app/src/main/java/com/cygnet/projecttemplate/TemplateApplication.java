package com.cygnet.projecttemplate;

import android.content.Context;

import com.cygnet.model.ModelApp;
import com.facebook.FacebookSdk;

/**
 * Name : TemplateApplication
 *<br> Created by 1618 on 10/30/2017
 *<br> Modified by 1618 on 10/30/2017
 *<br> Purpose :    Application class to manage the app level configurations.
 */
public class TemplateApplication extends ModelApp {

    private final String DATABASE_NAME = "demoroom";
    public static Context mContext;
//    private MyDatabase db;


    @Override
    public void onCreate() {
        super.onCreate();

        FacebookSdk.sdkInitialize(getApplicationContext());
      //  Fabric.with(this, new Crashlytics());


        mContext = this;
//        getDbInstance();
    }


    /**
     * Name : getDbInstance
     *<br> Created by 1730 on 11/1/2017
     *<br> Modified by 1730 on 11/1/2017
     *<br> Purpose : To get instance of RoomDatabase class
     * @return Database instance
     */
//    public MyDatabase getDbInstance(){
//
//        if (db == null)
//            db = Room.databaseBuilder(mContext, MyDatabase.class, DATABASE_NAME).allowMainThreadQueries().build();
//
//        return db;
//    }
}
