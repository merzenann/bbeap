package com.example.merzensumagaysay.bbeap;

/**
 * Created by merzensumagaysay on 24/10/2018.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SafeExits {

    @SerializedName("exitID")
    @Expose
    private int exitID;

    @SerializedName("exitName")
    @Expose
    private String exitName;

    @SerializedName("iStatus")
    @Expose
    private int iStatus;      //boolean?


    @SerializedName("userID")
    @Expose
    private int userID;

    @SerializedName("instruction")
    @Expose
    private String instruction;


    @SerializedName("value")
    @Expose
    private int value;

    public SafeExits()
    {

    }

    public SafeExits(int exitID, String exitName, int iStatus, int userID, String instruction)
    {
        this.exitID = exitID;
        this.exitName = exitName;
        this.iStatus = iStatus;
        this.userID = userID;
        this.instruction = instruction;

    }


    public int getExitID() {
        return exitID;
    }

    public void setExitID(int exitID) {
        this.exitID = exitID;
    }


    public String getExitName() {
        return exitName;
    }

    public void setExitName(String exitName) {
        this.exitName = exitName;
    }


    public int getiStatus() {
        return iStatus;
    }

    public void setiStatus(int iStatus) {
        this.iStatus = iStatus;
    }


    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }


    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }


    public String getInstruction() {
        return instruction;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }


}
