/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mscs_710l.systemantics.pojo;

/**
 *
 * @author Siva Chintapalli
 */
public class NetworkStats {

  int NI_PID;
  String NI_User;
  String NI_Protocal;
  String NI_Program;
  double NI_BWSent;
  double NI_BWReceived;
  String NI_Status;

  public String getNI_Protocal() {
    return NI_Protocal;
  }

  public void setNI_Protocal(String NI_Protocal) {
    this.NI_Protocal = NI_Protocal;
  }

  public int getNI_PID() {
    return NI_PID;
  }

  public void setNI_PID(int NI_PID) {
    this.NI_PID = NI_PID;
  }

  public String getNI_User() {
    return NI_User;
  }

  public void setNI_User(String NI_User) {
    this.NI_User = NI_User;
  }

  public String getNI_Program() {
    return NI_Program;
  }

  public void setNI_Program(String NI_Program) {
    this.NI_Program = NI_Program;
  }

  public double getNI_BWSent() {
    return NI_BWSent;
  }

  public void setNI_BWSent(double NI_BWSent) {
    this.NI_BWSent = NI_BWSent;
  }

  public double getNI_BWReceived() {
    return NI_BWReceived;
  }

  public void setNI_BWReceived(double NI_BWReceived) {
    this.NI_BWReceived = NI_BWReceived;
  }

  public String getNI_Status() {
    return NI_Status;
  }

  public void setNI_Status(String NI_Status) {
    this.NI_Status = NI_Status;
  }
  
}
