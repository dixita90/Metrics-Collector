/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * file: SyStemantics.java
 * author: Siva Chintapalli, Dixita Sheregar, Bhargav Uppalapati.
 * course: MSCS 710
 * Project
 * version: 1.0
 *
 * This file contains functions which implement ..........
 */
package com.mscs_710l.systemantics.ui;

import com.mscs_710l.systemantics.bl.CpuInfo;
import com.mscs_710l.systemantics.db.SystemanticsDb;
import java.io.IOException;
import org.apache.log4j.PropertyConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * SyStemantics
 *
 * This class implements functions which...........
 */
public class SyStemantics {

    static {
        SystemanticsDb.saveSystematic();
    }
    private static final Logger LOGGER = LoggerFactory.getLogger(SyStemantics.class);
    //"top"- command to pull system information.
    private static final String CMDTOP = "top -b";
    //command to pull free memory that is avaliable.
    private static final String CMDFREEMEMORY = "free -m";
    //command to pull memory information.
    private static final String CMDMEMINFO = "cat /proc/meminfo";
    //command to access virtual memory statistics.
    private static final String CMDVMSTAT = "vmstat -t 1 6";
    //command to access virtual memory statistics.
    private static final String CMDVDISKSTATS = "vmstat -t -d";
    //command to access network statistics.
    private static final String CMDNETSTATTCP = "netstat -e -p -at";
    //command to access network statistics.
    private static final String CMDNETSTATUDP = "netstat -au";
    //command to access IO statistics.
    private static final String CMDIOSTAT = "iostat -d -N";

    public static void main(String[] args) throws IOException {
        PropertyConfigurator.configure("log4j.properties");
        LOGGER.debug("SySTematics main(): starts");
        CpuInfo cpuInfo = new CpuInfo();
        cpuInfo.memoryStats(CMDFREEMEMORY);
        cpuInfo.virtualMemoryStats(CMDVMSTAT);
        cpuInfo.virtualDiskStats(CMDVDISKSTATS);
        cpuInfo.getCpu(CMDFREEMEMORY);
//    cpuInfo.getCpu(cmdMeminfo);
        cpuInfo.networkStats(CMDNETSTATTCP);
        cpuInfo.networkStats(CMDNETSTATUDP);
        cpuInfo.iOStats(CMDIOSTAT);
        cpuInfo.discInformation();
        cpuInfo.cpuInformation();
        LOGGER.debug("SySTematics main(): ends");
    }

    /*
  public void BindTable() {
        try {
            CpuInfo cpuInfo = new CpuInfo();
            cpuInfo.memoryStats(cmdFreeMemory);
            ResultSet rs = cpuInfo.getCpuInfoResultSet();
            /**
             * ********************************
             * TABLE COLUMN ADDED DYNAMICALLY * ********************************
     */
 /*            for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
                //We are using non property style for making dynamic table
                final int j = i;
                TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i + 1));
                col.setCellValueFactory(new Callback<CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
                    public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {
                        return new SimpleStringProperty(param.getValue().get(j).toString());
                    }
                });

                tblProcess.getColumns().addAll(col);
                System.out.println("Column [" + i + "] ");
            }

            /**
             * ******************************
             * Data added to ObservableList * ******************************
     */
 /*          while (rs.next()) {
                //Iterate Row
                ObservableList<String> row = FXCollections.observableArrayList();
                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                    //Iterate Column
                    row.add(rs.getString(i));
                }
                System.out.println("Row [1] added " + row);
                data.add(row);

            }

            //FINALLY ADDED TO TableView
            tblProcess.setItems(data);
            //FINALLY ADDED TO TableView
            tblProcess.setItems(data);

        } catch (SQLException ex) {
            LOGGER.error("Error in BindTable() in SyStemantics.java: "+ex.getMessage());
        }
    }
     */
}
