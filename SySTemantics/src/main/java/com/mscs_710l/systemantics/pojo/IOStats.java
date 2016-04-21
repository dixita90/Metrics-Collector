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
public class IOStats {

  String IO_DISKNAME;
  double IO_TRANSFERPERSEC;
  double IO_KB_READS;
  double IO_KB_WRITES;
  double IO_TOTALBLOCKSREAD;
  double IO_TOATALBLOCKSWRITES;

  public String getIO_DISKNAME() {
    return IO_DISKNAME;
  }

  public void setIO_DISKNAME(String IO_DISKNAME) {
    this.IO_DISKNAME = IO_DISKNAME;
  }

  public double getIO_TRANSFERPERSEC() {
    return IO_TRANSFERPERSEC;
  }

  public void setIO_TRANSFERPERSEC(double IO_TRANSFERPERSEC) {
    this.IO_TRANSFERPERSEC = IO_TRANSFERPERSEC;
  }

  public double getIO_KB_READS() {
    return IO_KB_READS;
  }

  public void setIO_KB_READS(double IO_KB_READS) {
    this.IO_KB_READS = IO_KB_READS;
  }

  public double getIO_KB_WRITES() {
    return IO_KB_WRITES;
  }

  public void setIO_KB_WRITES(double IO_KB_WRITES) {
    this.IO_KB_WRITES = IO_KB_WRITES;
  }

  public double getIO_TOTALBLOCKSREAD() {
    return IO_TOTALBLOCKSREAD;
  }

  public void setIO_TOTALBLOCKSREAD(double IO_TOTALBLOCKSREAD) {
    this.IO_TOTALBLOCKSREAD = IO_TOTALBLOCKSREAD;
  }

  public double getIO_TOATALBLOCKSWRITES() {
    return IO_TOATALBLOCKSWRITES;
  }

  public void setIO_TOATALBLOCKSWRITES(double IO_TOATALBLOCKSWRITES) {
    this.IO_TOATALBLOCKSWRITES = IO_TOATALBLOCKSWRITES;
  }
  
}
