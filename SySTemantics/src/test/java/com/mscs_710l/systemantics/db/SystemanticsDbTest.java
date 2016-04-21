/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mscs_710l.systemantics.db;

import com.mscs_710l.systemantics.pojo.FreeMemory;
import com.mscs_710l.systemantics.pojo.IOStats;
import com.mscs_710l.systemantics.pojo.NetworkStats;
import com.mscs_710l.systemantics.pojo.ProcessInfo;
import com.mscs_710l.systemantics.pojo.VirtualDiskInfo;
import com.mscs_710l.systemantics.pojo.VirtualMemoryStats;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author root
 */
public class SystemanticsDbTest {
  
  public SystemanticsDbTest() {
  }
  
  @BeforeClass
  public static void setUpClass() {
  }
  
  @AfterClass
  public static void tearDownClass() {
  }
  
  @Before
  public void setUp() {
  }
  
  @After
  public void tearDown() {
  }

  /**
   * Test of saveSystematic method, of class SystemanticsDb.
   */
  @Test
  public void testSaveSystematic() {
    System.out.println("saveSystematic");
    SystemanticsDb.saveSystematic();
    
  }

  /**
   * Test of saveFreeMemory method, of class SystemanticsDb.
   */
  @Test
  public void testSaveFreeMemory() {
    System.out.println("saveFreeMemory");
    List<FreeMemory> fmList = null;
    SystemanticsDb instance = new SystemanticsDb();
    String expResult = "";
    String result = instance.saveFreeMemory(fmList);
    assertEquals(expResult, result);
    
  }

  /**
   * Test of saveProcessInfo method, of class SystemanticsDb.
   */
  @Test
  public void testSaveProcessInfo() {
    System.out.println("saveProcessInfo");
    List<ProcessInfo> processInfosList = null;
    SystemanticsDb instance = new SystemanticsDb();
    String expResult = "";
    String result = instance.saveProcessInfo(processInfosList);
    assertEquals(expResult, result);
    
  }

  /**
   * Test of saveNetworkStats method, of class SystemanticsDb.
   */
  @Test
  public void testSaveNetworkStats() {
    System.out.println("saveNetworkStats");
    List<NetworkStats> networkStatList = null;
    SystemanticsDb instance = new SystemanticsDb();
    String expResult = "";
    String result = instance.saveNetworkStats(networkStatList);
    assertEquals(expResult, result);
    
  }

  /**
   * Test of saveVMStats method, of class SystemanticsDb.
   */
  @Test
  public void testSaveVMStats() {
    System.out.println("saveVMStats");
    List<VirtualMemoryStats> virtualMemoryStatList = null;
    SystemanticsDb instance = new SystemanticsDb();
    String expResult = "";
    String result = instance.saveVMStats(virtualMemoryStatList);
    assertEquals(expResult, result);
    
  }

  /**
   * Test of saveVirtualDiskInfo method, of class SystemanticsDb.
   */
  @Test
  public void testSaveVirtualDiskInfo() {
    System.out.println("saveVirtualDiskInfo");
    List<VirtualDiskInfo> virtualDiskInfoList = null;
    SystemanticsDb instance = new SystemanticsDb();
    String expResult = "";
    String result = instance.saveVirtualDiskInfo(virtualDiskInfoList);
    assertEquals(expResult, result);
    
  }

  /**
   * Test of saveIOStats method, of class SystemanticsDb.
   */
  @Test
  public void testSaveIOStats() {
    System.out.println("saveIOStats");
    List<IOStats> iOStatsList = null;
    SystemanticsDb instance = new SystemanticsDb();
    String expResult = "";
    String result = instance.saveIOStats(iOStatsList);
    assertEquals(expResult, result);
    
  }
  
}
