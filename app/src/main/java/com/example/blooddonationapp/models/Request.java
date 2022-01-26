package com.example.blooddonationapp.models;

import java.io.Serializable;

public class Request implements Serializable {
    String userName,userAge,userPhone,userLocation,hospital,unitNeeded,userBloodGrp,uid,uEmail;


    public Request() {
    }

    public Request(String userName, String userAge, String userPhone, String userLocation, String hospital, String unitNeeded, String userBloodGrp, String uid, String uEmail)

    {
        this.userName = userName;
        this.userAge = userAge;
        this.userPhone = userPhone;
        this.userLocation = userLocation;
        this.hospital = hospital;
        this.unitNeeded = unitNeeded;
        this.userBloodGrp = userBloodGrp;
        this.uid = uid;
        this.uEmail = uEmail;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getuEmail() {
        return uEmail;
    }

    public void setuEmail(String uEmail) {
        this.uEmail = uEmail;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserAge() {
        return userAge;
    }

    public void setUserAge(String userAge) {
        this.userAge = userAge;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserLocation() {
        return userLocation;
    }

    public void setUserLocation(String userLocation) {
        this.userLocation = userLocation;
    }

    public String getHospital() {
        return hospital;
    }

    public void setHospital(String hospital) {
        this.hospital = hospital;
    }

    public String getUnitNeeded() {
        return unitNeeded;
    }

    public void setUnitNeeded(String unitNeeded) {
        this.unitNeeded = unitNeeded;
    }

    public String getUserBloodGrp() {
        return userBloodGrp;
    }

    public void setUserBloodGrp(String userBloodGrp) {
        this.userBloodGrp = userBloodGrp;
    }
}