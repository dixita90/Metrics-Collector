/**
 * file: HomeScreen.java author: Siva Chintapalli, Dixita Sheregar, Bhargav
 * Uppalapati. course: MSCS 710 Project version: 1.0
 *
 * This file contains code for UI and populating data from the backend to the
 * UI.
 */
package com.mscs_710l.systemantics.ui;

import com.mscs_710l.systemantics.bl.CpuInfo;
import com.mscs_710l.systemantics.db.SystemanticsDb;
import com.mscs_710l.systemantics.pojo.FreeMemory;
import com.mscs_710l.systemantics.pojo.IOStats;
import com.mscs_710l.systemantics.pojo.ProcessInfo;
import com.mscs_710l.systemantics.pojo.VirtualDiskInfo;
import com.mscs_710l.systemantics.pojo.VirtualMemoryStats;
import java.util.List;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.apache.log4j.PropertyConfigurator;
import org.slf4j.LoggerFactory;

/**
 * HomeScreen
 *
 * This class implements contains code for UI and populating data from the
 * backend to the UI.
 */
public class HomeScreen extends Application {

    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(HomeScreen.class);
    private final TableView tblProcessInfo = new TableView();
    private final TableView tblFreeMemory = new TableView();
    private final TableView tblVmStat = new TableView();
    private final TableView tblVmStatDisk = new TableView();
    private final TableView tblIOStats = new TableView();
    private final TableView tblNetStats = new TableView();

    static {
        SystemanticsDb.saveSystematic();
    }

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

    private List lstCpuInfo;

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
            CpuInfo c = new CpuInfo();
            List lst;
            switch (i) {
                case 0:
                    tab.setText("CPU Info");
                    createProcessTable();

                    lst = c.getCpu(CMDTOP);
                    bindListToTable(lst, 0);
                    tab.setContent(tblProcessInfo);

                    break;
                case 1:
                    tab.setText("Memory Info");
                    createMemoryTable();
                    lst = c.memoryStats(CMDFREEMEMORY);
                    bindListToTable(lst, 11);

                    lst = c.virtualDiskStats(CMDVDISKSTATS);
                    bindListToTable(lst, 12);

                    lst = c.virtualMemoryStats(CMDVMSTAT);
                    bindListToTable(lst, 13);

                    lst = c.iOStats(CMDIOSTAT);
                    bindListToTable(lst, 14);

                    VBox vBox = new VBox();
                    vBox.getChildren().addAll(
                            tblFreeMemory, tblVmStatDisk, tblVmStat,tblIOStats);
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
        PropertyConfigurator.configure("log4j.properties");
        LOGGER.debug("SySTematics main(): starts");
        CpuInfo cpuInfo = new CpuInfo();
        //cpuInfo.memoryStats(CMDFREEMEMORY);
        //cpuInfo.virtualMemoryStats(CMDVMSTAT);
        //cpuInfo.virtualDiskStats(CMDVDISKSTATS);
        //List lstCpuInfo = cpuInfo.getCpu(CMDTOP);
        cpuInfo.networkStats(CMDNETSTATTCP);
        cpuInfo.networkStats(CMDNETSTATUDP);
        //cpuInfo.iOStats(CMDIOSTAT);
        cpuInfo.discInformation();
        cpuInfo.cpuInformation();

        LOGGER.debug("HomeScreen main(): ends");
    }

    private void bindListToTable(List lst, int tabNumber) {
        try {
            //final ObservableList<ProcessInfo> data = FXCollections.observableArrayList();
            final ObservableList<Object> data = FXCollections.observableArrayList();
            for (Object p : lst) {
                data.add(p);
            }
            switch (tabNumber) {
                case 0:
                    tblProcessInfo.setItems(data);
                    break;
                case 11:
                    tblFreeMemory.setItems(data);
                    break;
                case 12:
                    tblVmStatDisk.setItems(data);
                    break;
                case 13:
                    tblVmStat.setItems(data);
                    break;
                case 14:
                    tblIOStats.setItems(data);
                    break;
                default:
                    break;
            }

        } catch (Exception ex) {
            LOGGER.error("Error in bindListToTable(): " + ex.getMessage());
        }
    }

