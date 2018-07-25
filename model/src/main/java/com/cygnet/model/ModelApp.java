package com.cygnet.model;

import android.arch.persistence.room.Room;

import com.cygnet.framework.BaseApplication;
import com.cygnet.framework.utils.AppLog;
import com.cygnet.model.db.MyDatabase;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.IOException;

import retrofit2.Converter;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;

import static android.content.ContentValues.TAG;
import static com.cygnet.framework.utils.Constants.DATABASE_NAME;
import static com.cygnet.model.db.MyDatabase.MIGRATION_1_2;


/**
 * Name : ModelApp
 *<br> Created by 1618 on 10/30/2017
 *<br> Modified by 1618 on 10/30/2017
 *<br> Purpose : Application class of Model module. App module's Application should have this class in super class hierarchy.
 */
public class ModelApp extends BaseApplication {

    private static ModelApp mAppInstance;
    private ObjectMapper jsonMapper;

    public Converter.Factory JACKSON ;
    public Converter.Factory GSON ;

    private MyDatabase db;

    //    private Gson mGson;

    /**
     * Created by Dev 1064 on 5/5/2016
     * Modified by Dev 1064 on 5/5/2016
     * <p/>
     * To get singleton instance of {@link ModelApp}
     *
     * @return ModelApp instance
     */
    public static ModelApp getAppInstance() {
        return mAppInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        mAppInstance = this;
    }

    /**
     * Name : getDbInstance
     *<br> Created by 1730 on 11/1/2017
     *<br> Modified by 1730 on 11/1/2017
     *<br> Purpose : To get instance of RoomDatabase class
     * @return Database instance
     */
    public MyDatabase getDbInstance(){

        if (db == null)
            db = Room.databaseBuilder(this, MyDatabase.class, DATABASE_NAME)

                    /**
                     * Room ensures that Database is never accessed on the main thread because it may lock the main thread and trigger an ANR.
                     * If you need to access the database from the main thread, you should always use async alternatives or manually move the call to a background thread.
                     */
//                    .allowMainThreadQueries()
                    .addMigrations(MIGRATION_1_2)
                    .build();

        return db;
    }

    public Converter.Factory getJaksonConverter(){
        if (JACKSON == null)
            return JACKSON = JacksonConverterFactory.create(getJsonMapper());
        else
            return JACKSON;
    }

    public Converter.Factory getGsonConverter(){
        if (GSON == null)
            return GSON = GsonConverterFactory.create();
        else
            return GSON;
    }


//    /**
//     * Name : ModelApp getEventBus
//     *<br> Created by 1618 on 10/30/2017
//     *<br> Modified by 1618 on 10/30/2017
//     *<br> Purpose : To get eventbus instance for presenter.
//     * @return  : {@link EventBus} object
//     */
//    public EventBus getEventBus() {
//        if (mEventBus == null)
//            mEventBus = EventBus.getDefault();
//        return mEventBus;
//    }

//    /**
//     * Name : ModelApp getRestApis
//     *<br> Created by 1618 on 10/30/2017
//     *<br> Modified by 1618 on 10/30/2017
//     *<br> Purpose :    Get singleton of RestWebCaller .
//     * @return  : Object of {@link RestWebCaller}
//     */
//    public RestWebCaller getRestApis() {
//        if (restWebCaller == null)
//            restWebCaller = new RestWebCaller();
//        return restWebCaller;
//    }

    /**
     * Name : ModelApp getJsonMapper
     *<br> Created by 1618 on 10/30/2017
     *<br> Modified by 1618 on 10/30/2017
     *<br> Purpose : Get singleton json mapper object.
     * @return  : Object of {@link ObjectMapper}
     */
    public ObjectMapper getJsonMapper() {
        if (jsonMapper == null) {
            jsonMapper = new ObjectMapper();


// to enable standard indentation ("pretty-printing"):
            jsonMapper.enable(SerializationFeature.INDENT_OUTPUT);
// to allow serialization of "empty" POJOs (no properties to serialize)
// (without this setting, an exception is thrown in those cases)
            jsonMapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
// to write java.util.Date, Calendar as number (timestamp):
            jsonMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

// DeserializationFeature for changing how JSON is read as POJOs:

// to prevent exception when encountering unknown property:
            jsonMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
// to allow coercion of JSON empty String ("") to null Object value:
            jsonMapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);

            jsonMapper.setVisibility(jsonMapper.getSerializationConfig()
                    .getDefaultVisibilityChecker()
                    .withFieldVisibility(JsonAutoDetect.Visibility.ANY)
                    .withGetterVisibility(JsonAutoDetect.Visibility.NONE)
                    .withSetterVisibility(JsonAutoDetect.Visibility.NONE)
                    .withCreatorVisibility(JsonAutoDetect.Visibility.NONE));
        }
        return jsonMapper;
    }



    /**
     * Name : ModelApp getJSONFromObject
     *<br> Created by 1618 on 10/30/2017
     *<br> Modified by 1618 on 10/30/2017
     *<br> Purpose :This method will return json String from Object.
     *
     * @param o : class object of which we want to get json
     * @return : json string of POJO class.
     */
    public String getJSONFromObject(Object o) {
        try {
            return getJsonMapper().writeValueAsString(o);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * Created By : dev1618
     * Created Date : 7/19/2016
     * <p>
     *
     */

    /**
     * Name : ModelApp convertJsonToObject
     *<br> Created by 1618 on 10/30/2017
     *<br> Modified by 1618 on 10/30/2017
     *<br> Purpose : Convert any json string to model java class.
     *
     * @param jsonString    : json string
     * @param objectClass   : class for which we want to get object
     * @return  : object of class
     */
    public Object convertJsonToObject(String jsonString, Class objectClass) {
        try {
            return getJsonMapper().readValue(jsonString, objectClass);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Name : ModelApp getGson
     * <br> Created by 1549 on 9/1/2017
     * <br> Modified by 1549 on 9/1/2017
     * <br> Purpose :
     * To get singleton instance of {@link } with exclude field without expose. It will generate {@link } instance only if existing instance is null
     *
     * @return OkHttpClient instance
     */
//    public Gson getGson()
//    {
//        if (mGson == null)
//        {
//            GsonFactory.Builder builder = new GsonFactory.Builder();
//            mGson = builder.buildByExcludeFieldsWithoutExpose();
//        }
//        return mGson;
//    }
    @Override
    public void onLowMemory() {
        super.onLowMemory();
        AppLog.e(TAG, "onLowMemory()");
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        AppLog.e(TAG, "onTrimMemory()");
        AppLog.e(TAG, "level: " + level);
    }


}
