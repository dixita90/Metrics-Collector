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
import com.mscs_710l.systemantics.pojo.NetworkStats;
import com.mscs_710l.systemantics.pojo.ProcessInfo;
import com.mscs_710l.systemantics.pojo.SystemDetails;
import com.mscs_710l.systemantics.pojo.VirtualDiskInfo;
import com.mscs_710l.systemantics.pojo.VirtualMemoryStats;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import javafx.animation.AnimationTimer;

import javafx.application.Application;
import javafx.application.Platform;
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
    Timer timer = new Timer();

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
    //command to access virtual Disc memory statistics.
    private static final String CMDVDISKSTATS = "vmstat -t -d";
    //command to access network statistics for TCP.
    private static final String CMDNETSTATTCP = "netstat -e -p -at";
    //command to access network statistics for UDP.
    private static final String CMDNETSTATUDP = "netstat -au -e -p";
    //command to access IO statistics.
    private static final String CMDIOSTAT = "iostat -d -N";

    TabPane tabPane = new TabPane();
    Tab tabProcessInfo = new Tab();
    Tab tabMemInfo = new Tab();
    Tab tabNetwkInfo = new Tab();
    Tab tabSysInfo = new Tab();
    Label lblCompName = new Label("Computer Name:");
    Label lblCompNameVal = new Label();
    Label lblSysType = new Label("System Type:");
    Label lblSysTypeVal = new Label();
    Label lblProcessor = new Label("Procesor:");
    Label lblProcessorVal = new Label();
    Label lblByteOrder = new Label("Byte Order");
    Label lblByteOrderVal = new Label();
    GridPane grid = new GridPane();

    /**
     * This function sets the scene, i.e all UI elements. Four tabs are created
     * to display Process,Memory, Network and System related data. Data is
     * populated in the tables by executing commands.
     *
     * @param primaryStage Paints the scene on the screen.
     */
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Home Screen");
        Group root = new Group();
        Scene scene = new Scene(root, 1250, 450, Color.LIGHTGRAY);

        BorderPane borderPane = new BorderPane();
        int i = 0;
        while (i < 4) {

            CpuInfo c = new CpuInfo();
            List lst;
            switch (i) {
                case 0:
                    tabProcessInfo.setClosable(false);
                    tabProcessInfo.setText("CPU Info");
                    createProcessTable();

                    lst = c.getCpu(CMDTOP);
                    bindListToTable(lst, 0);
                    tabProcessInfo.setContent(tblProcessInfo);
                    tabPane.getTabs().add(tabProcessInfo);
                    break;
                case 1:
                    tabMemInfo.setText("Memory Info");
                    tabMemInfo.setClosable(false);
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
                            tblFreeMemory, tblVmStatDisk, tblVmStat, tblIOStats);
                    tabMemInfo.setContent(vBox);
                    tabPane.getTabs().add(tabMemInfo);

                    break;
                case 2:
                    tabNetwkInfo.setText("Network Info");
                    tabNetwkInfo.setClosable(false);
                    createNetworkTable();

                    lst = c.networkStats(CMDNETSTATTCP);
                    lst.addAll(c.networkStats(CMDNETSTATUDP));
                    bindListToTable(lst, 2);

                    tabNetwkInfo.setContent(tblNetStats);
                    tabPane.getTabs().add(tabNetwkInfo);
                    SystemanticsDb db = new SystemanticsDb();
                    db.retriveProcessData();
                    break;
                case 3:
                    tabSysInfo.setText("System Details");
                    tabSysInfo.setClosable(false);
                    SystemDetails sd = c.cpuInformation();
                    String sysName = System.getenv("HOSTNAME");
                    String name[] = sysName.replace(".", ",").split(",");

                    grid.setAlignment(Pos.CENTER);
                    grid.setHgap(10);
                    grid.setVgap(10);
                    grid.setPadding(new Insets(25, 25, 25, 25));

                    grid.add(lblCompName, 0, 1);

                    lblCompNameVal.setText(name[0]);
                    grid.add(lblCompNameVal, 1, 1);

                    grid.add(lblSysType, 0, 2);

                    lblSysTypeVal.setText(sd.getSYSTEM_TYPE());
                    grid.add(lblSysTypeVal, 1, 2);

                    grid.add(lblProcessor, 0, 3);

                    lblProcessorVal.setText(sd.getPROCESSOR());
                    grid.add(lblProcessorVal, 1, 3);

                    grid.add(lblByteOrder, 0, 4);

                    lblByteOrderVal.setText(sd.getBYTE_ORDER());
                    grid.add(lblByteOrderVal, 1, 4);

                    refreshData();
                    tabSysInfo.setContent(grid);
                    tabPane.getTabs().add(tabSysInfo);
                    break;
                default:
                    break;
            }
            i++;
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
     * Launches the screen and sets the logger
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        launch(null);
        PropertyConfigurator.configure("log4j.properties");
        LOGGER.debug("SySTematics main(): starts");
        CpuInfo cpuInfo = new CpuInfo();
        //cpuInfo.memoryStats(CMDFREEMEMORY);
        //cpuInfo.virtualMemoryStats(CMDVMSTAT);
        //cpuInfo.virtualDiskStats(CMDVDISKSTATS);
        //List lstCpuInfo = cpuInfo.getCpu(CMDTOP);
        //cpuInfo.networkStats(CMDNETSTATTCP);
        //cpuInfo.networkStats(CMDNETSTATUDP);
        //cpuInfo.iOStats(CMDIOSTAT);
        //cpuInfo.discInformation();
        cpuInfo.cpuInformation();

        LOGGER.debug("HomeScreen main(): ends");
    }

    /**
     * Binds the list returned after executing commands to the tableView
     *
     * @param lst Contains data that needs to be displayed on screen.
     * @param tabNumber used to bind data to appropriate table.
     */
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
                case 2:
                    tblNetStats.setItems(data);
                    break;
                default:
                    break;
            }

        } catch (Exception ex) {
            LOGGER.error("Error in bindListToTable(): " + ex.getMessage());
        }
    }

    /**
     * Create the process info table with appropriate data types for each column
     * and the link to appropriate field to which it is bound.
     */
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
            tblProcessInfo.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
            tblProcessInfo.getColumns().addAll(ppidCol, usernameCol,
                    priorityCol, niceCol, virtualCol, resCol, sharedCol,
                    statusCol, prctCpuUsageCol, prctMemUsageCol, timeCol, commandCol);

        } catch (Exception ex) {
            LOGGER.error("Exception in createProcessTable() in HomeScreen.java" + ex.getMessage());
        }
    }

    /**
     * Create the network info table with appropriate data types for each column
     * and the link to appropriate field to which it is bound.
     */
    private void createNetworkTable() {
        try {

            TableColumn npidCol = new TableColumn("Network Process ID");
            npidCol.setCellValueFactory(
                    new PropertyValueFactory<NetworkStats, Integer>("NI_PID"));
            TableColumn nProtocolCol = new TableColumn("Protocol");
            nProtocolCol.setCellValueFactory(
                    new PropertyValueFactory<NetworkStats, String>("NI_Protocal"));
            TableColumn nUserCol = new TableColumn("User");
            nUserCol.setCellValueFactory(
                    new PropertyValueFactory<NetworkStats, String>("NI_User"));
            TableColumn nPrgCol = new TableColumn("Program");
            nPrgCol.setCellValueFactory(
                    new PropertyValueFactory<NetworkStats, String>("NI_Program"));
            TableColumn nBWSentCol = new TableColumn("Sending Bandwidth");
            nBWSentCol.setCellValueFactory(
                    new PropertyValueFactory<NetworkStats, Double>("NI_BWSent"));
            TableColumn nBWReceivedCol = new TableColumn("Receiving Bandwidth");
            nBWReceivedCol.setCellValueFactory(
                    new PropertyValueFactory<NetworkStats, Double>("NI_BWReceived"));
            TableColumn nStatus = new TableColumn("Status");
            nStatus.setCellValueFactory(
                    new PropertyValueFactory<NetworkStats, String>("NI_Status"));
            tblNetStats.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
            tblNetStats.getColumns().addAll(npidCol, nProtocolCol, nPrgCol,
                    nUserCol, nBWSentCol, nBWReceivedCol, nStatus);

        } catch (Exception ex) {
            LOGGER.error("Exception in createProcessTable() in HomeScreen.java" + ex.getMessage());
        }
    }

    /**
     * Create the memory info table with appropriate data types for each column
     * and the link to appropriate field to which it is bound.
     */
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
            tblFreeMemory.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
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
            tblVmStatDisk.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
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
            tblVmStat.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
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
            tblIOStats.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
            tblIOStats.getColumns().addAll(ioDiskNameCol, ioTransferCol, ioKbReadsCol,
                    ioKbWritesCol, ioTotBlocksReadCol, ioTotBlocksWriteCol);
        } catch (Exception ex) {
            LOGGER.error("Exception in createMemoryTable() in HomeScreen.java" + ex.getMessage());
        }
    }

    private void refreshData() {
        try {

            new AnimationTimer() {

                @Override
                public void handle(long now) {
           // timer.scheduleAtFixedRate(new TimerTask() {
                    //timer.schedule(new TimerTask() {
                    //  @Override
                    // public void run() {
                    // Platform.runLater(new Runnable() {

//                        @Override
//                        public void run() {
                    System.out.print("I would be called every 2 seconds");
//                    lblTimer.setText("Timer refreshed");
                    int i = 0;
                    while (i < 4) {

                        CpuInfo c = new CpuInfo();
                        List lst;
                        switch (i) {
                            case 0:
                                //tabProcessInfo.setText("CPU Info");
                                //tabProcessInfo.setClosable(false);
                                lst = c.getCpu(CMDTOP);
                                bindListToTable(lst, 0);
                                tabProcessInfo.setContent(tblProcessInfo);
                                tabPane.getTabs().add(tabProcessInfo);
                                break;
                            case 1:
                                //tabMemInfo.setText("Memory Info");

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
                                        tblFreeMemory, tblVmStatDisk, tblVmStat, tblIOStats);
                                tabMemInfo.setContent(vBox);
                                tabPane.getTabs().add(tabMemInfo);
                                break;
                            case 2:
                                //tabNetwkInfo.setText("Network Info");

                                lst = c.networkStats(CMDNETSTATTCP);
                                lst.addAll(c.networkStats(CMDNETSTATUDP));
                                bindListToTable(lst, 2);

                                tabNetwkInfo.setContent(tblNetStats);
                                tabPane.getTabs().add(tabNetwkInfo);
                                break;
                            case 3:
                                //tabSysInfo.setText("System Details");
                                SystemDetails sd = c.cpuInformation();
                                String sysName = System.getenv("HOSTNAME");
                                String name[] = sysName.replace(".", ",").split(",");

                                lblCompNameVal.setText(name[0]);

                                lblSysTypeVal.setText(sd.getSYSTEM_TYPE());

                                lblProcessorVal.setText(sd.getPROCESSOR());

                                lblByteOrderVal.setText(sd.getBYTE_ORDER());
                                tabSysInfo.setContent(grid);
                                tabPane.getTabs().add(tabSysInfo);
                                break;
                            default:
                                break;
                        }
                        i++;
                    }
                    //        }
                    //});
                }
            }.start();
        } catch (Exception ex) {
            System.out.println("Exception in refreshData():" + ex.getMessage());
        }
    }
}
