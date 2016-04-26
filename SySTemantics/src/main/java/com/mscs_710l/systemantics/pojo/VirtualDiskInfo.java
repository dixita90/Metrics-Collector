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
public class VirtualDiskInfo {

  String VM_DISKNAME;
  int VM_TOTALREADS;
  int VM_MERGEDREADS;
  int VM_SECTORSREADS;
  int VM_MSREADS;
  int VM_TOATALWRITES;
  int VM_MERGEDWRITES;
  int VM_SECTORWRITES;
  int VM_MSWRITES;
  int VM_CUR;
  int VM_SEC;

  public String getVM_DISKNAME() {
    return VM_DISKNAME;
  }

  public void setVM_DISKNAME(String VM_DISKNAME) {
    this.VM_DISKNAME = VM_DISKNAME;
  }

  public int getVM_TOTALREADS() {
    return VM_TOTALREADS;
  }

  public void setVM_TOTALREADS(int VM_TOTALREADS) {
    this.VM_TOTALREADS = VM_TOTALREADS;
  }

  public int getVM_MERGEDREADS() {
    return VM_MERGEDREADS;
  }

  public void setVM_MERGEDREADS(int VM_MERGEDREADS) {
    this.VM_MERGEDREADS = VM_MERGEDREADS;
  }

  public int getVM_SECTORSREADS() {
    return VM_SECTORSREADS;
  }

  public void setVM_SECTORSREADS(int VM_SECTORSREADS) {
    this.VM_SECTORSREADS = VM_SECTORSREADS;
  }

  public int getVM_MSREADS() {
    return VM_MSREADS;
  }

  public void setVM_MSREADS(int VM_MSREADS) {
    this.VM_MSREADS = VM_MSREADS;
  }

  public int getVM_TOATALWRITES() {
    return VM_TOATALWRITES;
  }

  public void setVM_TOATALWRITES(int VM_TOATALWRITES) {
    this.VM_TOATALWRITES = VM_TOATALWRITES;
  }

  public int getVM_MERGEDWRITES() {
    return VM_MERGEDWRITES;
  }

  public void setVM_MERGEDWRITES(int VM_MERGEDWRITES) {
    this.VM_MERGEDWRITES = VM_MERGEDWRITES;
  }

  public int getVM_SECTORWRITES() {
    return VM_SECTORWRITES;
  }

  public void setVM_SECTORWRITES(int VM_SECTORWRITES) {
    this.VM_SECTORWRITES = VM_SECTORWRITES;
  }

  public int getVM_MSWRITES() {
    return VM_MSWRITES;
  }

  public void setVM_MSWRITES(int VM_MSWRITES) {
    this.VM_MSWRITES = VM_MSWRITES;
  }

  public int getVM_CUR() {
    return VM_CUR;
  }

  public void setVM_CUR(int VM_CUR) {
    this.VM_CUR = VM_CUR;
  }

  public int getVM_SEC() {
    return VM_SEC;
  }

  public void setVM_SEC(int VM_SEC) {
    this.VM_SEC = VM_SEC;
  }
  
}
