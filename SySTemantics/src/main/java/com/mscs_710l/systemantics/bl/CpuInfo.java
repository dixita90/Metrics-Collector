/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mscs_710l.systemantics.bl;

import com.mscs_710l.systemantics.db.SystemanticsDb;
import com.mscs_710l.systemantics.pojo.FreeMemory;
import com.mscs_710l.systemantics.pojo.IOStats;
import com.mscs_710l.systemantics.pojo.NetworkStats;
import com.mscs_710l.systemantics.pojo.ProcessInfo;
import com.mscs_710l.systemantics.pojo.VirtualDiskInfo;
import com.mscs_710l.systemantics.pojo.VirtualMemoryStats;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Siva Chintapalli
 */
public class CpuInfo {

  SystemanticsDb systemanticsDb;
  private final static Logger LOGGER = LoggerFactory.getLogger(CpuInfo.class);

  /**
   *
   * @param cmd
   * @return
   */
  public String getCpu(String cmd) {
    LOGGER.debug("CpuInfo getCpu(): starts");
    String status = "";
    try {
      if (cmd.equals("top -b")) {
        // start up the command in child process
        Process child = Runtime.getRuntime().exec(cmd);
        BufferedReader input = new BufferedReader(new InputStreamReader(child.getInputStream()));
        String cpuInfo;
        List<String> cpuStatArray = new ArrayList();
        for (int i = 0; i < input.read(); i++) {
          cpuInfo = input.readLine();
          cpuStatArray.add(cpuInfo);
          System.out.println(cpuInfo);
        }
        List processInfoList = setProcessSats(cpuStatArray);
        systemanticsDb = new SystemanticsDb();
        status = systemanticsDb.saveProcessInfo(processInfoList);
//      System.out.println(status);
      }
      else{
        LOGGER.error("invalid command");
      }
    } catch (Exception e) { // exception thrown
//      System.out.println("Command failed!");
      LOGGER.error("exception occured"+e.getMessage());
    }
    LOGGER.debug("CpuInfo getCpu(): ends");
    return status;
  }

  /**
   *
   * @param array
   * @return
   */
  private List<ProcessInfo> setProcessSats(List<String> array) {
    LOGGER.debug("CpuInfo  setProcessStats(): Starts");
    List<ProcessInfo> processInfoList = new ArrayList<>();

    for (int i = 10; i < array.size(); i++) {

      String val = array.get(i);
      val = val.replace(" ", ",");
      val = val.replace(",,", ",");
      val = val.replace(",,", ",");
      val = val.replace(",,", ",");
      val = val.replace(",,", ",");
      val = val.replace(",,", ",");
      val = val.replace(",,", ",");
      val = val.replace(",,", ",");
      String processValues[] = val.split(",");
      ProcessInfo pInfo = new ProcessInfo();
      for (int j = 0; j < processValues.length; j++) {
        if (j % processValues.length == 0) {
          if (processValues[0].equals("")) {
            j = j + 1;
          }
          pInfo.setPI_PID(Integer.parseInt(processValues[j]));
        }
        if (j % processValues.length == 1) {
          if (processValues[0].equals("")) {
            j = j + 1;
          }
          pInfo.setPI_Username(processValues[j]);
        }
        if (j % processValues.length == 2) {
          if (processValues[0].equals("")) {
            j = j + 1;
          }
          pInfo.setPI_Priority(processValues[j]);
        }
        if (j % processValues.length == 3) {
          if (processValues[0].equals("")) {
            j = j + 1;
          }
          pInfo.setPI_Nice(Integer.parseInt(processValues[j]));
        }
        if (j % processValues.length == 4) {
          if (processValues[0].equals("")) {
            j = j + 1;
          }
          pInfo.setPI_Virtual(Integer.parseInt(processValues[j]));
        }
        if (j % processValues.length == 5) {
          if (processValues[0].equals("")) {
            j = j + 1;
          }
          pInfo.setPI_Res(processValues[j]);
        }
        if (j % processValues.length == 6) {
          if (processValues[0].equals("")) {
            j = j + 1;
          }
          pInfo.setPI_Shared(Integer.parseInt(processValues[j]));
        }
        if (j % processValues.length == 7) {
          if (processValues[0].equals("")) {
            j = j + 1;
          }
          pInfo.setPI_Status(processValues[j]);
        }
        if (j % processValues.length == 8) {
          if (processValues[0].equals("")) {
            j = j + 1;
          }
          pInfo.setPI_PerctCpuUsage(Double.parseDouble(processValues[j]));
        }
        if (j % processValues.length == 9) {
          if (processValues[0].equals("")) {
            j = j + 1;
          }
          pInfo.setPI_PerctMemUsage(Double.parseDouble(processValues[j]));
        }
        if (j % processValues.length == 10) {
//          java.sql.Time time = java.sql.Time.valueOf(processValues[j]);
          if (processValues[0].equals("")) {
            j = j + 1;
          }
          pInfo.setPI_TIME(processValues[j]);
        }
        if (j % processValues.length == 11) {
          if (processValues[0].equals("")) {
            j = j + 1;
          }
          pInfo.setPI_Command(processValues[j]);
        }
      }

      processInfoList.add(pInfo);
    }
    LOGGER.debug("CpuInfo  setProcessStats(): ends");
    return processInfoList;
  }

