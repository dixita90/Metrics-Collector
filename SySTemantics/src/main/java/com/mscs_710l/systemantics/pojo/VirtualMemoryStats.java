/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * file: VirtualMemoryStats.java
 * author: Siva Chintapalli, Dixita Sheregar, Bhargav Uppalapati.
 * course: MSCS 710
 * Project
 * version: 1.0
 *
 * This file contains functions which implement ..............
 */
package com.mscs_710l.systemantics.pojo;

/**
 * VirtualMemoryStats
 *
 * This class implements functions which..........
 */
public class VirtualMemoryStats {

    int VM_PROCESS_WAIT_TIME;
    int VM_PROCESS_IO_WAIT_TIME;
    int VM_SWPDOUT;
    int VM_FREE;
    int VM_BUFFER;
    int VM_CACHE;
    int VM_OSSWAPIN;
    int VM_OSSWAPOUT;
    int VM_BLOCKREAD;
    int VM_BLOCKWRITE;
    int VM_INTERRUPTS;
    int VM_CONTXTSWITCHES;
    int VM_CPUNONKERNALMODE;
    int VM_CPUKERNALMODE;
    int VM_CPUIDELTIME;
    int VM_CPUWAITIO;
    int VM_TIMESTOLENVM;

    /**
     * getVM_TIMESTOLENVM
     *
     * @return VM_TIMESTOLENVM
     */
    public int getVM_TIMESTOLENVM() {
        return VM_TIMESTOLENVM;
    }

    /**
     * setVM_TIMESTOLENVM
     *
     * @param VM_TIMESTOLENVM
     */
    public void setVM_TIMESTOLENVM(int VM_TIMESTOLENVM) {
        this.VM_TIMESTOLENVM = VM_TIMESTOLENVM;
    }

    /**
     * getVM_PROCESS_WAIT_TIME
     *
     * @return VM_PROCESS_WAIT_TIME
     */
    public int getVM_PROCESS_WAIT_TIME() {
        return VM_PROCESS_WAIT_TIME;
    }

    /**
     * setVM_PROCESS_WAIT_TIME
     *
     * @param VM_PROCESS_WAIT_TIME
     */
    public void setVM_PROCESS_WAIT_TIME(int VM_PROCESS_WAIT_TIME) {
        this.VM_PROCESS_WAIT_TIME = VM_PROCESS_WAIT_TIME;
    }

    /**
     * getVM_PROCESS_IO_WAIT_TIME
     *
     * @return VM_PROCESS_IO_WAIT_TIME
     */
    public int getVM_PROCESS_IO_WAIT_TIME() {
        return VM_PROCESS_IO_WAIT_TIME;
    }

    /**
     * setVM_PROCESS_IO_WAIT_TIME
     *
     * @param VM_PROCESS_IO_WAIT_TIME
     */
    public void setVM_PROCESS_IO_WAIT_TIME(int VM_PROCESS_IO_WAIT_TIME) {
        this.VM_PROCESS_IO_WAIT_TIME = VM_PROCESS_IO_WAIT_TIME;
    }

    /**
     * getVM_SWPDOUT
     *
     * @return VM_SWPDOUT
     */
    public int getVM_SWPDOUT() {
        return VM_SWPDOUT;
    }

    /**
     * setVM_SWPDOUT
     *
     * @param VM_SWPDOUT
     */
    public void setVM_SWPDOUT(int VM_SWPDOUT) {
        this.VM_SWPDOUT = VM_SWPDOUT;
    }

    /**
     * getVM_FREE
     *
     * @return VM_FREE
     */
    public int getVM_FREE() {
        return VM_FREE;
    }

    /**
     * setVM_FREE
     *
     * @param VM_FREE
     */
    public void setVM_FREE(int VM_FREE) {
        this.VM_FREE = VM_FREE;
    }

    /**
     * getVM_BUFFER
     *
     * @return VM_BUFFER
     */
    public int getVM_BUFFER() {
        return VM_BUFFER;
    }

    /**
     * setVM_BUFFER
     *
     * @param VM_BUFFER
     */
    public void setVM_BUFFER(int VM_BUFFER) {
        this.VM_BUFFER = VM_BUFFER;
    }

    /**
     * getVM_CACHE
     *
     * @return VM_CACHE
     */
    public int getVM_CACHE() {
        return VM_CACHE;
    }

    /**
     * setVM_CACHE
     *
     * @param VM_CACHE
     */
    public void setVM_CACHE(int VM_CACHE) {
        this.VM_CACHE = VM_CACHE;
    }

    /**
     * getVM_OSSWAPIN
     *
     * @return VM_OSSWAPIN
     */
    public int getVM_OSSWAPIN() {
        return VM_OSSWAPIN;
    }

