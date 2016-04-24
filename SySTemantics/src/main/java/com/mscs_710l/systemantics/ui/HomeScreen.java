/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mscs_710l.systemantics.ui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.slf4j.LoggerFactory;

/**
 *
 * @author dixita
 */
public class HomeScreen extends Application {
    
    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(HomeScreen.class);
    private final TableView tblProcessInfo = new TableView();
    private final TableView tblFreeMemory = new TableView();
    private final TableView tblVmStat = new TableView();
    private final TableView tblVmStatDisk = new TableView();
    private final TableView tblIOStats = new TableView();
     private final TableView tblNetStats = new TableView();
    
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Home Screen");
        Group root = new Group();
        Scene scene = new Scene(root, 1250, 450, Color.WHITE);
        
        TabPane tabPane = new TabPane();
        
        BorderPane borderPane = new BorderPane();
        for (int i = 0; i < 3; i++) {
            Tab tab = new Tab();
             tab.setClosable(false);
            switch (i) {
                case 0:
                    tab.setText("CPU Info");
                    createProcessTable();
                    final VBox vbox = new VBox();
                    vbox.setSpacing(5);
                    vbox.setPadding(new Insets(50, 10, 10, 70));
                    vbox.getChildren().addAll(tblProcessInfo);
                    
                    ((Group) scene.getRoot()).getChildren().addAll(vbox);
                    tab.setContent(tblProcessInfo);
                    
                    break;
                case 1:
                    tab.setText("Memory Info");
                    createMemoryTable();
                    final VBox vbox1 = new VBox();
                    vbox1.setSpacing(5);
                    vbox1.setPadding(new Insets(50, 10, 10, 70));
                    vbox1.getChildren().addAll(tblFreeMemory, tblVmStatDisk,tblVmStat);
                    
                    ((Group) scene.getRoot()).getChildren().addAll(vbox1);
                    //tab.setContent(tblFreeMemory);

                    //tab.setContent(tblVmStatDisk);
                    ScrollPane sp=new ScrollPane();
                    //sp.setContent(tab);
                    break;
                case 2:
                    tab.setText("Network Info");
                   createNetworkTable();
                    final VBox vbox2 = new VBox();
                    vbox2.setSpacing(5);
                    vbox2.setPadding(new Insets(50, 10, 10, 70));
                    vbox2.getChildren().addAll(tblNetStats);
                    
                    ((Group) scene.getRoot()).getChildren().addAll(vbox2);
                    tab.setContent(tblNetStats);
                    break;
                default:
                    tab.setText("Extra tab");
                    break;
            }
            
            tabPane.getTabs().add(tab);
        }
        // bind to take available space
        borderPane.prefHeightProperty().bind(scene.heightProperty());
        borderPane.prefWidthProperty().bind(scene.widthProperty());
        
        borderPane.setCenter(tabPane);
        root.getChildren().add(borderPane);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    private void createProcessTable() {
        try {
            TableColumn pidCol = new TableColumn("Process ID");
            TableColumn ppidCol = new TableColumn("Parent Process ID");
            TableColumn usernameCol = new TableColumn("Username");
            TableColumn priorityCol = new TableColumn("Priority");
            TableColumn niceCol = new TableColumn("Nice Value");
            TableColumn virtualCol = new TableColumn("Virtual");
            TableColumn resCol = new TableColumn("Res");
            TableColumn sharedCol = new TableColumn("Shared");
            TableColumn statusCol = new TableColumn("Status");
            TableColumn prctCpuUsageCol = new TableColumn("% CPU Usage");
            TableColumn prctMemUsageCol = new TableColumn("% Memory Usage");
            TableColumn timeCol = new TableColumn("Time");
            TableColumn commandCol = new TableColumn("Command");
            
            tblProcessInfo.getColumns().addAll(pidCol, ppidCol, usernameCol,
                    priorityCol, niceCol, virtualCol, resCol, sharedCol,
                    statusCol, prctCpuUsageCol, prctMemUsageCol, timeCol, commandCol);
            
        } catch (Exception ex) {
            LOGGER.error("Exception in createProcessTable() in HomeScreen.java" + ex.getMessage());
        }
    }
    
