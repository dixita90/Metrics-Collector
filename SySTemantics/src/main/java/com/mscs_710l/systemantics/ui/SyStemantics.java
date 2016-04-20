package com.mscs_710l.systemantics.ui;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.mscs_710l.systemantics.bl.CpuInfo;
import com.mscs_710l.systemantics.db.SystemanticsDb;
import java.io.IOException;

/**
 *
 * @author Siva Chintapalli
 */
public class SyStemantics {

  static String cmdTop = "top -b";
  static String cmdFreeMemory = "free -m";
  static String cmdMeminfo = "cat /proc/meminfo";
  static String cmdVmStat = "vmstat";
  static String cmdNetStat ="netstat -pt -au";
  static String cmdIostat = "iostat";
  
//  netstat -a | more
  //iostat

  public static void main(String[] args) throws IOException {
    SystemanticsDb.saveSystematic();
    CpuInfo cpuInfo = new CpuInfo();
    cpuInfo.getCpu(cmdTop);
    cpuInfo.memoryStats(cmdFreeMemory);
    cpuInfo.memoryStats(cmdVmStat);
    cpuInfo.getCpu(cmdMeminfo);
    
//     CpuInfo.discInformation();
//     CpuInfo.cpuInformation();

  }
}
