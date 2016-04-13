
/*This table maintains information about all the processes that run in the 
system.
*/
DROP TABLE IF EXISTS tblProcessInfo;

CREATE TABLE tblProcessInfo 
(PI_PID VARCHAR2 PRIMARY KEY,
 PI_Username VARCHAR2, 
 PI_Priority VARCHAR2, 
 PI_Nice INTEGER, 
 PI_Virtual INTEGER, 
 PI_Res LONG, 
 PI_Shared INTEGER, 
 PI_Status VARCHAR2, 
 PI_PerctCpuUsage LONG, 
 PI_PerctMemUsage LONG, 
 PI_TIME TIME, 
 PI_Command VARCHAR2);

/*This table maintains information about the system memory.
*/
DROP TABLE IF EXISTS tblMemoryInfo;

CREATE TABLE tblMemoryInfo(
MI_TotalMemory LONG,
MI_UsedMemory LONG,
MI_ActiveMemory LONG,
MI_InactiveMemory LONG,
MI_BufferMemory LONG,
MI_SwapCache LONG,
MI_TotalSwap LONG,
MI_SwapUSed LONG,
MI_SystemCpuTicks LONG,
MI_IdleCpuTicks LONG,
MI_IOWaitCpuTicks LONG,
MI_PagesPagedIn LONG,
MI_PagesPagedOut LONG,
MI_PagesSwappedIn LONG,
MI_PagesSwappedOut LONG,
MI_Interrupts LONG,
MI_CpuContextSwitches,
MI_BootTime LONG);

/*This table maintains information about the network bandwidth used 
by the process. */

DROP TABLE IF EXISTS tblNetworkInfo;

CREATE TABLE tblNetworkInfo(
NI_PID INTEGER PRIMARY KEY,
NI_User VARCHAR2,
NI_Program VARCHAR2,
NI_BWSent LONG,
NI_BWReceived LONG);