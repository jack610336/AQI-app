package com.jack.aqi_query.DBtools;

import android.content.Context;

import com.jack.aqi_query.sqlite.DaoMaster;
import com.jack.aqi_query.sqlite.DaoSession;
import com.jack.aqi_query.sqlite.weatherEntryDao;

/**
 * Created by Jack_Wang on 2017/12/14.
 */

public class DBtools {
    private static final String DB_NAME = "weather.db";

    private static DBtools dBtools = null;

    private DaoSession daoSession;


    public static DBtools Instance(Context context){
        if(dBtools == null){
            dBtools = new DBtools(context);
        }

        return dBtools;

    }

    private DBtools(Context context){

        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(context,DB_NAME);
        DaoMaster daoMaster = new DaoMaster(helper.getWritableDb());
        daoSession = daoMaster.newSession();

    }

    public weatherEntryDao getCityDao(){
        return daoSession.getWeatherEntryDao();

    }


}