  /**
   *
   * @param cmd
   * @return
   */
  public String memoryStats(String cmd) {
    LOGGER.debug("CpuInfo  memoryStats(): Starts");
    String status = "";
    try {
      // start up the command in child process

      Process child = Runtime.getRuntime().exec(cmd);

      // hook up child process output to parent
      InputStream childOutput = child.getInputStream();
      InputStreamReader parentInput = new InputStreamReader(childOutput);
      BufferedReader readingParentInput = new BufferedReader(parentInput);

      // read the child process output
      String cpuInfo;
      String array[] = new String[3];
      parentInput = new InputStreamReader(childOutput);
      readingParentInput = new BufferedReader(parentInput);
      int i = 0;
      while ((cpuInfo = readingParentInput.readLine()) != null) {
        array[i] = cpuInfo;
//        System.out.println(cpuInfo);
        i++;
      }
      List fmList = setvalues(array);
      systemanticsDb = new SystemanticsDb();
      status = systemanticsDb.saveFreeMemory(fmList);
//      System.out.println(status);
    } catch (Exception e) { // exception thrown
//      System.out.println("Command failed!");
      LOGGER.error("error message" + e.getMessage());
    }
    LOGGER.debug("CpuInfo  memoryStats(): ends");
    return status;
  }

  /**
   *
   * @param array
   * @return
   */
  private static List<FreeMemory> setvalues(String[] array) {
    LOGGER.debug("CpuInfo  setvalues(): Start");
    List fmList = new ArrayList();
    for (int i = 1; i < array.length; i++) {
      array[i] = array[i].replace(" ", ",");
      array[i] = array[i].replace(",,", ",");
      array[i] = array[i].replace(",,", ",");
      array[i] = array[i].replace(",,", ",");
      array[i] = array[i].replace(",,", ",");
      String memoryValues[] = array[i].split(",");
      FreeMemory fm = new FreeMemory();
      for (int j = 0; j < memoryValues.length; j++) {
        if (j % 7 == 0) {

          fm.setName(memoryValues[j]);
        }
        if (j % 7 == 1) {
          fm.setTotal(Integer.parseInt(memoryValues[j]));
        }
        if (j % 7 == 2) {
          fm.setUsed(Integer.parseInt(memoryValues[j]));
        }
        if (j % 7 == 4) {
          fm.setShared(Integer.parseInt(memoryValues[j]));
        }
        if (j % 7 == 5) {
          fm.setBuff_cache(Integer.parseInt(memoryValues[j]));
        }
        if (j % 7 == 6) {
          fm.setAvailable(Integer.parseInt(memoryValues[j]));
        }
      }
      fmList.add(fm);
    }
    LOGGER.debug("CpuInfo  setvalues(): ends");
    return fmList;
  }

