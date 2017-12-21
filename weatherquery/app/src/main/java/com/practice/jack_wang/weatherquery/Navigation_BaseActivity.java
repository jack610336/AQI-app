package com.practice.jack_wang.weatherquery;

import android.os.AsyncTask;
import android.support.annotation.LayoutRes;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;


import com.practice.jack_wang.weatherquery.DBtools.DBtools;
import com.practice.jack_wang.weatherquery.sqlite.weatherEntry;
import com.practice.jack_wang.weatherquery.sqlite.weatherEntryDao;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jack_Wang on 2017/12/18.
 */

public class Navigation_BaseActivity extends AppCompatActivity {
    private DrawerLayout DL;
    private FrameLayout FL;

    protected NavigationView NV;
    protected Toolbar toolbar;
    protected int CurrentMenuItem = 0;//紀錄目前User位於哪一個項目
    protected int citycode =0;

    weatherEntryDao mWeatherDao;
    private List<weatherEntry> mWeatherEntryList = new ArrayList<>();

    @Override
    public void setContentView(@LayoutRes int layoutResID) {


            DL = (DrawerLayout) getLayoutInflater().inflate(R.layout.navigation_drawer, null);
            FL = (FrameLayout) DL.findViewById(R.id.content_frame);
            NV = (NavigationView)DL.findViewById(R.id.Left_Navigation);
            getLayoutInflater().inflate(layoutResID, FL, true);
            super.setContentView(DL);
            toolbar = findViewById(R.id.ToolBar);
            setUpNavigation();


    }
    private void setUpNavigation() {
        // Set navigation item selected listener
        NV.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                if(!(menuItem == NV.getMenu().getItem(CurrentMenuItem))) {//判斷使者者是否點擊當前畫面的項目，若不是，根據所按的項目做出分別的動作
                    switch (menuItem.getItemId()) {
                        case R.id.tAll:
                            citycode=0;
                            new queryDB().execute();
                            CurrentMenuItem = 0;//目前Navigation項目位置
                            NV.getMenu().getItem(CurrentMenuItem).setChecked(true);
                            DL.closeDrawer(GravityCompat.START);
                            break;
                        case R.id.tKeelung:
                            citycode=1;
                            new queryDB().execute();
                            CurrentMenuItem = 1;//目前Navigation項目位置
                            NV.getMenu().getItem(CurrentMenuItem).setChecked(true);
                            DL.closeDrawer(GravityCompat.START);
                            break;
                        case R.id.tNewTaipei:
                            citycode=2;
                            new queryDB().execute();
                            CurrentMenuItem = 2;//目前Navigation項目位置
                            NV.getMenu().getItem(CurrentMenuItem).setChecked(true);
                            DL.closeDrawer(GravityCompat.START);
                            break;
                        case R.id.tTaipei:
                            citycode=3;
                            new queryDB().execute();
                            CurrentMenuItem = 3;//目前Navigation項目位置
                            NV.getMenu().getItem(CurrentMenuItem).setChecked(true);
                            DL.closeDrawer(GravityCompat.START);
                            break;
                        case R.id.tTaoyuan:
                            citycode=4;
                            new queryDB().execute();
                            CurrentMenuItem = 4;//目前Navigation項目位置
                            NV.getMenu().getItem(CurrentMenuItem).setChecked(true);
                            DL.closeDrawer(GravityCompat.START);
                            break;
                        case R.id.tHsinchuB:
                            citycode=5;
                            new queryDB().execute();
                            CurrentMenuItem = 5;//目前Navigation項目位置
                            NV.getMenu().getItem(CurrentMenuItem).setChecked(true);
                            DL.closeDrawer(GravityCompat.START);
                            break;
                        case R.id.tHsinchu:
                            citycode=6;
                            new queryDB().execute();
                            CurrentMenuItem = 6;//目前Navigation項目位置
                            NV.getMenu().getItem(CurrentMenuItem).setChecked(true);
                            DL.closeDrawer(GravityCompat.START);
                            break;
                        case R.id.tMiaoliB:
                            citycode=7;
                            new queryDB().execute();
                            CurrentMenuItem = 7;//目前Navigation項目位置
                            NV.getMenu().getItem(CurrentMenuItem).setChecked(true);
                            DL.closeDrawer(GravityCompat.START);
                            break;
                        case R.id.tTaichung:
                            citycode=8;
                            new queryDB().execute();
                            CurrentMenuItem = 8;//目前Navigation項目位置
                            NV.getMenu().getItem(CurrentMenuItem).setChecked(true);
                            DL.closeDrawer(GravityCompat.START);
                            break;
                        case R.id.tChanghuaB:
                            citycode=9;
                            new queryDB().execute();
                            CurrentMenuItem = 9;//目前Navigation項目位置
                            NV.getMenu().getItem(CurrentMenuItem).setChecked(true);
                            DL.closeDrawer(GravityCompat.START);
                            break;
                        case R.id.tNantouB:
                            citycode=10;
                            new queryDB().execute();
                            CurrentMenuItem = 10;//目前Navigation項目位置
                            NV.getMenu().getItem(CurrentMenuItem).setChecked(true);
                            DL.closeDrawer(GravityCompat.START);
                            break;
                        case R.id.tYunlinB:
                            citycode=11;
                            new queryDB().execute();
                            CurrentMenuItem = 11;//目前Navigation項目位置
                            NV.getMenu().getItem(CurrentMenuItem).setChecked(true);
                            DL.closeDrawer(GravityCompat.START);
                            break;
                        case R.id.tChiayi:
                            citycode=12;
                            new queryDB().execute();
                            CurrentMenuItem = 12;//目前Navigation項目位置
                            NV.getMenu().getItem(CurrentMenuItem).setChecked(true);
                            DL.closeDrawer(GravityCompat.START);
                            break;
                        case R.id.tChiayiB:
                            citycode=13;
                            new queryDB().execute();
                            CurrentMenuItem = 13;//目前Navigation項目位置
                            NV.getMenu().getItem(CurrentMenuItem).setChecked(true);
                            DL.closeDrawer(GravityCompat.START);
                            break;
                        case R.id.tTainan:
                            citycode=14;
                            new queryDB().execute();
                            CurrentMenuItem = 14;//目前Navigation項目位置
                            NV.getMenu().getItem(CurrentMenuItem).setChecked(true);
                            DL.closeDrawer(GravityCompat.START);
                            break;
                        case R.id.tKaohsiung:
                            citycode=15;
                            new queryDB().execute();
                            CurrentMenuItem = 15;//目前Navigation項目位置
                            NV.getMenu().getItem(CurrentMenuItem).setChecked(true);
                            DL.closeDrawer(GravityCompat.START);
                            break;
                        case R.id.tPingtungB:
                            citycode=16;
                            new queryDB().execute();
                            CurrentMenuItem = 16;//目前Navigation項目位置
                            NV.getMenu().getItem(CurrentMenuItem).setChecked(true);
                            DL.closeDrawer(GravityCompat.START);
                            break;
                        case R.id.tYilan:
                            citycode=17;
                            new queryDB().execute();
                            CurrentMenuItem = 17;//目前Navigation項目位置
                            NV.getMenu().getItem(CurrentMenuItem).setChecked(true);
                            DL.closeDrawer(GravityCompat.START);
                            break;
                        case R.id.tHualienB:
                            citycode=18;
                            new queryDB().execute();
                            CurrentMenuItem = 18;//目前Navigation項目位置
                            NV.getMenu().getItem(CurrentMenuItem).setChecked(true);
                            DL.closeDrawer(GravityCompat.START);
                            break;
                        case R.id.tTaitung:
                            citycode=19;
                            new queryDB().execute();
                            CurrentMenuItem = 19;//目前Navigation項目位置
                            NV.getMenu().getItem(CurrentMenuItem).setChecked(true);
                            DL.closeDrawer(GravityCompat.START);
                            break;
                        case R.id.tLienchiangB:
                            citycode=20;
                            new queryDB().execute();
                            CurrentMenuItem = 20;//目前Navigation項目位置
                            NV.getMenu().getItem(CurrentMenuItem).setChecked(true);
                            DL.closeDrawer(GravityCompat.START);
                            break;
                        case R.id.tKinmen:
                            citycode=21;
                            new queryDB().execute();
                            CurrentMenuItem = 21;//目前Navigation項目位置
                            NV.getMenu().getItem(CurrentMenuItem).setChecked(true);
                            DL.closeDrawer(GravityCompat.START);
                            break;
                        case R.id.tPenghuB:
                            citycode=22;
                            new queryDB().execute();
                            CurrentMenuItem = 22;//目前Navigation項目位置
                            NV.getMenu().getItem(CurrentMenuItem).setChecked(true);
                            DL.closeDrawer(GravityCompat.START);
                            break;


                    }
                }
                else {//點擊當前項目時，收起Navigation
                    DL.closeDrawer(GravityCompat.START);
                }
                return false;
            }
        });

    }



    protected List<weatherEntry> queryDBData(){

        DBtools dBtools = DBtools.Instance(Navigation_BaseActivity.this);
        mWeatherDao = dBtools.getCityDao();

        switch (citycode){

            case 0:
                mWeatherEntryList= mWeatherDao.loadAll();
                break;
            case 1:
                mWeatherEntryList= mWeatherDao.queryBuilder()
                        .where(weatherEntryDao.Properties.CityPics.eq("基隆市"))
                        .list();
                break;
            case 2:
                mWeatherEntryList= mWeatherDao.queryBuilder()
                        .where(weatherEntryDao.Properties.CityPics.eq("新北市"))
                        .list();
                break;
            case 3:
                mWeatherEntryList= mWeatherDao.queryBuilder()
                        .where(weatherEntryDao.Properties.CityPics.eq("臺北市"))
                        .list();
                break;
            case 4:
                mWeatherEntryList= mWeatherDao.queryBuilder()
                        .where(weatherEntryDao.Properties.CityPics.eq("桃園市"))
                        .list();
                break;
            case 5:
                mWeatherEntryList= mWeatherDao.queryBuilder()
                        .where(weatherEntryDao.Properties.CityPics.eq("新竹縣"))
                        .list();
                break;
            case 6:
                mWeatherEntryList= mWeatherDao.queryBuilder()
                        .where(weatherEntryDao.Properties.CityPics.eq("新竹市"))
                        .list();
                break;
            case 7:
                mWeatherEntryList= mWeatherDao.queryBuilder()
                        .where(weatherEntryDao.Properties.CityPics.eq("苗栗縣"))
                        .list();
                break;
            case 8:
                mWeatherEntryList= mWeatherDao.queryBuilder()
                        .where(weatherEntryDao.Properties.CityPics.eq("臺中市"))
                        .list();
                break;
            case 9:
                mWeatherEntryList= mWeatherDao.queryBuilder()
                        .where(weatherEntryDao.Properties.CityPics.eq("彰化縣"))
                        .list();
                break;
            case 10:
                mWeatherEntryList= mWeatherDao.queryBuilder()
                        .where(weatherEntryDao.Properties.CityPics.eq("南投縣"))
                        .list();
                break;
            case 11:
                mWeatherEntryList= mWeatherDao.queryBuilder()
                        .where(weatherEntryDao.Properties.CityPics.eq("雲林縣"))
                        .list();
                break;
            case 12:
                mWeatherEntryList= mWeatherDao.queryBuilder()
                        .where(weatherEntryDao.Properties.CityPics.eq("嘉義市"))
                        .list();
                break;
            case 13:
                mWeatherEntryList= mWeatherDao.queryBuilder()
                        .where(weatherEntryDao.Properties.CityPics.eq("嘉義縣"))
                        .list();
                break;
            case 14:
                mWeatherEntryList= mWeatherDao.queryBuilder()
                        .where(weatherEntryDao.Properties.CityPics.eq("臺南市"))
                        .list();
                break;
            case 15:
                mWeatherEntryList= mWeatherDao.queryBuilder()
                        .where(weatherEntryDao.Properties.CityPics.eq("高雄市"))
                        .list();
                break;
            case 16:
                mWeatherEntryList= mWeatherDao.queryBuilder()
                        .where(weatherEntryDao.Properties.CityPics.eq("屏東縣"))
                        .list();
                break;
            case 17:
                mWeatherEntryList= mWeatherDao.queryBuilder()
                        .where(weatherEntryDao.Properties.CityPics.eq("宜蘭縣"))
                        .list();
                break;
            case 18:
                mWeatherEntryList= mWeatherDao.queryBuilder()
                        .where(weatherEntryDao.Properties.CityPics.eq("花蓮縣"))
                        .list();
                break;
            case 19:
                mWeatherEntryList= mWeatherDao.queryBuilder()
                        .where(weatherEntryDao.Properties.CityPics.eq("臺東縣"))
                        .list();
                break;
            case 20:
                mWeatherEntryList= mWeatherDao.queryBuilder()
                        .where(weatherEntryDao.Properties.CityPics.eq("連江縣"))
                        .list();
                break;
            case 21:
                mWeatherEntryList= mWeatherDao.queryBuilder()
                        .where(weatherEntryDao.Properties.CityPics.eq("金門縣"))
                        .list();
                break;
            case 22:
                mWeatherEntryList= mWeatherDao.queryBuilder()
                        .where(weatherEntryDao.Properties.CityPics.eq("澎湖縣"))
                        .list();
                break;

        }

        return mWeatherEntryList;

    }


    //非同步方式查詢資料庫
    class queryDB extends AsyncTask<String,Void,String> {

        @Override
        protected String doInBackground(String... strings) {

            DBtools dBtools = DBtools.Instance(Navigation_BaseActivity.this);
            mWeatherDao = dBtools.getCityDao();
            mWeatherEntryList = mWeatherDao.loadAll();

            queryDBData();//查詢資料庫並顯示各個縣市AQI值

            MainActivity.adpter.setList(queryDBData());


            return null;
        }
    }


    public void setUpToolBar() {//設置ToolBar
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                DL.openDrawer(GravityCompat.START);
            }
        });
        //設定當使用者點擊ToolBar中的Navigation Icon時，Icon會隨著轉動
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle( this, DL, toolbar,R.string.open_navigation,R.string.close_navigation){
            @Override
            public void onDrawerClosed(View drawerView) {
                super .onDrawerClosed(drawerView);
            }
            @Override
            public void onDrawerOpened(View drawerView) {
                super .onDrawerOpened(drawerView);
            }
        };
        DL.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
    }
}