/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mscs_710l.systemantics.db;

import com.mscs_710l.systemantics.pojo.FreeMemory;
import com.mscs_710l.systemantics.pojo.ProcessInfo;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 *
 * @author Siva Chintapalli
 */
public class SystemanticsDb {

  static Connection c;
  static Statement stmt;

  public static void saveSystematic() {

    try {
      Class.forName("org.sqlite.JDBC");
      c = DriverManager.getConnection("jdbc:sqlite:Systemantics.db");
      String f = "/root/Projects/Metrics-Collector/SySTemantics/Scripts/CreateTableScripts.sql";

      stmt = c.createStatement();

      boolean executeDBSCripts;
      executeDBSCripts = executeDBScripts(f, stmt);
    } catch (ClassNotFoundException | SQLException | IOException e) {
      System.err.println(e.getClass().getName() + ": " + e.getMessage());
//      System.exit(0);
    }
    System.out.println("Opened database successfully");
  }

  private static boolean executeDBScripts(String aSQLScriptFilePath, Statement stmt) throws IOException, SQLException {
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
      System.err.println("Failed to Execute" + aSQLScriptFilePath + ". The error is" + e.getMessage());
    }
    return isScriptExecuted;
  }

  public String saveFreeMemory(List<FreeMemory> fmList) {
    String msg = "";
    try {
      Class.forName("org.sqlite.JDBC");
      c = DriverManager.getConnection("jdbc:sqlite:Systemantics.db");

      for (int i = 0; i < fmList.size(); i++) {
        FreeMemory fm = fmList.get(i);
        String query = "INSERT INTO tblFreeMemory(FM_NAME,FM_TOTAL,FM_USED,FM_SHARED,FM_BUFFCACHE,FM_AVAILABLE)" + "values(?,?,?,?,?,?)";
        PreparedStatement pStmt = c.prepareStatement(query);
        pStmt.setString(1, fm.getName());
        pStmt.setInt(2, fm.getTotal());
        pStmt.setInt(3, fm.getUsed());
        pStmt.setInt(4, fm.getShared());
        pStmt.setInt(5, fm.getBuff_cache());
        pStmt.setInt(6, fm.getAvailable());
        pStmt.execute(query);
      }
      msg = "Success";
    } catch (SQLException | ClassNotFoundException e) {
      System.err.println("an exception Occured");
      System.err.println(e.getMessage());
      msg = e.getMessage();
    }

    return msg;
  }

  public String saveProcessInfo(List<ProcessInfo> processInfosList) {
    String msg = "";
    try {
      Class.forName("org.sqlite.JDBC");
      c = DriverManager.getConnection("jdbc:sqlite:Systemantics.db");

      for (int i = 0; i < processInfosList.size(); i++) {
        ProcessInfo processInfo = processInfosList.get(i);
        String query = "INSERT INTO tblProcessInfo(PI_PID,PI_Username,PI_Priority,"
          + "PI_Nice,PI_Virtual,PI_Res,PI_Shared,PI_Status,"
          + "PI_PerctCpuUsage,PI_PerctMemUsage,PI_TIME,PI_Command)"
          + "values(?,?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement pStmt = c.prepareStatement(query);
        pStmt.setString(1, processInfo.getPI_PID());
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
        pStmt.executeUpdate();
      }
      msg = "Success";
    } catch (SQLException | ClassNotFoundException e) {
      System.err.println("an exception Occured");
      System.err.println(e.getMessage());
      msg = e.getMessage();
    }

    return msg;
  }
}
