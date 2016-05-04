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
package com.mscs_710l.systemantics.db;

import com.mscs_710l.systemantics.pojo.FreeMemory;
import com.mscs_710l.systemantics.pojo.IOStats;
import com.mscs_710l.systemantics.pojo.NetworkStats;
import com.mscs_710l.systemantics.pojo.ProcessInfo;
import com.mscs_710l.systemantics.pojo.VirtualDiskInfo;
import com.mscs_710l.systemantics.pojo.VirtualMemoryStats;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * SystemanticsDb
 *
 * This class implements functions which create data bases for storing CPU
 * Information, Disc Information, IO Statistics, Memory Statistics, Network
 * Statistics, Virtual Memory Statistics. Systematically all the information is
 * logged into the database and displayed in regular time intervals.
 */
public class SystemanticsDb {

  private static final Logger LOGGER = LoggerFactory.getLogger(SystemanticsDb.class);
  private static Connection c;
  private static Statement stmt;
  private String msg = "";
  private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd HH:MM");

  /**
   * saveSystematic
   *
   */
  public static void saveSystematic() {
    LOGGER.debug("SystemanticsDb: saveSystematic(): Starts");
    try {
      Class.forName("org.sqlite.JDBC");
      c = DriverManager.getConnection("jdbc:sqlite:Systemantics.db");
      //........................
      String f = "/root/Projects/Metrics-Collector/SySTemantics/Scripts/CreateTableScripts.sql";
      stmt = c.createStatement();
      System.out.println(stmt.getResultSet());
      boolean executeDBSCripts;
      executeDBSCripts = executeDBScripts(f, stmt);
    } catch (ClassNotFoundException | SQLException | IOException e) {
      System.err.println(e.getClass().getName() + ": " + e.getMessage());
      LOGGER.error("SystemanticsDb: exception occured at saveSystematic() " + e.getMessage());
    }
    LOGGER.info("SystemanticsDb: Opened database successfully");
    LOGGER.debug("SystemanticsDb: saveSystematic(): Ends");
  }

  /**
   * executeDBScripts
   *
   * @param aSQLScriptFilePath
   * @param stmt
   * @returns isScriptExecuted
   * @throws IOException
   * @throws SQLException
   */
  private static boolean executeDBScripts(String aSQLScriptFilePath, Statement stmt)
    throws IOException, SQLException {
    LOGGER.debug("SystemanticsDb: executeDBScripts(): Starts");
    boolean isScriptExecuted = false;
    try {
      BufferedReader in = new BufferedReader(new FileReader(aSQLScriptFilePath));
      String str;
      StringBuilder sb = new StringBuilder();
      while ((str = in.readLine()) != null) {
        sb.append(str).append("\n ");
      }
      in.close();
      int i = stmt.executeUpdate(sb.toString());
      isScriptExecuted = true;
    } catch (IOException | SQLException e) {
      System.err.println("SystemanticsDb: Failed to Execute" + aSQLScriptFilePath + ". The error is" + e.getMessage());
    }
    LOGGER.debug("SystemanticsDb: executeDBScripts(): Ends");
    return isScriptExecuted;
  }

  /**
   * saveFreeMemory
   *
   * @param fmList
   * @return msg
   */
  public String saveFreeMemory(List<FreeMemory> fmList) {
    try {
      LOGGER.debug("SystemanticsDb: saveFreeMemory(): Starts");
      //..................................
      Class.forName("org.sqlite.JDBC");
      //..................................
      c = DriverManager.getConnection("jdbc:sqlite:Systemantics.db");
      Date date = new Date();
      String d = dateFormat.format(date);
      for (int i = 0; i < fmList.size(); i++) {
        FreeMemory fm = fmList.get(i);
        //..................
       String query = "INSERT INTO tblFreeMemory(FM_NAME,FM_TOTAL,FM_USED,FM_SHARED,FM_BUFFCACHE,FM_AVAILABLE,FM_DATE)" + "values(?,?,?,?,?,?,?)";
        PreparedStatement pStmt = c.prepareStatement(query);
        pStmt.setString(1, fm.getName());
        pStmt.setInt(2, fm.getTotal());
        pStmt.setInt(3, fm.getUsed());
        pStmt.setInt(4, fm.getShared());
        pStmt.setInt(5, fm.getBuff_cache());
        pStmt.setInt(6, fm.getAvailable());
        pStmt.setString(7, d);
        pStmt.executeUpdate();
      }
      msg = "Success";
    } catch (SQLException | ClassNotFoundException e) {
      System.err.println("SystemanticsDb: an exception Occured at saveFreeMemory()");
      System.err.println(e.getMessage());
      msg = e.getMessage();
    }
    LOGGER.debug("SystemanticsDb: saveFreeMemory(): Ends");
    return msg;
  }

