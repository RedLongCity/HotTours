package com.smitsworks.redlo.hottours;

import android.app.Application;
import android.content.Context;

/**
 * Created by redlongcity on 03.12.2017.
 * fundamental class for trading information throw application
 */

public class App extends Application{

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        App.context = getApplicationContext();
    }

    public static Context getAppContext(){
        return App.context;
    }
}