  /**
   *
   * @param cmd
   * @return
   */
  public String virtualMemoryStats(String cmd) {
    LOGGER.debug("CpuInfo  virtualMemoryStats(): Start");
    String status = "";
    try {
      // start up the command in child process
      Process child = Runtime.getRuntime().exec(cmd);
      // hook up child process output to parent
      InputStream childOutput = child.getInputStream();
      InputStreamReader parentInput = new InputStreamReader(childOutput);
      BufferedReader readingParentInput = new BufferedReader(parentInput);

      // read the child process output
      String cpuInfo;
      List<String> vMStat = new ArrayList();
      for (int i = 0; i < readingParentInput.read(); i++) {

        cpuInfo = readingParentInput.readLine();
        vMStat.add(cpuInfo);
//        System.out.println(cpuInfo);
      }
      List vMList = setVMStats(vMStat);
      systemanticsDb = new SystemanticsDb();
      status = systemanticsDb.saveVMStats(vMList);
//      System.out.println(status);
    } catch (Exception e) { // exception thrown
      LOGGER.debug("CpuInfo error" + e.getMessage());
//      System.out.println("Command failed!");
    }
    LOGGER.debug("CpuInfo  virtualMemoryStats(): ends");
    return status;
  }

  /**
   *
   * @param vMStatList
   * @return
   */
  private List<VirtualMemoryStats> setVMStats(List<String> vMStatList) {
    LOGGER.debug("CpuInfo  setVMStats(): starts");
    List<VirtualMemoryStats> virtualMemoryStatsList = new ArrayList<>();
    for (int i = 2; i < vMStatList.size(); i++) {
      String val = vMStatList.get(i);
      val = val.replace(" ", ",");
      val = val.replace(",,", ",");
      val = val.replace(",,", ",");
      val = val.replace(",,", ",");
      val = val.replace(",,", ",");
      val = val.replace(",,", ",");
      val = val.replace(",,", ",");
      val = val.replace(",,", ",");
      String vMValues[] = val.split(",");
      VirtualMemoryStats vms = new VirtualMemoryStats();
      for (int j = 0; j < vMValues.length; j++) {
        if (j % vMValues.length == 0) {
          vms.setVM_PROCESS_WAIT_TIME(Integer.parseInt(vMValues[j]));
        }
        if (j % vMValues.length == 1) {
          vms.setVM_PROCESS_IO_WAIT_TIME(Integer.parseInt(vMValues[j]));
        }
        if (j % vMValues.length == 2) {
          vms.setVM_SWPDOUT(Integer.parseInt(vMValues[j]));
        }
        if (j % vMValues.length == 3) {
          vms.setVM_FREE(Integer.parseInt(vMValues[j]));
        }
        if (j % vMValues.length == 4) {
          vms.setVM_BUFFER(Integer.parseInt(vMValues[j]));
        }
        if (j % vMValues.length == 5) {
          vms.setVM_CACHE(Integer.parseInt(vMValues[j]));
        }
        if (j % vMValues.length == 6) {
          vms.setVM_OSSWAPIN(Integer.parseInt(vMValues[j]));
        }
        if (j % vMValues.length == 7) {
          vms.setVM_OSSWAPOUT(Integer.parseInt(vMValues[j]));
        }
        if (j % vMValues.length == 8) {
          vms.setVM_BLOCKREAD(Integer.parseInt(vMValues[j]));
        }
        if (j % vMValues.length == 9) {
          vms.setVM_BLOCKWRITE(Integer.parseInt(vMValues[j]));
        }
        if (j % vMValues.length == 10) {
          vms.setVM_INTERRUPTS(Integer.parseInt(vMValues[j]));
        }
        if (j % vMValues.length == 11) {
          vms.setVM_CONTXTSWITCHES(Integer.parseInt(vMValues[j]));
        }
        if (j % vMValues.length == 12) {
          vms.setVM_CPUNONKERNALMODE(Integer.parseInt(vMValues[j]));
        }
        if (j % vMValues.length == 13) {
          vms.setVM_CPUKERNALMODE(Integer.parseInt(vMValues[j]));
        }
        if (j % vMValues.length == 14) {
          vms.setVM_CPUIDELTIME(Integer.parseInt(vMValues[j]));
        }
        if (j % vMValues.length == 15) {
          vms.setVM_CPUWAITIO(Integer.parseInt(vMValues[j]));
        }
        if (j % vMValues.length == 16) {
          vms.setVM_TIMESTOLENVM(Integer.parseInt(vMValues[j]));
        }
      }
      virtualMemoryStatsList.add(vms);
    }
    LOGGER.debug("CpuInfo  setVMStats(): ends");
    return virtualMemoryStatsList;
  }