  /**
   * saveProcessInfo
   *
   * @param processInfosList
   * @return msg
   */
  public String saveProcessInfo(List<ProcessInfo> processInfosList) {
    try {
      LOGGER.debug("SystemanticsDb: saveProcessInfo(): Starts");
      Class.forName("org.sqlite.JDBC");
      c = DriverManager.getConnection("jdbc:sqlite:Systemantics.db");
      SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd HH:MM");
      Date date = new Date();
      String d = dateFormat.format(date);
//            String d = date.toString();
      for (int i = 0; i < processInfosList.size(); i++) {
        ProcessInfo processInfo = processInfosList.get(i);
        //...................
        String query = "INSERT INTO tblProcessInfo(PI_PID,PI_Username,PI_Priority,"
          + "PI_Nice,PI_Virtual,PI_Res,PI_Shared,PI_Status,"
          + "PI_PerctCpuUsage,PI_PerctMemUsage,PI_TIME,PI_Command,PI_Date)"
          + "values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement pStmt = c.prepareStatement(query);
        pStmt.setInt(1, processInfo.getPI_PID());
        pStmt.setString(2, processInfo.getPI_Username());
        pStmt.setString(3, processInfo.getPI_Priority());
        pStmt.setInt(4, processInfo.getPI_Nice());
        pStmt.setInt(5, processInfo.getPI_Virtual());
        pStmt.setString(6, processInfo.getPI_Res());
        pStmt.setInt(7, processInfo.getPI_Shared());
        pStmt.setString(8, processInfo.getPI_Status());
        pStmt.setDouble(9, processInfo.getPI_PerctCpuUsage());
        pStmt.setDouble(10, processInfo.getPI_PerctMemUsage());
        pStmt.setString(11, processInfo.getPI_TIME());
        pStmt.setString(12, processInfo.getPI_Command());
        pStmt.setString(13, d);
        pStmt.executeUpdate();
      }
      msg = "Success";
    } catch (SQLException | ClassNotFoundException e) {
      System.err.println("SystemanticsDb: an exception Occured at saveProcessInfo()");
      System.err.println(e.getMessage());
      msg = e.getMessage();
    }
    LOGGER.debug("SystemanticsDb: saveProcessInfo(): Ends");
    return msg;
  }

  /**
   * saveNetworkStats
   *
   * @param networkStatList
   * @return msg
   */
  public String saveNetworkStats(List<NetworkStats> networkStatList) {
    String msg = "";
    try {
      LOGGER.debug("SystemanticsDb: saveNetworkStats(): Starts");
      Class.forName("org.sqlite.JDBC");
      c = DriverManager.getConnection("jdbc:sqlite:Systemantics.db");
      Date date = new Date();
      String d = dateFormat.format(date);
      for (int i = 0; i < networkStatList.size(); i++) {
        NetworkStats networkStats = networkStatList.get(i);
        //.....................
        String query = "INSERT INTO tblNetworkInfo(NI_PID,NI_PROTOCOL,"
          + "NI_USER,NI_PROGRAM,NI_BWSENT,NI_BWRECEIVED,NI_STATUS,NI_DATE)"
          + "values(?,?,?,?,?,?,?,?)";
        PreparedStatement pStmt = c.prepareStatement(query);
        pStmt.setInt(1, networkStats.getNI_PID());
        pStmt.setString(2, networkStats.getNI_Protocal());
        pStmt.setString(3, networkStats.getNI_User());
        pStmt.setString(4, networkStats.getNI_Program());
        pStmt.setDouble(5, networkStats.getNI_BWSent());
        pStmt.setDouble(6, networkStats.getNI_BWReceived());
        pStmt.setString(7, networkStats.getNI_Status());
        pStmt.setString(8, d);
        pStmt.executeUpdate();
      }
      msg = "Success";
    } catch (SQLException | ClassNotFoundException e) {
      System.err.println("SystemanticsDb: an exception Occured at saveNetworkStats()");
      System.err.println(e.getMessage());
      msg = e.getMessage();
    }
    LOGGER.debug("SystemanticsDb: saveNetworkStats(): Ends");
    return msg;
  }

