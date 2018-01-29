package com.jack.aqi_query;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.support.v4.widget.SwipeRefreshLayout;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.jack.aqi_query.DBtools.DBtools;
import com.jack.aqi_query.adapter.MyRecyclerAdpter;
import com.jack.aqi_query.sqlite.weatherEntry;
import com.jack.aqi_query.sqlite.weatherEntryDao;

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

public class MainActivity extends Navigation_BaseActivity implements View.OnClickListener {


    OkHttpClient client = new OkHttpClient();

    private RecyclerView mRecyclerView;
    public static MyRecyclerAdpter adpter;
    private SwipeRefreshLayout swipeRefreshLayout;
    private List<weatherEntry> mWeatherEntryList = new ArrayList<>();
    private weatherEntryDao mWeatherDao;

    private int[] TollBarTitle = {R.string.app_name};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();


        toolbar.setTitle(TollBarTitle[0]);//設置ToolBar Title
        setUpToolBar();//使用父類別的setUpToolBar()，設置ToolBar
        CurrentMenuItem = 0;//目前Navigation項目位置
        NV.getMenu().getItem(CurrentMenuItem).setChecked(true);//設置Navigation目前項目被選取狀態



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

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(2000);
                        }catch (InterruptedException e ){
                            e.printStackTrace();
                        }

                    }
                }).start();
                swipeRefreshLayout.setRefreshing(false);
                searchWeatherAPIs();
                queryDBdata();

            }
        });
    }

    private void findViews(){

        swipeRefreshLayout = findViewById(R.id.swipe_refresh);
        mRecyclerView = findViewById(R.id.recyclerView);
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
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    adpter.setList(queryDBData());
                }});
            return null;
        }
    }


    //上網抓取資料
    class API_Task extends AsyncTask<String,Void,String>{
        Toast toast = Toast.makeText(MainActivity.this,getString(R.string.noNetworkInfo), Toast.LENGTH_LONG);

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
                                    toast.show();
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

                String cityid = TownjsonObject.getString("County");//城市名字
                String value = TownjsonObject.getString("AQI");//AQI值
                String locationName = TownjsonObject.getString("SiteName");//鄉鎮名稱
                String dataTime = TownjsonObject.getString("PublishTime");//更新時間
                String cityPics = TownjsonObject.getString("County"); //城市名字多存了一欄



                if(value.length() == 0){
                    value = "-1";
                }

                doInsertOrEdit(Long.valueOf(j),cityid,locationName,value,dataTime,cityPics);

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    // 控制新增或修改

    private void doInsertOrEdit(Long Id,String cityName,String aqiValue,String townName,String date,String cityPics){
        weatherEntry entry = new weatherEntry();
        entry.setId(Id);//_id
        entry.setCityId(cityName);//城市名字
        entry.setCityName(aqiValue);//AQI值
        entry.setCityValue(townName);//鄉鎮名稱
        entry.setDate(date);//更新時間
        entry.setCityPics(cityPics);//城市名字多存了一欄
        mWeatherDao.insertOrReplace(entry);


        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                adpter.setList(queryDBData());
            }});
    }


    @Override
    public void onClick(View view) {

        //點選到畫面上  所有元件  可以偵測所有元件發生事件
    }
}
