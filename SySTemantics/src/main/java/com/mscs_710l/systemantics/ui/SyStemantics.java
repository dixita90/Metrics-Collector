package com.mscs_710l.systemantics.ui;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.mscs_710l.systemantics.bl.CpuInfo;
import com.mscs_710l.systemantics.db.SystemanticsDb;
import java.io.IOException;
import org.apache.log4j.PropertyConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Siva Chintapalli
 */
public class SyStemantics {

  static {
    SystemanticsDb.saveSystematic();
  }
  private static final Logger LOGGER = LoggerFactory.getLogger(SyStemantics.class);
  static String cmdTop = "top -b";
  static String cmdFreeMemory = "free -m";
  static String cmdMeminfo = "cat /proc/meminfo";
  static String cmdVmStat = "vmstat -t 1 6";
  static String cmdVDiskStats = "vmstat -t -d";
  static String cmdNetStatTcp = "netstat -e -p -at";
  static String cmdNetStatUdp = "netstat -au";
  static String cmdIostat = "iostat -d -N";

//  netstat -a | more
  //iostat
  public static void main(String[] args) throws IOException {
    PropertyConfigurator.configure("log4j.properties");
    LOGGER.debug("SySTematics main(): starts");
    CpuInfo cpuInfo = new CpuInfo();
    cpuInfo.memoryStats(cmdFreeMemory);
    cpuInfo.virtualMemoryStats(cmdVmStat);
    cpuInfo.virtualDiskStats(cmdVDiskStats);
     cpuInfo.getCpu(cmdTop);
//    cpuInfo.getCpu(cmdMeminfo);
    cpuInfo.networkStats(cmdNetStatTcp);
    cpuInfo.networkStats(cmdNetStatUdp);
   cpuInfo.iOStats(cmdIostat);
//     cpuInfo.discInformation();
//     cpuInfo.cpuInformation();
    LOGGER.debug("SySTematics main(): ends");
  }
}
