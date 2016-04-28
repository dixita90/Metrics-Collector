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
     cpuInfo.getCpu(cmdFreeMemory);
//    cpuInfo.getCpu(cmdMeminfo);
    cpuInfo.networkStats(cmdNetStatTcp);
    cpuInfo.networkStats(cmdNetStatUdp);
   cpuInfo.iOStats(cmdIostat);
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