  /**
   * saveVMStats
   *
   * @param virtualMemoryStatList
   * @return msg
   */
  public String saveVMStats(List<VirtualMemoryStats> virtualMemoryStatList) {
    try {
      LOGGER.debug("SystemanticsDb: saveVMStats(): Starts");
      Class.forName("org.sqlite.JDBC");
      c = DriverManager.getConnection("jdbc:sqlite:Systemantics.db");
      Date date = new Date();
      String d = dateFormat.format(date);
      for (int i = 0; i < virtualMemoryStatList.size(); i++) {
        VirtualMemoryStats vmStats = virtualMemoryStatList.get(i);
        //......................
        String query = "INSERT INTO tblVMStat(VM_PROCESS_WAIT_TIME,VM_PROCESS_IO_WAIT_TIME,"
          + "VM_SWPDOUT,VM_FREE,VM_BUFFER,VM_CACHE,VM_OSSWAPIN,VM_OSSWAPOUT,VM_BLOCKREAD,"
          + "VM_BLOCKWRITE,VM_INTERRUPTS,VM_CONTXTSWITCHES,VM_CPUNONKERNALMODE,VM_CPUKERNALMODE,"
          + "VM_CPUIDELTIME,VM_CPUWAITIO,VM_TIMESTOLENVM,VM_DATE)"
          + "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement pStmt = c.prepareStatement(query);
        pStmt.setInt(1, vmStats.getVM_PROCESS_WAIT_TIME());
        pStmt.setInt(2, vmStats.getVM_PROCESS_IO_WAIT_TIME());
        pStmt.setInt(3, vmStats.getVM_SWPDOUT());
        pStmt.setInt(4, vmStats.getVM_FREE());
        pStmt.setInt(5, vmStats.getVM_BUFFER());
        pStmt.setInt(6, vmStats.getVM_CACHE());
        pStmt.setInt(7, vmStats.getVM_OSSWAPIN());
        pStmt.setInt(8, vmStats.getVM_OSSWAPOUT());
        pStmt.setInt(9, vmStats.getVM_BLOCKREAD());
        pStmt.setInt(10, vmStats.getVM_BLOCKWRITE());
        pStmt.setInt(11, vmStats.getVM_INTERRUPTS());
        pStmt.setInt(12, vmStats.getVM_CONTXTSWITCHES());
        pStmt.setInt(13, vmStats.getVM_CPUNONKERNALMODE());
        pStmt.setInt(14, vmStats.getVM_CPUKERNALMODE());
        pStmt.setInt(15, vmStats.getVM_CPUIDELTIME());
        pStmt.setInt(16, vmStats.getVM_CPUWAITIO());
        pStmt.setInt(17, vmStats.getVM_TIMESTOLENVM());
        pStmt.setString(18, d);
        pStmt.executeUpdate();
      }
      msg = "Success";
    } catch (SQLException | ClassNotFoundException e) {
      System.err.println("SystemanticsDb: an exception Occured at saveVMStats()");
      System.err.println(e.getMessage());
      msg = "not Success";
    }
    LOGGER.debug("SystemanticsDb: saveVMStats(): Ends");
    return msg;
  }

