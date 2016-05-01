/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * file: SystemanticsDb.java
 * author: Siva Chintapalli, Dixita Sheregar, Bhargav Uppalapati.
 * course: MSCS 710
 * Project
 * version: 1.0
 *
 * This file contains functions which implement ............
 */
package com.mscs_710l.systemantics.pojo;

/**
 * NetworkStats
 *
 * This class implements functions which..........
 */
public class NetworkStats {

    int NI_PID;
    String NI_User;
    String NI_Protocal;
    String NI_Program;
    double NI_BWSent;
    double NI_BWReceived;
    String NI_Status;

    /**
     * getNI_Protocal
     *
     * @return NI_Protocal
     */
    public String getNI_Protocal() {
        return NI_Protocal;
    }

    /**
     * setNI_Protocal
     *
     * @param NI_Protocal
     */
    public void setNI_Protocal(String NI_Protocal) {
        this.NI_Protocal = NI_Protocal;
    }

    /**
     * getNI_PID
     *
     * @return NI_PID
     */
    public int getNI_PID() {
        return NI_PID;
    }

    /**
     * setNI_PID
     *
     * @param NI_PID
     */
    public void setNI_PID(int NI_PID) {
        this.NI_PID = NI_PID;
    }

    /**
     * getNI_User
     *
     * @return NI_User
     */
    public String getNI_User() {
        return NI_User;
    }

    /**
     * setNI_User
     *
     * @param NI_User
     */
    public void setNI_User(String NI_User) {
        this.NI_User = NI_User;
    }

    /**
     * getNI_Program
     *
     * @return NI_Program
     */
    public String getNI_Program() {
        return NI_Program;
    }

    /**
     * setNI_Program
     *
     * @param NI_Program
     */
    public void setNI_Program(String NI_Program) {
        this.NI_Program = NI_Program;
    }

    /**
     * getNI_BWSent
     *
     * @return NI_BWSent
     */
    public double getNI_BWSent() {
        return NI_BWSent;
    }

    /**
     * setNI_BWSent
     *
     * @param NI_BWSent
     */
    public void setNI_BWSent(double NI_BWSent) {
        this.NI_BWSent = NI_BWSent;
    }

    /**
     * getNI_BWReceived
     *
     * @return NI_BWReceived
     */
    public double getNI_BWReceived() {
        return NI_BWReceived;
    }

    /**
     * setNI_BWReceived
     *
     * @param NI_BWReceived
     */
    public void setNI_BWReceived(double NI_BWReceived) {
        this.NI_BWReceived = NI_BWReceived;
    }

    /**
     * getNI_Status
     *
     * @return NI_Status
     */
    public String getNI_Status() {
        return NI_Status;
    }

    /**
     * setNI_Status
     *
     * @param NI_Status
     */
    public void setNI_Status(String NI_Status) {
        this.NI_Status = NI_Status;
    }

}
