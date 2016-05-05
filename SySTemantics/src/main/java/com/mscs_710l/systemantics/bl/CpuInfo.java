/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * file: CpuInfo.java author: Dixita Sheregar, Siva Chintapalli, Bhargav
 * Uppalapati. course: MSCS 710 Project version: 1.0
 *
 * This file contains functions which implement system Metrics.
 */
package com.mscs_710l.systemantics.bl;

import com.mscs_710l.systemantics.db.SystemanticsDb;
import com.mscs_710l.systemantics.pojo.FreeMemory;
import com.mscs_710l.systemantics.pojo.IOStats;
import com.mscs_710l.systemantics.pojo.NetworkStats;
import com.mscs_710l.systemantics.pojo.ProcessInfo;
import com.mscs_710l.systemantics.pojo.SystemDetails;
import com.mscs_710l.systemantics.pojo.VirtualDiskInfo;
import com.mscs_710l.systemantics.pojo.VirtualMemoryStats;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * CpuInfo
 *
 * This class implements functions which fetch CPU Information, Disc Information
 * IO Statistics, Memory Statistics, Network Statistics, Virtual Memory
 * Statistics. Systematically all the information is logged into the database
 * and displayed in regular time intervals.
 */
public class CpuInfo {

  SystemanticsDb systemanticsDb;
  private final static Logger LOGGER = LoggerFactory.getLogger(CpuInfo.class);
  private final static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd HH:MM");