  /**
   * saveVirtualDiskInfo
   *
   * @param virtualDiskInfoList
   * @return msg
   */
  public String saveVirtualDiskInfo(List<VirtualDiskInfo> virtualDiskInfoList) {
    try {
      LOGGER.debug("SystemanticsDb: saveVirtualDiskInfo(): Starts");
      Class.forName("org.sqlite.JDBC");
      c = DriverManager.getConnection("jdbc:sqlite:Systemantics.db");
      Date date = new Date();
      String d = dateFormat.format(date);
      for (int i = 0; i < virtualDiskInfoList.size(); i++) {
        VirtualDiskInfo virtualDiskInfo = virtualDiskInfoList.get(i);
        //.............................
        String query = "INSERT INTO tblVMStatDisk(VM_DISKNAME,VM_TOTALREADS,VM_MERGEDREADS,"
          + "VM_SECTORSREADS,VM_MSREADS,VM_TOATALWRITES,VM_MERGEDWRITES,VM_SECTORWRITES,VM_MSWRITES,"
          + "VM_CUR,VM_SEC,VM_DATE)"
          + "values(?,?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement pStmt = c.prepareStatement(query);
        pStmt.setString(1, virtualDiskInfo.getVM_DISKNAME());
        pStmt.setInt(2, virtualDiskInfo.getVM_TOTALREADS());
        pStmt.setInt(3, virtualDiskInfo.getVM_MERGEDREADS());
        pStmt.setInt(4, virtualDiskInfo.getVM_SECTORSREADS());
        pStmt.setInt(5, virtualDiskInfo.getVM_MSREADS());
        pStmt.setInt(6, virtualDiskInfo.getVM_TOATALWRITES());
        pStmt.setInt(7, virtualDiskInfo.getVM_MERGEDWRITES());
        pStmt.setInt(8, virtualDiskInfo.getVM_SECTORWRITES());
        pStmt.setInt(9, virtualDiskInfo.getVM_MSWRITES());
        pStmt.setInt(10, virtualDiskInfo.getVM_CUR());
        pStmt.setInt(11, virtualDiskInfo.getVM_SEC());
        pStmt.setString(12, d);
        pStmt.executeUpdate();
      }
      msg = "Success";
    } catch (SQLException | ClassNotFoundException e) {
      System.err.println("SystemanticsDb: an exception Occured at saveVirtualDiskInfo()");
      System.err.println(e.getMessage());
      msg = e.getMessage();
    }
    LOGGER.debug("SystemanticsDb: saveVirtualDiskInfo(): Ends");
    return msg;
  }

  /**
   * saveIOStats
   *
   * @param iOStatsList
   * @return msg
   */
  public String saveIOStats(List<IOStats> iOStatsList) {
    try {
      LOGGER.debug("SystemanticsDb: saveIOStats(): Starts");
      Class.forName("org.sqlite.JDBC");
      c = DriverManager.getConnection("jdbc:sqlite:Systemantics.db");
      Date date = new Date();
      String d = dateFormat.format(date);
      for (int i = 0; i < iOStatsList.size(); i++) {
        IOStats ioStats = iOStatsList.get(i);
        //.............................
        String query = "INSERT INTO tblIOStats(IO_DISKNAME,IO_TRANSFERPERSEC,IO_KB_READS,"
          + "IO_KB_WRITES,IO_TOTALBLOCKSREAD,IO_TOATALBLOCKSWRITES,IO_DATE)"
          + "values(?,?,?,?,?,?,?)";
        PreparedStatement pStmt = c.prepareStatement(query);
        pStmt.setString(1, ioStats.getIO_DISKNAME());
        pStmt.setDouble(2, ioStats.getIO_TRANSFERPERSEC());
        pStmt.setDouble(3, ioStats.getIO_KB_READS());
        pStmt.setDouble(4, ioStats.getIO_KB_WRITES());
        pStmt.setDouble(5, ioStats.getIO_TOTALBLOCKSREAD());
        pStmt.setDouble(6, ioStats.getIO_TOATALBLOCKSWRITES());
        pStmt.setString(7, d);
        pStmt.executeUpdate();
      }
      msg = "Success";
    } catch (SQLException | ClassNotFoundException e) {
      System.err.println("SystemanticsDb: an exception Occured at saveIOStats() ");
      System.err.println(e.getMessage());
      msg = e.getMessage();
    }
    LOGGER.debug("SystemanticsDb: saveIOStats(): Ends");
    return msg;
  }
}
