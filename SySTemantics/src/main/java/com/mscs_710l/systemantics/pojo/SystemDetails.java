/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mscs_710l.systemantics.pojo;

/**
 * file: SystemDetails.java author: Siva Chintapalli, Dixita Sheregar, Bhargav
 * Uppalapati. course: MSCS 710 Project version: 1.0
 *
 * This file contains Set methods and Get methods.
 */
public class SystemDetails {

    String COMP_NAME;
    String SYSTEM_TYPE;
    String PROCESSOR;
    String BYTE_ORDER;

    /**
     * getCOMP_NAME
     *
     * @return COMP_NAME
     */
    public String getCOMP_NAME() {
        return COMP_NAME;
    }

    /**
     * setVM_DISKNAME
     *
     * @param COMP_NAME
     */
    public void setVM_DISKNAME(String COMP_NAME) {
        this.COMP_NAME = COMP_NAME;
    }

    /**
     * getSYSTEM_TYPE
     *
     * @return SYSTEM_TYPE
     */
    public String getSYSTEM_TYPE() {
        return SYSTEM_TYPE;
    }

    /**
     * setSYSTEM_TYPE
     *
     * @param SYSTEM_TYPE
     */
    public void setSYSTEM_TYPE(String SYSTEM_TYPE) {
        this.SYSTEM_TYPE = SYSTEM_TYPE;
    }

    /**
     * getPROCESSOR
     *
     * @return PROCESSOR
     */
    public String getPROCESSOR() {
        return PROCESSOR;
    }

    /**
     * setPROCESSOR
     *
     * @param PROCESSOR
     */
    public void setPROCESSOR(String PROCESSOR) {
        this.PROCESSOR = PROCESSOR;
    }

    /**
     * getBYTE_ORDER
     *
     * @return BYTE_ORDER
     */
    public String getBYTE_ORDER() {
        return BYTE_ORDER;
    }

    /**
     * setBYTE_ORDER
     *
     * @param BYTE_ORDER
     */
    public void setBYTE_ORDER(String BYTE_ORDER) {
        this.BYTE_ORDER = BYTE_ORDER;
    }

}