  /**
   *
   * @param cmd
   * @return
   */
  public String virtualDiskStats(String cmd) {
    LOGGER.debug("CpuInfo  virtualDiskStats(): Start");
    String status = "";
    try {
      // start up the command in child process
      Process child = Runtime.getRuntime().exec(cmd);
      // hook up child process output to parent
      InputStream childOutput = child.getInputStream();
      InputStreamReader parentInput = new InputStreamReader(childOutput);
      BufferedReader readingParentInput = new BufferedReader(parentInput);

      // read the child process output
      String cpuInfo;
      List<String> vDiskStat = new ArrayList();
      for (int i = 0; i < readingParentInput.read(); i++) {

        cpuInfo = readingParentInput.readLine();
        vDiskStat.add(cpuInfo);
//        System.out.println(cpuInfo);
      }
      List vDiskList = setVDiskStats(vDiskStat);
      systemanticsDb = new SystemanticsDb();
      status = systemanticsDb.saveVirtualDiskInfo(vDiskList);
//      System.out.println(status);
    } catch (Exception e) { // exception thrown
      LOGGER.error("CpuInfo virtualDiskStats()" + e.getMessage());
//      System.out.println("Command failed!");
    }
    LOGGER.debug("CpuInfo  virtualDiskStats(): ends");
    return status;
  }

  /**
   *
   * @param vDiskStatList
   * @return
   */
  private List<VirtualDiskInfo> setVDiskStats(List<String> vDiskStatList) {
    LOGGER.debug("CpuInfo  setVDiskStats(): starts");
    List<VirtualDiskInfo> virtualDiskStatsList = new ArrayList<>();
    for (int i = 2; i < vDiskStatList.size(); i++) {
      String val = vDiskStatList.get(i);
      val = val.replace(" ", ",");
      val = val.replace(",,", ",");
      val = val.replace(",,", ",");
      val = val.replace(",,", ",");
      val = val.replace(",,", ",");
      val = val.replace(",,", ",");
      val = val.replace(",,", ",");
      val = val.replace(",,", ",");
      String vDiskValues[] = val.split(",");
      VirtualDiskInfo vDiskStats = new VirtualDiskInfo();
      for (int j = 0; j < vDiskValues.length; j++) {
        if (j % vDiskValues.length == 0) {
          String name = "";
          if (vDiskValues[j].equals("da")) {
            name = "sda";
          }
          if (vDiskValues[j].equals("m-0")) {
            name = "dm-0";
          }
          if (vDiskValues[j].equals("m-1")) {
            name = "dm-1";
          }
          if (vDiskValues[j].equals("m-2")) {
            name = "dm-2";
          }

          vDiskStats.setVM_DISKNAME(name);
        }
        if (j % vDiskValues.length == 1) {
          vDiskStats.setVM_TOTALREADS(Integer.parseInt(vDiskValues[j]));
        }
        if (j % vDiskValues.length == 2) {
          vDiskStats.setVM_MERGEDREADS(Integer.parseInt(vDiskValues[j]));
        }
        if (j % vDiskValues.length == 3) {
          vDiskStats.setVM_SECTORSREADS(Integer.parseInt(vDiskValues[j]));
        }
        if (j % vDiskValues.length == 4) {
          vDiskStats.setVM_MSREADS(Integer.parseInt(vDiskValues[j]));
        }
        if (j % vDiskValues.length == 5) {
          vDiskStats.setVM_TOATALWRITES(Integer.parseInt(vDiskValues[j]));
        }
        if (j % vDiskValues.length == 6) {
          vDiskStats.setVM_MERGEDWRITES(Integer.parseInt(vDiskValues[j]));
        }
        if (j % vDiskValues.length == 7) {
          vDiskStats.setVM_SECTORWRITES(Integer.parseInt(vDiskValues[j]));
        }
        if (j % vDiskValues.length == 8) {
          vDiskStats.setVM_MSWRITES(Integer.parseInt(vDiskValues[j]));
        }
        if (j % vDiskValues.length == 9) {
          vDiskStats.setVM_CUR(Integer.parseInt(vDiskValues[j]));
        }
        if (j % vDiskValues.length == 10) {
          vDiskStats.setVM_SEC(Integer.parseInt(vDiskValues[j]));
        }
      }
      virtualDiskStatsList.add(vDiskStats);
    }
    LOGGER.debug("CpuInfo  setVMStats(): ends");
    return virtualDiskStatsList;
  }

