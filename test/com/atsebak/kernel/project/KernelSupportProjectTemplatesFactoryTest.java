package com.atsebak.kernel.project;

import com.atsebak.kernel.project.builder.CppModuleBuilder;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class KernelSupportProjectTemplatesFactoryTest {
    @Test
    public void testAndroidBSPId() {
        CppModuleBuilder.AndroidBSP androidBSP = new CppModuleBuilder.AndroidBSP();
        assertEquals(androidBSP.getBuilderId(), "linux.android.bsp");
    }

    @Test
    public void testDriverId() {
        CppModuleBuilder.Driver driver = new CppModuleBuilder.Driver();
        assertEquals(driver.getBuilderId(), "linux.driver");
    }
}