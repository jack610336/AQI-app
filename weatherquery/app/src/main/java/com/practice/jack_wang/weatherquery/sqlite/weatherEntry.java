package com.practice.jack_wang.weatherquery.sqlite;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Index;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Jack_Wang on 2017/12/14.
 */

@Entity  //利用這個自動生成schema
public class weatherEntry {

    @Id(autoincrement = true)//設定 sqlite id 自動增長
    private Long id;

    @Property(nameInDb = "CityId")//設定欄位  然後給名稱
    @NotNull// 不能為空
    private String cityId;

    @Property(nameInDb = "CityName")
    @NotNull
    private String cityName;

    @Property(nameInDb = "CityValue")
    private String cityValue;

    @Property(nameInDb = "CityPics")
    private String cityPics;

    @Property(nameInDb = "dateTime")
    private String date;

    @Generated(hash = 262152176)
    public weatherEntry(Long id, @NotNull String cityId, @NotNull String cityName,
            String cityValue, String cityPics, String date) {
        this.id = id;
        this.cityId = cityId;
        this.cityName = cityName;
        this.cityValue = cityValue;
        this.cityPics = cityPics;
        this.date = date;
    }

    @Generated(hash = 938547615)
    public weatherEntry() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCityId() {
        return this.cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return this.cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCityValue() {
        return this.cityValue;
    }

    public void setCityValue(String cityValue) {
        this.cityValue = cityValue;
    }

    public String getCityPics() {
        return this.cityPics;
    }

    public void setCityPics(String cityPics) {
        this.cityPics = cityPics;
    }

    public String getDate() {
        return this.date;
    }

    public void setDate(String date) {
        this.date = date;
    }



}