  /**
   * getCpu
   *
   * This function accepts a shell command and executes it during runtime, in
   * this particular case, top command is executed and the corresponding data is
   * passed to a buffered reader. Necessary data is displayed.
   *
   * @param cmd: top command is the passed parameter.
   * @return processInfoList: Details regarding CPU processes.
   *
   */
  public List<ProcessInfo> getCpu(String cmd) {
    LOGGER.debug("CpuInfo: Fetching CpuInfo, getCpu() : starts");
    String status = "";
    List processInfoList = null;
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
        }
        //The CPU stats are split individually and stored in an processInfoList.
        processInfoList = setProcessSats(cpuStatArray);
        systemanticsDb = new SystemanticsDb();
        status = systemanticsDb.saveProcessInfo(processInfoList);
      } else {
        LOGGER.error("invalid shell command");
      }
      LOGGER.debug("CpuInfo: Fetching CpuInfo, getCpu() : ends");
      return processInfoList;
    } catch (Exception cpuinfo) {
      LOGGER.error("exception occured at getCpu() " + cpuinfo.getMessage());
      return null;
    }
  }

  /**
   * setProcessSats
   *
   * This method is responsible for fetching the data obtained from the top
   * command. The data is refined accordingly by removing spaces between data
   * and replacing the spaces with a comma. Finally the values are assigned to
   * their member variables of the processInfo class.
   *
   * @param array: an array is passes so that CPU processes can be stored.
   * @return processInfoList:contains Details regarding CPU processes.
   */
  private List<ProcessInfo> setProcessSats(List<String> array) {
    LOGGER.debug("CpuInfo:  setProcessStats(): Starts");
    List<ProcessInfo> processInfoList = new ArrayList<>();
    try {
      for (int i = 6; i < array.size(); i++) {
        String val = array.get(i);
        //The replace function will eliminate spaces and seperates elements with comma.
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
            //Sets PID.
            pInfo.setPI_PID(Integer.parseInt(processValues[j]));
          }
          if (j % processValues.length == 1) {
            if (processValues[0].equals("")) {
              j = j + 1;
            }
            //USER is set.
            pInfo.setPI_Username(processValues[j]);
          }
          if (j % processValues.length == 2) {
            if (processValues[0].equals("")) {
              j = j + 1;
            }
            //Setting Prioritys.
            pInfo.setPI_Priority(processValues[j]);
          }
          if (j % processValues.length == 3) {
            if (processValues[0].equals("")) {
              j = j + 1;
            }
            //Setting PI_NI values.
            pInfo.setPI_Nice(Integer.parseInt(processValues[j]));
          }
          if (j % processValues.length == 4) {
            if (processValues[0].equals("")) {
              j = j + 1;
            }
            //Setting PI_VIRT values.
            pInfo.setPI_Virtual(Integer.parseInt(processValues[j]));
          }
          if (j % processValues.length == 5) {
            if (processValues[0].equals("")) {
              j = j + 1;
            }
            //Setting RES values.
            pInfo.setPI_Res(processValues[j]);
          }
          if (j % processValues.length == 6) {
            if (processValues[0].equals("")) {
              j = j + 1;
            }
            //Setting SHR values.
            pInfo.setPI_Shared(Integer.parseInt(processValues[j]));
          }
          if (j % processValues.length == 7) {
            if (processValues[0].equals("")) {
              j = j + 1;
            }
            //Setting S values.
            pInfo.setPI_Status(processValues[j]);
          }
          if (j % processValues.length == 8) {
            if (processValues[0].equals("")) {
              j = j + 1;
            }
            //Setting %CPU values.
            pInfo.setPI_PerctCpuUsage(Double.parseDouble(processValues[j]));
          }
          if (j % processValues.length == 9) {
            if (processValues[0].equals("")) {
              j = j + 1;
            }
            //Setting %MEM values.
            pInfo.setPI_PerctMemUsage(Double.parseDouble(processValues[j]));
          }
          if (j % processValues.length == 10) {
            if (processValues[0].equals("")) {
              j = j + 1;
            }
            //Setting TIME+ values.
            pInfo.setPI_TIME(processValues[j]);
          }
          if (j % processValues.length == 11) {
            if (processValues[0].equals("")) {
              j = j + 1;
            }
            //Setting COMMAND values.
            pInfo.setPI_Command(processValues[j]);
          }
        }
        processInfoList.add(pInfo);
      }
    } catch (Exception setprocessstats) {
      LOGGER.error("exception occured" + setprocessstats.getMessage());
    }
    LOGGER.debug("Class CpuInfo: setProcessStats(): ends");
    return processInfoList;
  }

  /**
   * memoryStats
   *
   * This function accepts a shell command "free -m" and executes it during
   * runtime, in this particular case Memory statistics are obtained, command is
   * executed and the corresponding data is passed to a buffered reader.
   * Necessary data is displayed.
   *
   * @param cmd: "free -m"
   * @return fmList: Values from FreeMemory,which has details regarding memory
   * statistics are passes into a list and returned.
   */
  public List<FreeMemory> memoryStats(String cmd) {
    LOGGER.debug("CpuInfo:  memoryStats(): Starts");
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
        i++;
      }
      //Details regarding memory are passes into fmList and returned.
      List fmList = setvalues(array);
      systemanticsDb = new SystemanticsDb();
      status = systemanticsDb.saveFreeMemory(fmList);
      LOGGER.debug("Class CpuInfo:  memoryStats(): ends");
      return fmList;
    } catch (Exception memorystats) { // exception thrown
      LOGGER.error("error occured at memoryStats()" + memorystats.getMessage());
      return null;
    }
  }

  /**
   * setvalues
   *
   * This method is responsible for fetching the data obtained from the "free
   * -m" command. The data is refined accordingly by removing spaces between
   * data and replacing the spaces with a comma. Finally the values are assigned
   * to their member variables of the FreeMemory class.
   *
   * @param array:an array is passes as parameter so that memory stats can be
   * stored.
   * @return fmList: contains a list regarding Memory statistics.
   */
  private static List<FreeMemory> setvalues(String[] array) {
    LOGGER.debug("Class CpuInfo:  setvalues(): Start");
    List fmList = new ArrayList();
    for (int i = 1; i < array.length; i++) {
      //The replace function will eliminate spaces and seperates elements with comma.
      array[i] = array[i].replace(" ", ",");
      array[i] = array[i].replace(",,", ",");
      array[i] = array[i].replace(",,", ",");
      array[i] = array[i].replace(",,", ",");
      array[i] = array[i].replace(",,", ",");
      String memoryValues[] = array[i].split(",");
      FreeMemory fm = new FreeMemory();
      Date date = new Date();
      String dateconverted = dateFormat.format(date);
      //7 outputs are set after using the "free" command.
      for (int j = 0; j < memoryValues.length; j++) {
        if (j % memoryValues.length == 0) {
          //Name is set.
          fm.setName(memoryValues[j]);
        }
        if (j % memoryValues.length == 1) {
          //The amount of total memory present is set.
          fm.setTotal(Integer.parseInt(memoryValues[j]));
        }
        if (j % memoryValues.length == 2) {
          // Value of Used memory is set.
          fm.setUsed(Integer.parseInt(memoryValues[j]));
        }
        // (j % memoryValues.length == 3)case is not used, as we are not storing free memory statistics.
        if (j % memoryValues.length == 4) {
          //Shared memory is set.
          fm.setShared(Integer.parseInt(memoryValues[j]));
        }
        if (j % memoryValues.length == 5) {
          //value of buffer size is set
          fm.setBuff_cache(Integer.parseInt(memoryValues[j]));
        }
        if (j % memoryValues.length == 6) {
          //value of cached memory is set. 
          fm.setAvailable(Integer.parseInt(memoryValues[j]));
        }
        if (fm.getDate() == null) {
          fm.setDate(dateconverted);
        }
      }
      fmList.add(fm);
    }
    LOGGER.debug("Class: CpuInfo  setvalues(): ends");
    return fmList;
  }

  /**
   * virtualMemoryStats
   *
   * This function accepts a shell command "vmstat -t 1 6" and executes it
   * during runtime, in this particular case Virtual Memory statistics are
   * obtained, command is executed and the corresponding data is passed to a
   * buffered reader. Necessary data is displayed.
   *
   * @param cmd: "vmstat -t 1 6"
   * @return vMList: Values from VirtualMemoryStats,which has details regarding
   * Virtual memory statistics are passes into a list and returned.
   */
  public List<VirtualMemoryStats> virtualMemoryStats(String cmd) {
    LOGGER.debug("CpuInfo: virtualMemoryStats(): Start");
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
      }
      //Details regarding Virtual memory are passes into vMList and returned.
      List vMList = setVMStats(vMStat);
      systemanticsDb = new SystemanticsDb();
      status = systemanticsDb.saveVMStats(vMList);
      LOGGER.debug("CpuInfo: virtualMemoryStats(): ends");
      return vMList;
    } catch (Exception virtualmemorystats) {
      LOGGER.debug("CpuInfo: error occured at virtualMemoryStats() " + virtualmemorystats.getMessage());
      return null;
    }
  }

  /**
   * setVMStats
   *
   * This method is responsible for fetching the data obtained from the "vmstat
   * -t 1 6" command. The data is refined accordingly by removing spaces between
   * data and replacing the spaces with a comma. Finally the values are assigned
   * to their member variables of the VirtualMemoryStats class.
   *
   * @param vMStatList: a list containing virtual memory stats are passes as
   * parameter so that the values are assigned to their member variables.
   *
   * @returns virtualMemoryStatsList: Values from VirtualMemoryStats,which has
   * details regarding Virtual memory statistics are passes into a list and
   * returned.
   */
  private List<VirtualMemoryStats> setVMStats(List<String> vMStatList) {
    LOGGER.debug("CpuInfo: setVMStats(): starts");
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
          //Sets values of Virtual memory processes wait time
          vms.setVM_PROCESS_WAIT_TIME(Integer.parseInt(vMValues[j]));
        }
        if (j % vMValues.length == 1) {
          //Sets values of Virtual memory processes IO wait time
          vms.setVM_PROCESS_IO_WAIT_TIME(Integer.parseInt(vMValues[j]));
        }
        if (j % vMValues.length == 2) {
          //Sets values of SWapped output.
          vms.setVM_SWPDOUT(Integer.parseInt(vMValues[j]));
        }
        if (j % vMValues.length == 3) {
          //Sets values of free memory.
          vms.setVM_FREE(Integer.parseInt(vMValues[j]));
        }
        if (j % vMValues.length == 4) {
          //Sets values of VM Buffer
          vms.setVM_BUFFER(Integer.parseInt(vMValues[j]));
        }
        if (j % vMValues.length == 5) {
          //Sets values of VM cache
          vms.setVM_CACHE(Integer.parseInt(vMValues[j]));
        }
        if (j % vMValues.length == 6) {
          //Sets values of SWAP SI
          vms.setVM_OSSWAPIN(Integer.parseInt(vMValues[j]));
        }
        if (j % vMValues.length == 7) {
          //Sets values of SWAP SO
          vms.setVM_OSSWAPOUT(Integer.parseInt(vMValues[j]));
        }
        if (j % vMValues.length == 8) {
          //Sets values of IO block input.
          vms.setVM_BLOCKREAD(Integer.parseInt(vMValues[j]));
        }
        if (j % vMValues.length == 9) {
          //Sets values of IO block output.
          vms.setVM_BLOCKWRITE(Integer.parseInt(vMValues[j]));
        }
        if (j % vMValues.length == 10) {
          //Setting values of System Interrupt
          vms.setVM_INTERRUPTS(Integer.parseInt(vMValues[j]));
        }
        if (j % vMValues.length == 11) {
          //Setting values of System Context Switches.
          vms.setVM_CONTXTSWITCHES(Integer.parseInt(vMValues[j]));
        }
        if (j % vMValues.length == 12) {
          //Setting values of CPU non kernel mode.
          vms.setVM_CPUNONKERNALMODE(Integer.parseInt(vMValues[j]));
        }
        if (j % vMValues.length == 13) {
          //Setting values of CPU kernel mode.
          vms.setVM_CPUKERNALMODE(Integer.parseInt(vMValues[j]));
        }
        if (j % vMValues.length == 14) {
          //Setting values of CPU idle time.
          vms.setVM_CPUIDELTIME(Integer.parseInt(vMValues[j]));
        }
        if (j % vMValues.length == 15) {
          //Setting values of CPU wait time.
          vms.setVM_CPUWAITIO(Integer.parseInt(vMValues[j]));
        }
        if (j % vMValues.length == 16) {
          //Setting values of CPU time stolen.
          vms.setVM_TIMESTOLENVM(Integer.parseInt(vMValues[j]));
        }
      }
      virtualMemoryStatsList.add(vms);
    }
    LOGGER.debug("CpuInfo: setVMStats(): ends");
    return virtualMemoryStatsList;
  }

  /**
   * virtualDiskStats
   *
   * This function accepts a shell command "vmstat -t -d" and executes it during
   * runtime, in this particular case Virtual Disk statistics are obtained,
   * command is executed and the corresponding data is passed to a buffered
   * reader. Necessary data is displayed.
   *
   * @param cmd:"vmstat -t -d"
   * @return vDiskList: Values from VirtualDiskInfo,which has details regarding
   * Virtual Disk statistics are passes into a list and returned.
   */
  public List<VirtualDiskInfo> virtualDiskStats(String cmd) {
    LOGGER.debug("CpuInfo: virtualDiskStats(): Start");
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
      }
      List vDiskList = setVDiskStats(vDiskStat);
      systemanticsDb = new SystemanticsDb();
      status = systemanticsDb.saveVirtualDiskInfo(vDiskList);
      LOGGER.debug("CpuInfo: virtualDiskStats(): ends");
      return vDiskList;
    } catch (Exception e) {
      LOGGER.error("CpuInfo: error occured at virtualDiskStats()" + e.getMessage());
      return null;
    }
  }

  /**
   * setVDiskStats
   *
   * This method is responsible for fetching the data obtained from the "vmstat
   * -t -d" command. The data is refined accordingly by removing spaces between
   * data and replacing the spaces with a comma. Finally the values are assigned
   * to their member variables of the VirtualDiskInfo class.
   *
   * @param vDiskStatList: a list containing virtual Disk stats are passes as
   * parameter so that the values are assigned to their member variables.
   *
   * @return virtualDiskStatsList: Values from VirtualDiskInfo,which has details
   * regarding Virtual Disk statistics are passes into a list and returned.
   */
  private List<VirtualDiskInfo> setVDiskStats(List<String> vDiskStatList) {
    LOGGER.debug("CpuInfo: setVDiskStats(): starts");
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
    LOGGER.debug("CpuInfo: setVMStats(): ends");
    return virtualDiskStatsList;
  }

  /**
   * networkStats
   *
   * This function accepts a shell command "netstat -e -p -at" and executes it
   * during runtime, in this particular case Network statistics are obtained,
   * command is executed and the corresponding data is passed to a buffered
   * reader. Necessary data is displayed.
   *
   * @param cmd: "netstat -e -p -at"
   * @return vMList: Values from NetworkStats,which has details regarding
   * Network statistics are passes into a list and returned.
   */
  public List<NetworkStats> networkStats(String cmd) {
    LOGGER.debug("CpuInfo: networkStats(): starts");
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
      }
      List vMList;
      if ("netstat -e -p -at".equals(cmd)) {
        vMList = setNetworkStats(networkStat);
      } else {
        vMList = setUdpNetworkStats(networkStat);
      }
      systemanticsDb = new SystemanticsDb();
      status = systemanticsDb.saveNetworkStats(vMList);
      LOGGER.debug("CpuInfo: networkStats(): ends");
      return vMList;
    } catch (Exception e) {
      return null;
    }
  }

  /**
   * setNetworkStats
   *
   * This method is responsible for fetching the data obtained from the "netstat
   * -e -p -at" command. The data is refined accordingly by removing spaces
   * between data and replacing the spaces with a comma. Finally the values are
   * assigned to their member variables of the NetworkStats class.
   *
   * @param array:an array is passed as an parameter so that the value of TCP
   * network stats can be stored.
   *
   * @return networkStatList: Values from NetworkStats,which has details
   * regarding TCP Network statistics are passes into a list and returned.
   */
  private List<NetworkStats> setNetworkStats(List<String> array) {
    LOGGER.debug("CpuInfo: setNetworkStats(): starts");
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
          // Hard coding the protocol name from cp to tcp, for better underatanding.
          if ("cp".equalsIgnoreCase(name)) {
            protocolName = "tcp";
          }
          // Hard coding the protocol name from cp6 to tcp6, for better underatanding.
          if ("cp6".equals(name)) {
            protocolName = "tcp6";
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
        //Here the returned value comes in the form of PID and PRocess name so they are split.
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
    LOGGER.debug("CpuInfo: setNetworkStats(): ends");
    return networkStatList;
  }

  /**
   * setUdpNetworkStats
   *
   * This method is responsible for fetching the data obtained from the "netstat
   * -au -e -p" command. The data is refined accordingly by removing spaces
   * between data and replacing the spaces with a comma. Finally the values are
   * assigned to their member variables of the NetworkStats class.
   *
   * @param array: an array is passed as an parameter so that the value of UDP
   * network stats can be stored.
   *
   * @return networkStatList: Values from NetworkStats,which has details
   * regarding UDP Network statistics are passes into a list and returned.
   */
  private List<NetworkStats> setUdpNetworkStats(List<String> array) {
    LOGGER.debug("CpuInfo: setNetworkStats(): starts");
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
          // Hard coding the protocol name from dp to udp, for better underatanding.
          if ("dp".equalsIgnoreCase(name)) {
            protocolName = "udp";
          }
          // Hard coding the protocol name from dp6 to udp6, for better underatanding.
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
          netStat.setNI_User(networkStat[j]);
        }
        if (j % networkStat.length == 7) {
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
    LOGGER.debug("CpuInfo: setNetworkStats(): ends");
    return networkStatList;
  }

  /**
   * cpuInformation
   *
   * This function accepts a shell command and executes it during runtime, in
   * this particular case, "lscpu" command is executed and the corresponding
   * data is passed to a buffered reader. Necessary data is displayed.
   *
   * @return sysDetails: Values from SystemDetails,which has details regarding
   * CPU statistics are passes into a list and returned.
   */
  public SystemDetails cpuInformation() {
    LOGGER.debug("CpuInfo: cpuInformation(): starts");
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
      SystemDetails sysDetails = new SystemDetails();
      String[] str;
      while ((cpuInfo = readingParentInput.readLine()) != null) {
//                System.out.println(cpuInfo);
        str = cpuInfo.split("\\s+");
        if (cpuInfo.contains("Architecture")) {
          sysDetails.setSYSTEM_TYPE(str[1]);
        } else if (cpuInfo.contains("Byte Order")) {
          sysDetails.setBYTE_ORDER(str[2] + " " + str[3]);
        } else if (cpuInfo.contains("Model name")) {
          String name = str[3];
          for (int count = 4; count < str.length; count++) {
            name = name + str[count];
          }
          sysDetails.setPROCESSOR(name);
        }
      }
      LOGGER.debug("CpuInfo: cpuInformation(): ends");
      return sysDetails;
    } catch (Exception ex) {
      LOGGER.error("CpuInfo: Command failed! at cpuInformation()" + ex.getMessage());
      return null;
    }
  }

  /**
   * iOStats
   *
   * This function accepts a shell command "iostat -d -N" and executes it during
   * runtime, in this particular case IO statistics are obtained, command is
   * executed and the corresponding data is passed to a buffered reader.
   * Necessary data is displayed.
   *
   * @param cmd: "iostat -d -N"
   * @return iOStatList:Values from iOStats,which has details regarding IO
   * statistics are passes into a list and returned.
   */
  public List<IOStats> iOStats(String cmd) {
    LOGGER.debug("CpuInfo: iOStats(): Starts ");
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
        cpuInfo = readingParentInput.readLine();
        iOStats.add(cpuInfo);
      }
      List iOStatList = setIOStats(iOStats);
      systemanticsDb = new SystemanticsDb();
      status = systemanticsDb.saveIOStats(iOStatList);
      LOGGER.debug("CpuInfo: iOStats(): Ends ");
      return iOStatList;
    } catch (Exception iostats) {
      LOGGER.error("CpuInfo: exception occured at iOStats()" + iostats.getMessage());
      return null;
    }
  }

  /**
   * setIOStats
   *
   * This method is responsible for fetching the data obtained from the "iostat
   * -d -N" command. The data is refined accordingly by removing spaces between
   * data and replacing the spaces with a comma. Finally the values are assigned
   * to their member variables of the NetworkStats class.
   *
   * @param list:an list is passed as an parameter so that the values of IO
   * stats can be stored.
   *
   * @return ioStatsList: Values from iOStats,which has details regarding IO
   * statistics are passes into a list and returned.
   */
  private List<IOStats> setIOStats(List<String> list) {
    LOGGER.debug("CpuInfo: setIOStats(): starts");
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
    LOGGER.debug("CpuInfo: setNetworkStats(): ends");
    return ioStatsList;
  }

  /**
   * this method query data from the database of Process information and returns
   * the data to plot the graphs
   *
   * @return processInfo list from the database
   */
  public List<ProcessInfo> fetchingCpuUsage() {
    LOGGER.debug("CpuInfo: fetchingCpuUsage(): Starts ");
    try {
      List<ProcessInfo> ProcessInfoList = new ArrayList();
      systemanticsDb = new SystemanticsDb();
      ProcessInfoList = systemanticsDb.retriveProcessData();
      LOGGER.debug("CpuInfo: fetchingCpuUsage(): Ends ");
      return ProcessInfoList;
    } catch (Exception e) {
      LOGGER.error("CpuInfo: exception occured at fetchingCpuUsage()" + e.getMessage());
      return null;
    }

  }
}
