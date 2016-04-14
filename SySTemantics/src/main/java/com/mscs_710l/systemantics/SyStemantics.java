package com.mscs_710l.systemantics;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author SIVARAMAKRISHNAPRASA
 */
public class SyStemantics {

  static String cmdTop = "top -b";//"less /proc/meminfo";//
  // static String cmdTop = "top -n 10 -b -d 0.5";
  // returns user CPU usage
  static String cmdMemory = "free -m";
  static String cmdMeminfo = "cat /proc/meminfo";
  static String cmdVmStat = "vmstat";
  static String cmdCPUINFO = "/proc/cpuinfo";
  static String cmdP_STAT = "/proc/stat";
  static String cmdVERSION = "/proc/version";
  static String cmdUPTIME = "/proc/uptime";
  static String cmdLOADAVG = "/proc/loadavg";
  static String cmdNFS = "/proc/net/rpc/nfs";
  static String cmdNFSD = "/proc/net/rpc/nfsd";

  public static void getCpu() {

    try {
      // start up the command in child process
      String cmd = cmdTop;
      Process child = Runtime.getRuntime().exec(cmd);
      Process child1 = Runtime.getRuntime().exec(cmdMemory);

      // hook up child process output to parent
      InputStream childOutput = child.getInputStream();
      InputStreamReader parentInput = new InputStreamReader(childOutput);
      BufferedReader readingParentInput = new BufferedReader(parentInput);

      // read the child process output
      String cpuInfo = "";
      for (int i = 0; i < parentInput.read(); i++) {
        cpuInfo = readingParentInput.readLine();
        System.out.println(cpuInfo);
      }
      childOutput = child1.getInputStream();
      parentInput = new InputStreamReader(childOutput);
      readingParentInput = new BufferedReader(parentInput);
      while ((cpuInfo = readingParentInput.readLine()) != null) {
        System.out.println(cpuInfo);
      }
      int processorsAvailable = Runtime.getRuntime()
        .availableProcessors();
      System.out.println("Number of Cores=" + processorsAvailable);
      memoryStats();

    } catch (Exception e) { // exception thrown
      System.out.println("Command failed!");
    }

  }

  public static void OSInformation() {
    String operatingSystemName = System.getProperty("os.name");
    String operatingSystemVersion = System.getProperty("os.version");
    String operatingSyetemArchitecture = System.getProperty("os.arch");
    System.out.println(operatingSystemName + "\t" + operatingSystemVersion
      + "\t" + operatingSyetemArchitecture);
    getCpu();
  }

  public static void memoryStats() {
    long totalMemory = Runtime.getRuntime().totalMemory();
    long freeMemory = Runtime.getRuntime().freeMemory();
    long usedMemory = totalMemory - freeMemory;
    long maxMemory = Runtime.getRuntime().maxMemory();
    System.out.println("\nMemory Statistics in Mega Bytes................");
    System.out.println("Total Memory=" + totalMemory
      + "\nUsed Memory=" + usedMemory + "\nfreeMemory="
      + freeMemory + "\nMaximum Memory=" + maxMemory);
    System.out.println("Total Free Memory="
      + (freeMemory + (maxMemory - totalMemory)));
    discInformation();
  }

  public static void discInformation() {
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
    cpuInformation();
  }

  public static void cpuInformation() {

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

  public static void main(String[] args) throws IOException {
    SyStemantics.OSInformation();
    Connection c = null;
    try {
      Class.forName("org.sqlite.JDBC");
      c = DriverManager.getConnection("jdbc:sqlite:Systemantics.db");
      String f = "/root/Projects/Metrics-Collector/SySTemantics/Scripts/CreateTableScripts.sql";
      Statement stmt = null;
      stmt = c.createStatement();

      boolean executeDBSCripts;
      executeDBSCripts = executeDBScripts(f, stmt);
    } catch (Exception e) {
      System.err.println(e.getClass().getName() + ": " + e.getMessage());
      System.exit(0);
    }
    System.out.println("Opened database successfully");

//    Terminal terminal = new DefaultTerminalFactory().createTerminal();
//    Screen screen = new TerminalScreen(terminal);

//    String s = "Hello World!";
//    TextGraphics tGraphics = screen.newTextGraphics();
//
//    screen.startScreen();
//    screen.clear();
//
//    tGraphics.putString(10, 10, s);
//    screen.refresh();
//
//    screen.readInput();
//    screen.stopScreen();
  }

  public static boolean executeDBScripts(String aSQLScriptFilePath, Statement stmt) throws IOException, SQLException {
    boolean isScriptExecuted = false;
    try {
      BufferedReader in = new BufferedReader(new FileReader(aSQLScriptFilePath));
      String str;
      StringBuffer sb = new StringBuffer();
      while ((str = in.readLine()) != null) {
        sb.append(str + "\n ");
      }
      in.close();
      stmt.executeUpdate(sb.toString());
      isScriptExecuted = true;
    } catch (Exception e) {
      System.err.println("Failed to Execute" + aSQLScriptFilePath + ". The error is" + e.getMessage());
    }
    return isScriptExecuted;
  }

}