  /**
   *
   * @param cmd
   * @return
   */
  public String networkStats(String cmd) {
    LOGGER.debug("CpuInfo  networkStats(): starts");
    String status = "";
    try {
      // start up the command in child process

      Process child = Runtime.getRuntime().exec(cmd);
      // hook up child process output to parent
      InputStream childOutput = child.getInputStream();
      InputStreamReader parentInput = new InputStreamReader(childOutput);
      BufferedReader readingParentInput = new BufferedReader(parentInput);

      // read the child process output
      String cpuInfo;
      List<String> networkStat = new ArrayList();
      for (int i = 0; i < readingParentInput.read(); i++) {

        cpuInfo = readingParentInput.readLine();
        networkStat.add(cpuInfo);
//        System.out.println(cpuInfo);
      }
      List vMList = setNetworkStats(networkStat);
      systemanticsDb = new SystemanticsDb();
      status = systemanticsDb.saveNetworkStats(vMList);
//      System.out.println(status);
    } catch (Exception e) { // exception thrown
//      System.out.println("Command failed!");
    }
    LOGGER.debug("CpuInfo  networkStats(): ends");
    return status;
  }

  /**
   *
   * @param array
   * @return
   */
  private List<NetworkStats> setNetworkStats(List<String> array) {
    LOGGER.debug("CpuInfo  setNetworkStats(): starts");
    List<NetworkStats> networkStatList = new ArrayList<>();
    for (int i = 6; i < array.size(); i++) {
      String val = array.get(i);
      val = val.replace(" ", ",");
      val = val.replace(",,", ",");
      val = val.replace(",,", ",");
      val = val.replace(",,", ",");
      val = val.replace(",,", ",");
      val = val.replace(",,", ",");
      val = val.replace(",,", ",");
      val = val.replace(",,", ",");
      val = val.replace(",,", ",");
      String networkStat[] = val.split(",");
      NetworkStats netStat = new NetworkStats();
      for (int j = 0; j < networkStat.length; j++) {
        if (j % networkStat.length == 0) {
          String protocolName = null;
          String name = networkStat[j];
          if ("cp".equalsIgnoreCase(name)) {
            protocolName = "tcp";
          }
          if ("cp6".equals(name)) {
            protocolName = "tcp6";
          }
          if ("dp".equalsIgnoreCase(name)) {
            protocolName = "udp";
          }
          if ("dp6".equals(name)) {
            protocolName = "udp6";
          }
          netStat.setNI_Protocal(protocolName);
        }
        if (j % networkStat.length == 1) {
          netStat.setNI_BWReceived(Double.parseDouble(networkStat[j]));
        }
        if (j % networkStat.length == 2) {
          netStat.setNI_BWSent(Double.parseDouble(networkStat[j]));
        }
        if (j % networkStat.length == 5) {
          netStat.setNI_Status(networkStat[j]);
        }
        if (j % networkStat.length == 6) {
          netStat.setNI_User(networkStat[j]);
        }

        if (j % networkStat.length == 8) {
          String temp[] = new String[3];
          temp[0] = networkStat[j];
          temp = temp[0].split("/");
          if (temp[0].equals("-")) {
            temp[0] = "0";
          }
          netStat.setNI_PID(Integer.parseInt(temp[0]));
          if (temp[0].equals("0")) {
            temp[0] = " ";
          }
          netStat.setNI_Program(temp[0]);

        }
      }

      networkStatList.add(netStat);
    }
    LOGGER.debug("CpuInfo  setNetworkStats(): ends");
    return networkStatList;
  }

  /**
   *
   * @return
   */
  public String discInformation() {
    /* Get a list of all filesystem roots on this system */
    String status = "";
    File[] roots = File.listRoots();
    /* For each filesystem root, print some debug */
    for (File root : roots) {
      System.out.println("\nDisc Statistics in...........................");
      // System.out.println("File system root:"+root.getAbsolutePath());
      System.out.println("Total space (Gigabytes):"
        + root.getTotalSpace() / 1073741824);
      System.out.println("Free space (Gigabytes):" + root.getFreeSpace()
        / 1073741824);
      System.out.println("Usable space (Gigabytes):"
        + root.getUsableSpace() / 1073741824);
    }
    return status;
  }

