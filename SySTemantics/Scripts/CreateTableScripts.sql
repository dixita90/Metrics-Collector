
/*This table maintains information about all the processes that run in the 
system.
*/

CREATE TABLE tblProcessInfo 
(PI_ID INTEGER PRIMARY KEY, 
 PI_PID VARCHAR2,
 PI_Username VARCHAR2, 
 PI_Priority VARCHAR2, 
 PI_Nice INTEGER, 
 PI_Virtual INTEGER, 
 PI_Res VARCHAR2, 
 PI_Shared INTEGER, 
 PI_Status VARCHAR2, 
 PI_PerctCpuUsage LONG, 
 PI_PerctMemUsage LONG, 
 PI_TIME VARCHAR2, 
 PI_Command VARCHAR2);

/*This table maintains information about the system memory.
*/

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


CREATE TABLE tblNetworkInfo(
NI_PID INTEGER PRIMARY KEY,
NI_User VARCHAR2,
NI_Program VARCHAR2,
NI_BWSent LONG,
NI_BWReceived LONG);

CREATE TABLE tblFreeMemory(
FM_ID INTEGER PRIMARY KEY,
FM_NAME VARCHAR2,
FM_TOTAL INTEGER,
FM_USED INTEGER,
FM_SHARED INTEGER,
FM_BUFFCACHE  INTEGER,
FM_AVAILABLE INTEGER);

