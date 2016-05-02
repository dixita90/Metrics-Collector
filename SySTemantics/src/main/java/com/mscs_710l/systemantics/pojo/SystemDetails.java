/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mscs_710l.systemantics.pojo;

/**
 *
 * @author dixita
 */
public class SystemDetails {
    
    String COMP_NAME;
    String SYSTEM_TYPE;
    String PROCESSOR;
    String BYTE_ORDER;
    
    /**
     * 
     * @return 
     */
    public String getCOMP_NAME() {
        return COMP_NAME;
    }

   
    public void setVM_DISKNAME(String COMP_NAME) {
        this.COMP_NAME = COMP_NAME;
    }
    
    public String getSYSTEM_TYPE() {
        return SYSTEM_TYPE;
    }

   
    public void setSYSTEM_TYPE(String SYSTEM_TYPE) {
        this.SYSTEM_TYPE = SYSTEM_TYPE;
    }
    
     public String getPROCESSOR() {
        return PROCESSOR;
    }

   
    public void setPROCESSOR(String PROCESSOR) {
        this.PROCESSOR = PROCESSOR;
    }
    
     public String getBYTE_ORDER() {
        return BYTE_ORDER;
    }

    public void setBYTE_ORDER(String BYTE_ORDER) {
        this.BYTE_ORDER = BYTE_ORDER;
    }

    
}
