/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mscs_710l.systemantics.bl;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Siva Chintapalli
 */
public class CpuInfoTest {

    public CpuInfoTest() {
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
     * Test of getCpu method, of class CpuInfo.
     */
    @Test
    public void testGetCpu() {
        System.out.println("getCpu");
        String cmd = "free -m";
        CpuInfo instance = new CpuInfo();
        String expResult = "Success";
        //String result = instance.getCpu(cmd);
        //assertEquals(expResult, result);
    }

    /**
     * Test of memoryStats method, of class CpuInfo.
     */
    @Test
    public void testMemoryStats() {
        System.out.println("memoryStats");
        String cmd = "free -m";
        CpuInfo instance = new CpuInfo();
        String expResult = "Success";
        //String result = instance.memoryStats(cmd);
        //  assertEquals(expResult, result);
    }

    /**
     * Test of virtualMemoryStats method, of class CpuInfo.
     */
    @Test
    public void testVirtualMemoryStats() {
        System.out.println("virtualMemoryStats");
        String cmd = "vmstat -t 1 6";
        CpuInfo instance = new CpuInfo();
        String expResult = "Success";
       //String result = instance.virtualMemoryStats(cmd);
        //assertEquals(expResult, result);

    }

    /**
     * Test of virtualDiskStats method, of class CpuInfo.
     */
    @Test
    public void testVirtualDiskStats() {
        System.out.println("virtualDiskStats");
        String cmd = "vmstat -d -t";
        CpuInfo instance = new CpuInfo();
        String expResult = "Success";
       // String result = instance.virtualDiskStats(cmd);
       // assertEquals(expResult, result);

    }

    /**
     * Test of networkStats method, of class CpuInfo.
     */
    @Test
    public void testNetworkStats() {
        System.out.println("networkStats");
        String cmd = "netstat -e -p -at";
        CpuInfo instance = new CpuInfo();
        String expResult = "Success";
        //String result = instance.networkStats(cmd);
        //assertEquals(expResult, result);
    }

    /**
     * Test of iOStats method, of class CpuInfo.
     */
    @Test
    public void testIOStats() {
        System.out.println("iOStats");
        String cmd = "iostat -d -N";
        CpuInfo instance = new CpuInfo();
        String expResult = "Success";
       // String result = instance.iOStats(cmd);
        //assertEquals(expResult, result);
    }

}
