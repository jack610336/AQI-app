package com.practice.jack_wang.weatherquery;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.practice.jack_wang.weatherquery.DBtools.DBtools;
import com.practice.jack_wang.weatherquery.adapter.MyRecyclerAdpter;
import com.practice.jack_wang.weatherquery.sqlite.weatherEntry;
import com.practice.jack_wang.weatherquery.sqlite.weatherEntryDao;

import org.greenrobot.greendao.query.QueryBuilder;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    OkHttpClient client = new OkHttpClient();

    private RecyclerView mRecyclerView;
    private MyRecyclerAdpter adpter;
    private SwipeRefreshLayout swipeRefreshLayout;
    private List<weatherEntry> mWeatherEntryList = new ArrayList<>();

    private weatherEntryDao mWeatherDao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();



        searchWeatherAPIs();
        queryDBdata();
        refreshactivity();



    }

    @SuppressLint("ResourceAsColor")
    private void refreshactivity(){

        swipeRefreshLayout.setColorSchemeColors(R.color.colorPrimary);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                    refreshAQI();

            }
        });
    }

    private void refreshAQI() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                    swipeRefreshLayout.setRefreshing(false);
                }catch (InterruptedException e ){
                    e.printStackTrace();
                }

            }
        }).start();
        searchWeatherAPIs();
        queryDBdata();

    }

    private void findViews(){

        swipeRefreshLayout = (SwipeRefreshLayout)findViewById(R.id.swipe_refresh);
        mRecyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        adpter = new MyRecyclerAdpter(mWeatherEntryList);
        adpter.setRecyclerViewItemClick(new MyRecyclerAdpter.RecyclerViewItemClick() {
            @Override
            public void onItemClick(View view, int position) {
                //如果點了其中一個方塊會做甚麼事情
            }
        });
        mRecyclerView.setAdapter(adpter);
    }


    //查詢資料庫
    private void queryDBdata(){

        new queryDB().execute();

    }

    //非同步方式查詢資料庫
    class queryDB extends AsyncTask<String,Void,String>{

        @Override
        protected String doInBackground(String... strings) {


            DBtools dBtools = DBtools.Instance(MainActivity.this);
            mWeatherDao = dBtools.getCityDao();

            mWeatherEntryList = mWeatherDao.loadAll();
            adpter.setList(mWeatherEntryList);
            return null;
        }
    }


    //上網抓取資料
    class API_Task extends AsyncTask<String,Void,String>{

        @Override
        protected String doInBackground(String... strings) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try{
                        Thread.sleep(1000);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Request request = new Request.Builder()
//                                    .url("http://data.taipei/opendata/datalist/apiAccess?scope=resourceAquire&rid=1f1aaba5-616a-4a33-867d-878142cac5c4")
                                    .url("http://opendata2.epa.gov.tw/AQI.json")
                                    .build();

                            Call call = client.newCall(request);
                            call.enqueue(new Callback() {
                                @Override
                                public void onResponse(Call call, Response response) throws IOException {
                                    String json = response.body().string();
                                    Log.e("OKHTTP", json);
                                    //解析JSON
                                    parseJSON(json);
                                }
                                @Override
                                public void onFailure(Call call, IOException e) {
                                    //告知使用者連線失敗
                                    Log.e( " FAILLLLL"+String.valueOf(call),  "FFFFFF "+String.valueOf(e));
                                }
                            });
                        }
                    });
                }
            }).start();
            return null;
        }
    }



    //抓取天氣資訊
    private void searchWeatherAPIs(){

        new API_Task().execute();

    }





    //解析JSON 資料
    private void parseJSON(String s){

        try {
            JSONArray TownsJsonArray = new JSONArray(s);

            for(int j = 0;j<TownsJsonArray.length();j++){

                JSONObject TownjsonObject = TownsJsonArray.getJSONObject(j);

                String cityid = TownjsonObject.getString("County");
                String value = TownjsonObject.getString("AQI");
                String locationName = TownjsonObject.getString("SiteName");
                String dataTime = TownjsonObject.getString("PublishTime");

//                mWeatherDao.deleteAll();
                doInsertOrEdit(Long.valueOf(j),cityid,locationName,value,dataTime);

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    // 控制新增或修改

    private void doInsertOrEdit(Long Id,String cityid,String cityname,String cityvalue,String date){
        weatherEntry entry = new weatherEntry();
        entry.setId(Id);
        entry.setCityId(cityid);
        entry.setCityName(cityname);
        entry.setCityValue(cityvalue);
        entry.setDate(date);
        mWeatherDao.insertOrReplace(entry);


        mWeatherEntryList.add(entry);

//        mWeatherEntryList = mWeatherDao.loadAll();
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                adpter.setList(mWeatherEntryList);
            }});



    }


    @Override
    public void onClick(View view) {

        //點選到畫面上  所有元件  可以偵測所有元件發生事件 再用switch做事
    }
}