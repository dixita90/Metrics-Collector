/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * file: IOStats.java
 * author: Siva Chintapalli, Dixita Sheregar, Bhargav Uppalapati.
 * course: MSCS 710
 * Project
 * version: 1.0
 *
 * This file contains Set methods and Get methods.
 */
package com.mscs_710l.systemantics.pojo;

/**
 * IOStats
 */
public class IOStats {

    String IO_DISKNAME;
    double IO_TRANSFERPERSEC;
    double IO_KB_READS;
    double IO_KB_WRITES;
    double IO_TOTALBLOCKSREAD;
    double IO_TOATALBLOCKSWRITES;

    /**
     * getIO_DISKNAME
     * @return IO_DISKNAME
     */
    public String getIO_DISKNAME() {
        return IO_DISKNAME;
    }

    /**
     * setIO_DISKNAME
     * @param IO_DISKNAME
     */
    public void setIO_DISKNAME(String IO_DISKNAME) {
        this.IO_DISKNAME = IO_DISKNAME;
    }

    /**
     * getIO_TRANSFERPERSEC
     * @return IO_TRANSFERPERSEC
     */
    public double getIO_TRANSFERPERSEC() {
        return IO_TRANSFERPERSEC;
    }

    /**
     * setIO_TRANSFERPERSEC
     * @param IO_TRANSFERPERSEC
     */
    public void setIO_TRANSFERPERSEC(double IO_TRANSFERPERSEC) {
        this.IO_TRANSFERPERSEC = IO_TRANSFERPERSEC;
    }

    /**
     * getIO_KB_READS
     * @return IO_KB_READS
     */
    public double getIO_KB_READS() {
        return IO_KB_READS;
    }

    /**
     * setIO_KB_READS
     * @param IO_KB_READS
     */
    public void setIO_KB_READS(double IO_KB_READS) {
        this.IO_KB_READS = IO_KB_READS;
    }

    /**
     * getIO_KB_WRITES
     * @return IO_KB_WRITES
     */
    public double getIO_KB_WRITES() {
        return IO_KB_WRITES;
    }

    /**
     * setIO_KB_WRITES
     * @param IO_KB_WRITES
     */
    public void setIO_KB_WRITES(double IO_KB_WRITES) {
        this.IO_KB_WRITES = IO_KB_WRITES;
    }

    /**
     * getIO_TOTALBLOCKSREAD
     * @return IO_TOTALBLOCKSREAD
     */
    public double getIO_TOTALBLOCKSREAD() {
        return IO_TOTALBLOCKSREAD;
    }

    /**
     * setIO_TOTALBLOCKSREAD
     * @param IO_TOTALBLOCKSREAD
     */
    public void setIO_TOTALBLOCKSREAD(double IO_TOTALBLOCKSREAD) {
        this.IO_TOTALBLOCKSREAD = IO_TOTALBLOCKSREAD;
    }

    /**
     * getIO_TOATALBLOCKSWRITES
     * @return IO_TOATALBLOCKSWRITES
     */
    public double getIO_TOATALBLOCKSWRITES() {
        return IO_TOATALBLOCKSWRITES;
    }

    /**
     * setIO_TOATALBLOCKSWRITES
     * @param IO_TOATALBLOCKSWRITES
     */
    public void setIO_TOATALBLOCKSWRITES(double IO_TOATALBLOCKSWRITES) {
        this.IO_TOATALBLOCKSWRITES = IO_TOATALBLOCKSWRITES;
    }
}
