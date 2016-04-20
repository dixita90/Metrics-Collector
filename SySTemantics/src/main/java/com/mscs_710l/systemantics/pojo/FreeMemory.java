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
public class FreeMemory {
  String name;
  int total;
  int used;
  int shared;
  int buff_cache;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getTotal() {
    return total;
  }

  public void setTotal(int total) {
    this.total = total;
  }

  public int getUsed() {
    return used;
  }

  public void setUsed(int used) {
    this.used = used;
  }

  public int getShared() {
    return shared;
  }

  public void setShared(int shared) {
    this.shared = shared;
  }

  public int getBuff_cache() {
    return buff_cache;
  }

  public void setBuff_cache(int buff_cache) {
    this.buff_cache = buff_cache;
  }

  public int getAvailable() {
    return available;
  }

  public void setAvailable(int available) {
    this.available = available;
  }
  int available;
}
