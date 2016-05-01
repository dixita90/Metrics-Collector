/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * file: HomeScreen.java
 * author: Siva Chintapalli, Dixita Sheregar, Bhargav Uppalapati.
 * course: MSCS 710
 * Project
 * version: 1.0
 *
 * This file contains functions which implement ..............
 */
package com.mscs_710l.systemantics.ui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.slf4j.LoggerFactory;

/**
 * HomeScreen
 *
 * This class implements functions which..........
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
        Scene scene = new Scene(root, 1250, 450, Color.LIGHTGRAY);

        TabPane tabPane = new TabPane();

        BorderPane borderPane = new BorderPane();
        for (int i = 0; i < 4; i++) {
            Tab tab = new Tab();
            tab.setClosable(false);
            switch (i) {
                case 0:
                    tab.setText("CPU Info");
                    createProcessTable();
                    tab.setContent(tblProcessInfo);

                    break;
                case 1:
                    tab.setText("Memory Info");
                    createMemoryTable();

                    VBox vBox = new VBox();
                    vBox.getChildren().addAll(
                            tblFreeMemory, tblVmStatDisk, tblVmStat);
                    tab.setContent(vBox);

                    ScrollPane sp = new ScrollPane();
                    //sp.setContent(tab);
                    break;
                case 2:
                    tab.setText("Network Info");
                    createNetworkTable();
                    tab.setContent(tblNetStats);
                    break;
                case 3:
                    tab.setText("System Details");

                    GridPane grid = new GridPane();
                    grid.setAlignment(Pos.CENTER);
                    grid.setHgap(10);
                    grid.setVgap(10);

                    grid.setPadding(new Insets(25, 25, 25, 25));
                    Label lblCompName = new Label("Computer Name:");
                    grid.add(lblCompName, 0, 1);
                    
                    Label lblCompNameVal = new Label("DIXITA_PC");
                    grid.add(lblCompNameVal, 1, 1);
                    
                     Label lblSysType = new Label("System Type:");
                    grid.add(lblSysType, 0, 2);
                    
                     Label lblSysTypeVal = new Label("64 bit OS");
                    grid.add(lblSysTypeVal, 1, 2);
                    
                    Label lblProcessor = new Label("Procesor:");
                    grid.add(lblProcessor, 0, 3);
                    
                    Label lblProcessorVal = new Label("i5");
                    grid.add(lblProcessorVal, 1, 3);
                    
                    Label lblRAM = new Label("Installed Memory (RAM)");
                    grid.add(lblRAM, 0, 4);
                    
                    Label lblRAMVal = new Label("6.00 GB");
                    grid.add(lblRAMVal, 1, 4);

                    tab.setContent(grid);
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
            TableColumn vmWaitIOCol = new TableColumn("CPU Wait IO");
            TableColumn vmTimeStolenCol = new TableColumn("Time Stolen");

            tblVmStat.getColumns().addAll(vmIDCol, vmProcessWaitCol, vmProcessIoWaitCol,
                    vmSwpdOutCol, vmFreeCol, vmCacheCol, vmOsSwapInCol,
                    vmOsSwapOutCol, vmBlockRead, vmBlockWriteCol, vmInterruptsCol,
                    vmContextSwitchesCol, vmCpuNonKernelModeCol, vmCpuKernelModeCol,
                    vmCpuIdleTimeCol, vmWaitIOCol, vmTimeStolenCol);

        } catch (Exception ex) {
            LOGGER.error("Exception in createMemoryTable() in HomeScreen.java" + ex.getMessage());
        }
    }
}