  /**
   *
   * @return
   */
  public String cpuInformation() {
    String status = "";
    try {
      String cmdlscpu = "lscpu ";
      // start up the command in child process
      String cmd = cmdlscpu;
      Process child = Runtime.getRuntime().exec(cmd);

      // hook up child process output to parent
      InputStream childOutput = child.getInputStream();
      InputStreamReader parentInput = new InputStreamReader(childOutput);
      BufferedReader readingParentInput = new BufferedReader(parentInput);

      // read the child process output
      String cpuInfo;

      cpuInfo = readingParentInput.readLine();
      System.out.println(cpuInfo);

    } catch (Exception e) { // exception thrown
      System.out.println("Command failed!");
    }
    return status;
  }

  /**
   *
   * @param cmd
   * @return
   */
  public String iOStats(String cmd) {
    LOGGER.debug("CpuInfo iOStats(): Starts ");
    String status = "";
    try {
      // start up the command in child process

      Process child = Runtime.getRuntime().exec(cmd);
      // hook up child process output to parent
      InputStream childOutput = child.getInputStream();
      InputStreamReader parentInput = new InputStreamReader(childOutput);
      BufferedReader readingParentInput = new BufferedReader(parentInput);

      // read the child process output
      String cpuInfo;
      List<String> iOStats = new ArrayList();
      for (int i = 0; i < readingParentInput.read(); i++) {
//while((cpuInfo = readingParentInput.readLine())!=null){
        cpuInfo = readingParentInput.readLine();
        iOStats.add(cpuInfo);
//        System.out.println(cpuInfo);
      }
      List iOStatList = setIOStats(iOStats);
      systemanticsDb = new SystemanticsDb();
      status = systemanticsDb.saveIOStats(iOStatList);
//      System.out.println(status);
    } catch (Exception e) { // exception thrown
      LOGGER.error("exception" + e.getMessage());
//      System.out.println("Command failed!");
    }
    LOGGER.debug("CpuInfo iOStats(): Ends ");
    return status;
  }

  /**
   *
   * @param list
   * @return
   */
  private List<IOStats> setIOStats(List<String> list) {
    LOGGER.debug("CpuInfo  setIOStats(): starts");
    List<IOStats> ioStatsList = new ArrayList<>();
    for (int i = 2; i < list.size() - 1; i++) {
      String val = list.get(i);
      val = val.replace(" ", ",");
      val = val.replace(",,", ",");
      val = val.replace(",,", ",");
      val = val.replace(",,", ",");
      val = val.replace(",,", ",");
      val = val.replace(",,", ",");
      val = val.replace(",,", ",");
      val = val.replace(",,", ",");
      val = val.replace(",,", ",");
      String ioStats[] = val.split(",");
      IOStats iOStat = new IOStats();
      for (int j = 0; j < ioStats.length; j++) {
        if (j % ioStats.length == 0) {
          String DiskName = null;
          String name = ioStats[j];
          if ("da".equalsIgnoreCase(name)) {
            DiskName = "sda";
          }
          if ("entos-root".equals(name)) {
            DiskName = "centos-root";
          }
          if ("entos-swap".equalsIgnoreCase(name)) {
            DiskName = "centos-swap";
          }
          if ("entos-home".equals(name)) {
            DiskName = "centos-home";
          }
          iOStat.setIO_DISKNAME(DiskName);
        }
        if (j % ioStats.length == 1) {
          iOStat.setIO_TRANSFERPERSEC(Double.parseDouble(ioStats[j]));
        }
        if (j % ioStats.length == 2) {
          iOStat.setIO_KB_READS(Double.parseDouble(ioStats[j]));
        }
        if (j % ioStats.length == 3) {
          iOStat.setIO_KB_WRITES(Double.parseDouble(ioStats[j]));
        }
        if (j % ioStats.length == 4) {
          iOStat.setIO_TOTALBLOCKSREAD(Double.parseDouble(ioStats[j]));
        }

        if (j % ioStats.length == 5) {
          iOStat.setIO_TOATALBLOCKSWRITES(Double.parseDouble(ioStats[j]));
        }
      }
      ioStatsList.add(iOStat);
    }
    LOGGER.debug("CpuInfo  setNetworkStats(): ends");
    return ioStatsList;
  }
}
