package com.mscs_710l.systemantics;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author SIVARAMAKRISHNAPRASA
 */
public class SyStemantics {
    static String cmdTop = "top -b";//"less /proc/meminfo";//
    // static String cmdTop = "top -n 10 -b -d 0.5";
    // returns user CPU usage
    public static void getCpu() {

        try {
            // start up the command in child process
            String cmd = cmdTop;
            Process child = Runtime.getRuntime().exec(cmd);

            // hook up child process output to parent
            InputStream childOutput = child.getInputStream();
            InputStreamReader parentInput = new InputStreamReader(childOutput);
            BufferedReader readingParentInput = new BufferedReader(parentInput);

            // read the child process output
            String cpuInfo;
            for (int i = 0; i < parentInput.read(); i++) {
                cpuInfo = readingParentInput.readLine();
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
        System.out.println("\nMemory Statistics in Mega Bytes..................................");
        System.out.println("Total Memory=" + totalMemory
                + "\nUsed Memory=" + usedMemory  + "\nfreeMemory="
                + freeMemory  + "\nMaximum Memory=" + maxMemory);
        System.out.println("Total Free Memory="
                + (freeMemory + (maxMemory - totalMemory)));
        discInformation();
    }

    public static void discInformation() {
        /* Get a list of all filesystem roots on this system */
        File[] roots = File.listRoots();
        /* For each filesystem root, print some info */
        for (File root : roots) {
            System.out.println("\nDisc Statistics in..................................");
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

    public static void main(String[] args) {
        SyStemantics.OSInformation();
        Connection c = null;
    try {
      Class.forName("org.sqlite.JDBC");
      c = DriverManager.getConnection("jdbc:sqlite:Systemantics.db");
      
    } catch ( Exception e ) {
      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
      System.exit(0);
    }
    System.out.println("Opened database successfully");
    
    }

}