    private void createNetworkTable() {
        try {
            TableColumn nidCol = new TableColumn("Network ID");
            TableColumn npidCol = new TableColumn("Network Process ID");
            TableColumn nProtocolCol = new TableColumn("Protocol");
            TableColumn nUserCol = new TableColumn("User");
            TableColumn nBWSentCol = new TableColumn("Sending Bandwidth");
            TableColumn nBWReceivedCol = new TableColumn("Receiving Bandwidth");
            TableColumn nStatus = new TableColumn("Status");
                   
            tblNetStats.getColumns().addAll(nidCol, npidCol, nProtocolCol,
                    nUserCol, nBWSentCol, nBWReceivedCol, nStatus);
            
        } catch (Exception ex) {
            LOGGER.error("Exception in createProcessTable() in HomeScreen.java" + ex.getMessage());
        }
    }
    
    private void createMemoryTable() {
        try {
            TableColumn freeMemIDCol = new TableColumn("Memory ID");
            TableColumn freeMemNameCol = new TableColumn("Memory Name");
            TableColumn freeMemTotCol = new TableColumn("Total Free Memory");
            TableColumn freeMemUsedMemCol = new TableColumn("Used Memory");
            TableColumn freeMemSharedCol = new TableColumn("Shared Memory");
            TableColumn freeMemBuffCacheCol = new TableColumn("Buffer Cache");
            TableColumn freeMemAvailCol = new TableColumn("Available Memory");
            
            tblFreeMemory.getColumns().addAll(freeMemIDCol, freeMemNameCol, freeMemTotCol,
                    freeMemUsedMemCol, freeMemSharedCol, freeMemBuffCacheCol, freeMemAvailCol);
            
            TableColumn vmDiskNameCol = new TableColumn("Memory ID");
            TableColumn vmTotReadsCol = new TableColumn("Total Reads");
            TableColumn vmMergedReadsCol = new TableColumn("Merged Reads");
            TableColumn vmSectorReadsCol = new TableColumn("Sector Reads");
            TableColumn vmMSReadsCol = new TableColumn("MS Reads");
            TableColumn vmTotalWritesCol = new TableColumn("Total Writes");
            TableColumn vmMergedWritesCol = new TableColumn("Merged Writes");
            TableColumn vmSectorWritesCol = new TableColumn("Sector Writes");
            TableColumn vmMSWritesCol = new TableColumn("MS Writes");
            TableColumn vmCurCol = new TableColumn("Cur");
            TableColumn vmSecCol = new TableColumn("Sec");
            
            tblVmStatDisk.getColumns().addAll(vmDiskNameCol, vmTotReadsCol, vmMergedReadsCol,
                    vmSectorReadsCol, vmMSReadsCol, vmTotalWritesCol, vmMergedWritesCol,
                    vmSectorWritesCol, vmMSWritesCol, vmCurCol, vmSecCol);
            
            TableColumn vmIDCol = new TableColumn("VM ID");
            TableColumn vmProcessWaitCol = new TableColumn("VM Process Wait Time");
            TableColumn vmProcessIoWaitCol = new TableColumn("VM Process IO Wait Time");
            TableColumn vmSwpdOutCol = new TableColumn("Swpd Out");
            TableColumn vmFreeCol = new TableColumn("VM Free");
            TableColumn vmCacheCol = new TableColumn("VM Cache");
            TableColumn vmOsSwapInCol = new TableColumn("OS Swap In");
            TableColumn vmOsSwapOutCol = new TableColumn("OS Swap Out");
            TableColumn vmBlockRead = new TableColumn("VM Block Read");
            TableColumn vmBlockWriteCol = new TableColumn("VM Block Write");
            TableColumn vmInterruptsCol = new TableColumn("VM Interrupts");
            TableColumn vmContextSwitchesCol = new TableColumn("Context Switches");
            TableColumn vmCpuNonKernelModeCol = new TableColumn("CPU Non Kernel Mode");
            TableColumn vmCpuKernelModeCol = new TableColumn("CPU Kernel Mode");
            TableColumn vmCpuIdleTimeCol = new TableColumn("CPU Idle Time");
            TableColumn vmWaitIOCol= new TableColumn("CPU Wait IO");
            TableColumn vmTimeStolenCol = new TableColumn("Time Stolen");

             tblVmStat.getColumns().addAll(vmIDCol, vmProcessWaitCol, vmProcessIoWaitCol,
                    vmSwpdOutCol, vmFreeCol, vmCacheCol, vmOsSwapInCol,
                    vmOsSwapOutCol, vmBlockRead, vmBlockWriteCol, vmInterruptsCol,
                    vmContextSwitchesCol,vmCpuNonKernelModeCol,vmCpuKernelModeCol,
                    vmCpuIdleTimeCol,vmWaitIOCol,vmTimeStolenCol);
            
        } catch (Exception ex) {
            LOGGER.error("Exception in createMemoryTable() in HomeScreen.java" + ex.getMessage());
        }
    }
}
