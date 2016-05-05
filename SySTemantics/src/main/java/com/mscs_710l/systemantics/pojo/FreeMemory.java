/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * file: FreeMemory.java
 * author: Siva Chintapalli, Dixita Sheregar, Bhargav Uppalapati.
 * course: MSCS 710
 * Project
 * version: 1.0
 *
 * This file contains Set methods and Get methods
 */
package com.mscs_710l.systemantics.pojo;

/**
 * FreeMemory
 */
public class FreeMemory {

    String name;
    int total;
    int used;
    int shared;
    int buff_cache;

    /**
     * getName
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * setName
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * getTotal
     * @return total
     */
    public int getTotal() {
        return total;
    }

    /**
     * setTotal
     * @param total
     */
    public void setTotal(int total) {
        this.total = total;
    }

    /**
     * getUsed
     * @return used
     */
    public int getUsed() {
        return used;
    }

    /**
     * setUsed
     * @param used
     */
    public void setUsed(int used) {
        this.used = used;
    }

    /**
     * getShared
     * @return shared
     */
    public int getShared() {
        return shared;
    }

    /**
     * setShared
     * @param shared
     */
    public void setShared(int shared) {
        this.shared = shared;
    }

    /**
     * getBuff_cache
     * @return buff_cache
     */
    public int getBuff_cache() {
        return buff_cache;
    }

    /**
     * setBuff_cache
     * @param buff_cache
     */
    public void setBuff_cache(int buff_cache) {
        this.buff_cache = buff_cache;
    }

    /**
     * getAvailable
     * @return available
     */
    public int getAvailable() {
        return available;
    }

    /**
     * setAvailable
     * @param available
     */
    public void setAvailable(int available) {
        this.available = available;
    }
    int available;
}
