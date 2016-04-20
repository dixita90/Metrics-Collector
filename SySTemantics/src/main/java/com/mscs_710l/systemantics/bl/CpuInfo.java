/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mscs_710l.systemantics.bl;

import com.mscs_710l.systemantics.db.SystemanticsDb;
import com.mscs_710l.systemantics.pojo.FreeMemory;
import com.mscs_710l.systemantics.pojo.ProcessInfo;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Siva Chintapalli
 */
public class CpuInfo {

  SystemanticsDb systemanticsDb;

  public void getCpu(String cmd) {

    try {
      // start up the command in child process

      Process child = Runtime.getRuntime().exec(cmd);

      // hook up child process output to parent
      InputStream childOutput = child.getInputStream();
      InputStreamReader parentInput = new InputStreamReader(childOutput);
      BufferedReader readingParentInput = new BufferedReader(parentInput);
      // read the child process output
      String cpuInfo;
      List<String> cpuStatArray = new ArrayList();
      for (int i = 0; i < parentInput.read(); i++) {

        cpuInfo = readingParentInput.readLine();
        cpuStatArray.add(cpuInfo);
        System.out.println(cpuInfo);
      }
      List processInfoList = setProcessSats(cpuStatArray);
      systemanticsDb = new SystemanticsDb();
      String status = systemanticsDb.saveProcessInfo(processInfoList);
      System.out.println(status);
    } catch (Exception e) { // exception thrown
      System.out.println("Command failed!");
    }

  }

  private List<ProcessInfo> setProcessSats(List<String> array) {
    List<ProcessInfo> processInfoList = new ArrayList<>();

for (int i = 7; i < array.size(); i++) {

String val = array.get(i);
      val = val.replace(" ", ",");
      val = val.replace(",,", ",");
      val = val.replace(",,", ",");
      val = val.replace(",,", ",");
      val = val.replace(",,", ",");
      String processValues[] = val.split(",");
      ProcessInfo pInfo = new ProcessInfo();
      for (int j = 0; j < processValues.length; j++) {
        if (j % 13 == 1) {
          pInfo.setPI_PID(processValues[j]);
        }
        if (j % 13 == 2) {
          pInfo.setPI_Username(processValues[j]);
        }
        if (j % 13 == 3) {
          pInfo.setPI_Priority(processValues[j]);
        }
        if (j % 13 == 4) {
          pInfo.setPI_Nice(Integer.parseInt(processValues[j]));
        }
        if (j % 13 == 5) {
          pInfo.setPI_Virtual(Integer.parseInt(processValues[j]));
        }
        if (j % 13 == 6) {
          pInfo.setPI_Res(processValues[j]);
        }
        if (j % 13 == 7) {
          pInfo.setPI_Shared(Integer.parseInt(processValues[j]));
        }
        if (j % 13 == 8) {
          pInfo.setPI_Status(processValues[j]);
        }
        if (j % 13 == 9) {
          pInfo.setPI_PerctCpuUsage(Double.parseDouble(processValues[j]));
        }
        if (j % 13 == 10) {
          pInfo.setPI_PerctMemUsage(Double.parseDouble(processValues[j]));
        }
        if (j % 13 == 11) {
//          java.sql.Time time = java.sql.Time.valueOf(processValues[j]);
          pInfo.setPI_TIME(processValues[j]);
        }
        if (j % 13 == 12) {
          pInfo.setPI_Command(processValues[j]);
        }
      }
      
      processInfoList.add(pInfo);
    }
    return processInfoList;
  }

  public void memoryStats(String cmd) {

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
//      for (int i = 0; i < parentInput.read(); i++) {
//        cpuInfo = readingParentInput.readLine();
//        System.out.println(cpuInfo);
//      }
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
      String msg = systemanticsDb.saveFreeMemory(fmList);
      System.out.println(msg);
    } catch (Exception e) { // exception thrown
      System.out.println("Command failed!");
    }
  }

  private static List<FreeMemory> setvalues(String[] array) {
    List fmList = new ArrayList();
    for (int i = 1; i < array.length; i++) {
      array[i] = array[i].replace(" ", ",");
      array[i] = array[i].replace(",,", ",");
      array[i] = array[i].replace(",,", ",");
      array[i] = array[i].replace(",,", ",");
      array[i] = array[i].replace(",,", ",");
      String memoryValues[] = array[i].split(",");
      int k = 0;
//      while(memoryValues.length!=0){
//        System.out.println(memoryValues[k]);
//        k++;
//      }
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
    return fmList;
  }

  
  public void discInformation() {
    /* Get a list of all filesystem roots on this system */
    File[] roots = File.listRoots();
    /* For each filesystem root, print some info */
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
  }

  public void cpuInformation() {

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

  }
}