    private void createProcessTable() {
        try {
            // TableColumn pidCol = new TableColumn("Process ID");
            TableColumn ppidCol = new TableColumn("Parent Process ID");
            ppidCol.setCellValueFactory(
                    new PropertyValueFactory<ProcessInfo, Integer>("PI_PID"));
            TableColumn usernameCol = new TableColumn("Username");
            usernameCol.setCellValueFactory(
                    new PropertyValueFactory<ProcessInfo, String>("PI_Username"));
            TableColumn priorityCol = new TableColumn("Priority");
            priorityCol.setCellValueFactory(
                    new PropertyValueFactory<ProcessInfo, String>("PI_Priority"));
            TableColumn niceCol = new TableColumn("Nice Value");
            niceCol.setCellValueFactory(
                    new PropertyValueFactory<ProcessInfo, Integer>("PI_Nice"));
            TableColumn virtualCol = new TableColumn("Virtual");
            virtualCol.setCellValueFactory(
                    new PropertyValueFactory<ProcessInfo, Integer>("PI_Virtual"));
            TableColumn resCol = new TableColumn("Res");
            resCol.setCellValueFactory(
                    new PropertyValueFactory<ProcessInfo, String>("PI_Res"));
            TableColumn sharedCol = new TableColumn("Shared");
            sharedCol.setCellValueFactory(
                    new PropertyValueFactory<ProcessInfo, Integer>("PI_Shared"));
            TableColumn statusCol = new TableColumn("Status");
            statusCol.setCellValueFactory(
                    new PropertyValueFactory<ProcessInfo, String>("PI_Status"));
            TableColumn prctCpuUsageCol = new TableColumn("% CPU Usage");
            prctCpuUsageCol.setCellValueFactory(
                    new PropertyValueFactory<ProcessInfo, Double>("PI_PerctCpuUsage"));
            TableColumn prctMemUsageCol = new TableColumn("% Memory Usage");
            prctMemUsageCol.setCellValueFactory(
                    new PropertyValueFactory<ProcessInfo, Double>("PI_PerctMemUsage"));
            TableColumn timeCol = new TableColumn("Time");
            timeCol.setCellValueFactory(
                    new PropertyValueFactory<ProcessInfo, String>("PI_TIME"));
            TableColumn commandCol = new TableColumn("Command");
            commandCol.setCellValueFactory(
                    new PropertyValueFactory<ProcessInfo, String>("PI_Command"));

            tblProcessInfo.getColumns().addAll(ppidCol, usernameCol,
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
            TableColumn freeMemNameCol = new TableColumn("Memory Name");
            freeMemNameCol.setCellValueFactory(
                    new PropertyValueFactory<FreeMemory, String>("name"));
            TableColumn freeMemTotCol = new TableColumn("Total Free Memory");
            freeMemTotCol.setCellValueFactory(
                    new PropertyValueFactory<FreeMemory, Integer>("total"));
            TableColumn freeMemUsedMemCol = new TableColumn("Used Memory");
            freeMemUsedMemCol.setCellValueFactory(
                    new PropertyValueFactory<FreeMemory, Integer>("used"));
            TableColumn freeMemSharedCol = new TableColumn("Shared Memory");
            freeMemSharedCol.setCellValueFactory(
                    new PropertyValueFactory<FreeMemory, Integer>("shared"));
            TableColumn freeMemBuffCacheCol = new TableColumn("Buffer Cache");
            freeMemBuffCacheCol.setCellValueFactory(
                    new PropertyValueFactory<FreeMemory, Integer>("buff_cache"));

            tblFreeMemory.getColumns().addAll(freeMemNameCol, freeMemTotCol,
                    freeMemUsedMemCol, freeMemSharedCol, freeMemBuffCacheCol);

            TableColumn vmDiskNameCol = new TableColumn("Memory ID");
            vmDiskNameCol.setCellValueFactory(
                    new PropertyValueFactory<VirtualDiskInfo, String>("VM_DISKNAME"));
            TableColumn vmTotReadsCol = new TableColumn("Total Reads");
            vmTotReadsCol.setCellValueFactory(
                    new PropertyValueFactory<VirtualDiskInfo, Integer>("VM_TOTALREADS"));
            TableColumn vmMergedReadsCol = new TableColumn("Merged Reads");
            vmMergedReadsCol.setCellValueFactory(
                    new PropertyValueFactory<VirtualDiskInfo, Integer>("VM_MERGEDREADS"));
            TableColumn vmSectorReadsCol = new TableColumn("Sector Reads");
            vmSectorReadsCol.setCellValueFactory(
                    new PropertyValueFactory<VirtualDiskInfo, Integer>("VM_SECTORSREADS"));
            TableColumn vmMSReadsCol = new TableColumn("MS Reads");
            vmMSReadsCol.setCellValueFactory(
                    new PropertyValueFactory<VirtualDiskInfo, Integer>("VM_MSREADS"));
            TableColumn vmTotalWritesCol = new TableColumn("Total Writes");
            vmTotalWritesCol.setCellValueFactory(
                    new PropertyValueFactory<VirtualDiskInfo, Integer>("VM_TOATALWRITES"));
            TableColumn vmMergedWritesCol = new TableColumn("Merged Writes");
            vmMergedWritesCol.setCellValueFactory(
                    new PropertyValueFactory<VirtualDiskInfo, Integer>("VM_MERGEDWRITES"));
            TableColumn vmSectorWritesCol = new TableColumn("Sector Writes");
            vmSectorWritesCol.setCellValueFactory(
                    new PropertyValueFactory<VirtualDiskInfo, Integer>("VM_SECTORWRITES"));
            TableColumn vmMSWritesCol = new TableColumn("MS Writes");
            vmMSWritesCol.setCellValueFactory(
                    new PropertyValueFactory<VirtualDiskInfo, Integer>("VM_MSWRITES"));
            TableColumn vmCurCol = new TableColumn("Cur");
            vmCurCol.setCellValueFactory(
                    new PropertyValueFactory<VirtualDiskInfo, Integer>("VM_CUR"));
            TableColumn vmSecCol = new TableColumn("Sec");
            vmSecCol.setCellValueFactory(
                    new PropertyValueFactory<VirtualDiskInfo, Integer>("VM_SEC"));

            tblVmStatDisk.getColumns().addAll(vmDiskNameCol, vmTotReadsCol, vmMergedReadsCol,
                    vmSectorReadsCol, vmMSReadsCol, vmTotalWritesCol, vmMergedWritesCol,
                    vmSectorWritesCol, vmMSWritesCol, vmCurCol, vmSecCol);

            TableColumn vmProcessWaitCol = new TableColumn("VM Process Wait Time");
            vmProcessWaitCol.setCellValueFactory(
                    new PropertyValueFactory<VirtualMemoryStats, Integer>("VM_PROCESS_WAIT_TIME"));
            TableColumn vmProcessIoWaitCol = new TableColumn("VM Process IO Wait Time");
            vmProcessIoWaitCol.setCellValueFactory(
                    new PropertyValueFactory<VirtualMemoryStats, Integer>("VM_PROCESS_IO_WAIT_TIME"));
            TableColumn vmSwpdOutCol = new TableColumn("Swpd Out");
            vmSwpdOutCol.setCellValueFactory(
                    new PropertyValueFactory<VirtualMemoryStats, Integer>("VM_SWPDOUT"));
            TableColumn vmFreeCol = new TableColumn("VM Free");
            vmFreeCol.setCellValueFactory(
                    new PropertyValueFactory<VirtualMemoryStats, Integer>("VM_FREE"));
            TableColumn vmBufferCol = new TableColumn("VM Buffer");
            vmBufferCol.setCellValueFactory(
                    new PropertyValueFactory<VirtualMemoryStats, Integer>("VM_BUFFER"));
            TableColumn vmCacheCol = new TableColumn("VM Cache");
            vmCacheCol.setCellValueFactory(
                    new PropertyValueFactory<VirtualMemoryStats, Integer>("VM_CACHE"));
            TableColumn vmOsSwapInCol = new TableColumn("OS Swap In");
            vmOsSwapInCol.setCellValueFactory(
                    new PropertyValueFactory<VirtualMemoryStats, Integer>("VM_OSSWAPIN"));
            TableColumn vmOsSwapOutCol = new TableColumn("OS Swap Out");
            vmOsSwapOutCol.setCellValueFactory(
                    new PropertyValueFactory<VirtualMemoryStats, Integer>("VM_OSSWAPOUT"));
            TableColumn vmBlockRead = new TableColumn("VM Block Read");
            vmBlockRead.setCellValueFactory(
                    new PropertyValueFactory<VirtualMemoryStats, Integer>("VM_BLOCKREAD"));
            TableColumn vmBlockWriteCol = new TableColumn("VM Block Write");
            vmBlockWriteCol.setCellValueFactory(
                    new PropertyValueFactory<VirtualMemoryStats, Integer>("VM_BLOCKWRITE"));
            TableColumn vmInterruptsCol = new TableColumn("VM Interrupts");
            vmInterruptsCol.setCellValueFactory(
                    new PropertyValueFactory<VirtualMemoryStats, Integer>("VM_INTERRUPTS"));
            TableColumn vmContextSwitchesCol = new TableColumn("Context Switches");
            vmContextSwitchesCol.setCellValueFactory(
                    new PropertyValueFactory<VirtualMemoryStats, Integer>("VM_CONTXTSWITCHES"));
            TableColumn vmCpuNonKernelModeCol = new TableColumn("CPU Non Kernel Mode");
            vmCpuNonKernelModeCol.setCellValueFactory(
                    new PropertyValueFactory<VirtualMemoryStats, Integer>("VM_CPUNONKERNALMODE"));
            TableColumn vmCpuKernelModeCol = new TableColumn("CPU Kernel Mode");
            vmCpuKernelModeCol.setCellValueFactory(
                    new PropertyValueFactory<VirtualMemoryStats, Integer>("VM_CPUKERNALMODE"));
            TableColumn vmCpuIdleTimeCol = new TableColumn("CPU Idle Time");
            vmCpuIdleTimeCol.setCellValueFactory(
                    new PropertyValueFactory<VirtualMemoryStats, Integer>("VM_CPUIDELTIME"));
            TableColumn vmWaitIOCol = new TableColumn("CPU Wait IO");
            vmWaitIOCol.setCellValueFactory(
                    new PropertyValueFactory<VirtualMemoryStats, Integer>("VM_CPUWAITIO"));
            TableColumn vmTimeStolenCol = new TableColumn("Time Stolen");
            vmTimeStolenCol.setCellValueFactory(
                    new PropertyValueFactory<VirtualMemoryStats, Integer>("VM_TIMESTOLENVM"));

            tblVmStat.getColumns().addAll(vmProcessWaitCol, vmProcessIoWaitCol,
                    vmSwpdOutCol, vmFreeCol, vmBufferCol, vmCacheCol, vmOsSwapInCol,
                    vmOsSwapOutCol, vmBlockRead, vmBlockWriteCol, vmInterruptsCol,
                    vmContextSwitchesCol, vmCpuNonKernelModeCol, vmCpuKernelModeCol,
                    vmCpuIdleTimeCol, vmWaitIOCol, vmTimeStolenCol);

            TableColumn ioDiskNameCol = new TableColumn("IO Disk Name");
            ioDiskNameCol.setCellValueFactory(
                    new PropertyValueFactory<IOStats, String>("IO_DISKNAME"));
            TableColumn ioTransferCol = new TableColumn("IO Transfer Per sec");
            ioTransferCol.setCellValueFactory(
                    new PropertyValueFactory<IOStats, Double>("IO_TRANSFERPERSEC"));
            TableColumn ioKbReadsCol = new TableColumn("IO KB Read");
            ioKbReadsCol.setCellValueFactory(
                    new PropertyValueFactory<IOStats, Double>("IO_KB_READS"));
            TableColumn ioKbWritesCol = new TableColumn("IO KB Write");
            ioKbWritesCol.setCellValueFactory(
                    new PropertyValueFactory<IOStats, Double>("IO_KB_WRITES"));
            TableColumn ioTotBlocksReadCol = new TableColumn("IO Total Blocks Read");
            ioTotBlocksReadCol.setCellValueFactory(
                    new PropertyValueFactory<IOStats, Double>("IO_TOTALBLOCKSREAD"));
            TableColumn ioTotBlocksWriteCol = new TableColumn("IO Total Blocks Write");
            ioTotBlocksWriteCol.setCellValueFactory(
                    new PropertyValueFactory<IOStats, Double>("IO_TOATALBLOCKSWRITES"));

            tblIOStats.getColumns().addAll(ioDiskNameCol, ioTransferCol, ioKbReadsCol, 
                    ioKbWritesCol, ioTotBlocksReadCol, ioTotBlocksWriteCol);
        } catch (Exception ex) {
            LOGGER.error("Exception in createMemoryTable() in HomeScreen.java" + ex.getMessage());
        }
    }
}
