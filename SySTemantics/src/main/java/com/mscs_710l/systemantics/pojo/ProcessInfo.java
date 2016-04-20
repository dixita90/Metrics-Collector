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
public class ProcessInfo {

  String PI_PID;
  String PI_Username;
  String PI_Priority;
  int PI_Nice;
  int PI_Virtual;
  String PI_Res;
  int PI_Shared;
  String PI_Status;
  double PI_PerctCpuUsage;
  double PI_PerctMemUsage;
  String PI_TIME;
  String PI_Command;

  public String getPI_PID() {
    return PI_PID;
  }

  public void setPI_PID(String PI_PID) {
    this.PI_PID = PI_PID;
  }

  public String getPI_Username() {
    return PI_Username;
  }

  public void setPI_Username(String PI_Username) {
    this.PI_Username = PI_Username;
  }

  public String getPI_Priority() {
    return PI_Priority;
  }

  public void setPI_Priority(String PI_Priority) {
    this.PI_Priority = PI_Priority;
  }

  public int getPI_Nice() {
    return PI_Nice;
  }

  public void setPI_Nice(int PI_Nice) {
    this.PI_Nice = PI_Nice;
  }

  public int getPI_Virtual() {
    return PI_Virtual;
  }

  public void setPI_Virtual(int PI_Virtual) {
    this.PI_Virtual = PI_Virtual;
  }

  public String getPI_Res() {
    return PI_Res;
  }

  public void setPI_Res(String PI_Res) {
    this.PI_Res = PI_Res;
  }

  public int getPI_Shared() {
    return PI_Shared;
  }

  public void setPI_Shared(int PI_Shared) {
    this.PI_Shared = PI_Shared;
  }

  public String getPI_Status() {
    return PI_Status;
  }

  public void setPI_Status(String PI_Status) {
    this.PI_Status = PI_Status;
  }

  public double getPI_PerctCpuUsage() {
    return PI_PerctCpuUsage;
  }

  public void setPI_PerctCpuUsage(double PI_PerctCpuUsage) {
    this.PI_PerctCpuUsage = PI_PerctCpuUsage;
  }

  public double getPI_PerctMemUsage() {
    return PI_PerctMemUsage;
  }

  public void setPI_PerctMemUsage(double PI_PerctMemUsage) {
    this.PI_PerctMemUsage = PI_PerctMemUsage;
  }

  public String getPI_TIME() {
    return PI_TIME;
  }

  public void setPI_TIME(String PI_TIME) {
    this.PI_TIME = PI_TIME;
  }

  
  public String getPI_Command() {
    return PI_Command;
  }

  public void setPI_Command(String PI_Command) {
    this.PI_Command = PI_Command;
  }

}
