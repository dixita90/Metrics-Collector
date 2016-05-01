/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * file: SystemanticsDb.java
 * author: Siva Chintapalli, Dixita Sheregar, Bhargav Uppalapati.
 * course: MSCS 710
 * Project
 * version: 1.0
 *
 * This file contains functions which implement system Metrics Data Base.
 */
package com.mscs_710l.systemantics.pojo;

/**
 * MemoryInfo
 *
 * This class implements functions which..........
 */
public class MemoryInfo {

    float MI_TotalMemory;
    float MI_UsedMemory;
    float MI_ActiveMemory;
    float MI_InactiveMemory;
    float MI_BufferMemory;
    float MI_SwapCache;
    float MI_TotalSwap;
    float MI_SwapUSed;
    float MI_SystemCpuTicks;
    float MI_IdleCpuTicks;
    float MI_IOWaitCpuTicks;
    float MI_PagesPagedIn;
    float MI_PagesPagedOut;
    float MI_PagesSwappedIn;
    float MI_PagesSwappedOut;
    float MI_Interrupts;
    float MI_CpuContextSwitches;
    float MI_BootTime;
}