    /**
     * setVM_OSSWAPIN
     *
     * @param VM_OSSWAPIN
     */
    public void setVM_OSSWAPIN(int VM_OSSWAPIN) {
        this.VM_OSSWAPIN = VM_OSSWAPIN;
    }

    /**
     * getVM_OSSWAPOUT
     *
     * @return VM_OSSWAPOUT
     */
    public int getVM_OSSWAPOUT() {
        return VM_OSSWAPOUT;
    }

    /**
     * setVM_OSSWAPOUT
     *
     * @param VM_OSSWAPOUT
     */
    public void setVM_OSSWAPOUT(int VM_OSSWAPOUT) {
        this.VM_OSSWAPOUT = VM_OSSWAPOUT;
    }

    /**
     * getVM_BLOCKREAD
     *
     * @return VM_BLOCKREAD
     */
    public int getVM_BLOCKREAD() {
        return VM_BLOCKREAD;
    }

    /**
     * setVM_BLOCKREAD
     *
     * @param VM_BLOCKREAD
     */
    public void setVM_BLOCKREAD(int VM_BLOCKREAD) {
        this.VM_BLOCKREAD = VM_BLOCKREAD;
    }

    /**
     * getVM_BLOCKWRITE
     *
     * @return VM_BLOCKWRITE
     */
    public int getVM_BLOCKWRITE() {
        return VM_BLOCKWRITE;
    }

    /**
     * setVM_BLOCKWRITE
     *
     * @param VM_BLOCKWRITE
     */
    public void setVM_BLOCKWRITE(int VM_BLOCKWRITE) {
        this.VM_BLOCKWRITE = VM_BLOCKWRITE;
    }

    /**
     * getVM_INTERRUPTS
     *
     * @return VM_INTERRUPTS
     */
    public int getVM_INTERRUPTS() {
        return VM_INTERRUPTS;
    }

    /**
     * setVM_INTERRUPTS
     *
     * @param VM_INTERRUPTS
     */
    public void setVM_INTERRUPTS(int VM_INTERRUPTS) {
        this.VM_INTERRUPTS = VM_INTERRUPTS;
    }

    /**
     * getVM_CONTXTSWITCHES
     *
     * @return VM_CONTXTSWITCHES
     */
    public int getVM_CONTXTSWITCHES() {
        return VM_CONTXTSWITCHES;
    }

    /**
     * setVM_CONTXTSWITCHES
     *
     * @param VM_CONTXTSWITCHES
     */
    public void setVM_CONTXTSWITCHES(int VM_CONTXTSWITCHES) {
        this.VM_CONTXTSWITCHES = VM_CONTXTSWITCHES;
    }

    /**
     * getVM_CPUNONKERNALMODE
     *
     * @return VM_CPUNONKERNALMODE
     */
    public int getVM_CPUNONKERNALMODE() {
        return VM_CPUNONKERNALMODE;
    }

    /**
     * setVM_CPUNONKERNALMODE
     *
     * @param VM_CPUNONKERNALMODE
     */
    public void setVM_CPUNONKERNALMODE(int VM_CPUNONKERNALMODE) {
        this.VM_CPUNONKERNALMODE = VM_CPUNONKERNALMODE;
    }

    /**
     * getVM_CPUKERNALMODE
     *
     * @return VM_CPUKERNALMODE
     */
    public int getVM_CPUKERNALMODE() {
        return VM_CPUKERNALMODE;
    }

    /**
     * setVM_CPUKERNALMODE
     *
     * @param VM_CPUKERNALMODE
     */
    public void setVM_CPUKERNALMODE(int VM_CPUKERNALMODE) {
        this.VM_CPUKERNALMODE = VM_CPUKERNALMODE;
    }

    /**
     * getVM_CPUIDELTIME
     *
     * @return VM_CPUIDELTIME
     */
    public int getVM_CPUIDELTIME() {
        return VM_CPUIDELTIME;
    }

    /**
     * setVM_CPUIDELTIME
     *
     * @param VM_CPUIDELTIME
     */
    public void setVM_CPUIDELTIME(int VM_CPUIDELTIME) {
        this.VM_CPUIDELTIME = VM_CPUIDELTIME;
    }

    /**
     * getVM_CPUWAITIO
     *
     * @return VM_CPUWAITIO
     */
    public int getVM_CPUWAITIO() {
        return VM_CPUWAITIO;
    }

    /**
     * setVM_CPUWAITIO
     *
     * @param VM_CPUWAITIO
     */
    public void setVM_CPUWAITIO(int VM_CPUWAITIO) {
        this.VM_CPUWAITIO = VM_CPUWAITIO;
    }

}
