package com.hublotcloud.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Data
 */
@Entity
@Table(name = "t_data")
@JsonIgnoreProperties(value = { "handler", "hibernateLazyInitializer", "fieldHandler" })
public class Data implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String temperature;

    private String humidity;

    private String pressure;

    private String noraindays;

    private String illuminance;

    private String speed;

    private String direct;

    private String temperature1;

    private String humidity1;

    private String water;

    private String jx;

    private String fq;

    private String x;

    private String y;

    private String z;

    private Date createTime = new Date();


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getPressure() {
        return pressure;
    }

    public void setPressure(String pressure) {
        this.pressure = pressure;
    }

    public String getNoraindays() {
        return noraindays;
    }

    public void setNoraindays(String noraindays) {
        this.noraindays = noraindays;
    }

    public String getIlluminance() {
        return illuminance;
    }

    public void setIlluminance(String illuminance) {
        this.illuminance = illuminance;
    }

    public String getSpeed() {
        return speed;
    }

    public void setSpeed(String speed) {
        this.speed = speed;
    }

    public String getDirect() {
        return direct;
    }

    public void setDirect(String direct) {
        this.direct = direct;
    }

    public String getTemperature1() {
        return temperature1;
    }

    public void setTemperature1(String temperature1) {
        this.temperature1 = temperature1;
    }

    public String getHumidity1() {
        return humidity1;
    }

    public void setHumidity1(String humidity1) {
        this.humidity1 = humidity1;
    }

    public String getWater() {
        return water;
    }

    public void setWater(String water) {
        this.water = water;
    }

    public String getJx() {
        return jx;
    }

    public void setJx(String jx) {
        this.jx = jx;
    }

    public String getFq() {
        return fq;
    }

    public void setFq(String fq) {
        this.fq = fq;
    }

    public String getX() {
        return x;
    }

    public void setX(String x) {
        this.x = x;
    }

    public String getY() {
        return y;
    }

    public void setY(String y) {
        this.y = y;
    }

    public String getZ() {
        return z;
    }

    public void setZ(String z) {
        this.z = z;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}
