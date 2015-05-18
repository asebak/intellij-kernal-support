package com.atsebak.kernel.utils;

import com.intellij.openapi.application.Application;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.components.ApplicationComponent;
import com.intellij.openapi.options.Configurable;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.util.InvalidDataException;
import com.intellij.openapi.util.JDOMExternalizable;
import com.intellij.openapi.util.WriteExternalException;
import com.intellij.openapi.vfs.VirtualFile;
import org.jdom.Element;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.io.File;

public class CppSupportSettings implements ApplicationComponent, JDOMExternalizable, Configurable {
    private static CppSupportSettings instance;
    private
    @NonNls
    String gccPath = "gcc";
    private String gdbPath = "gdb";

    public static CppSupportSettings getInstance() {
        final Application application = ApplicationManager.getApplication();
        if (application.isUnitTestMode()) {
            return instance;
        }
        return application.getComponent(CppSupportSettings.class);
    }

    public static boolean isGccPath(VirtualFile virtualFile) {
        return !virtualFile.isDirectory() && virtualFile.getNameWithoutExtension().indexOf("gcc") != -1;
    }

    public static String getKernelHeadersPath() {
        File directory = new File("/usr/src/");
        String os = System.getProperty("os.version");
        File[] fList = directory.listFiles();
        for (File file : fList) {
            if (file.getAbsolutePath().contains(os)) {
                return file.getAbsolutePath();
            }
        }
        return "";
    }

    @Override
    public void initComponent() {

    }

    @Override
    public void disposeComponent() {

    }

    @Nls
    @Override
    public String getDisplayName() {
        return "Embedded Linux Kernel Support";
    }

    @Nullable
    @Override
    public String getHelpTopic() {
        return null;
    }

    @Override
    public void readExternal(Element element) throws InvalidDataException {

    }

    @Override
    public void writeExternal(Element element) throws WriteExternalException {

    }

    @NotNull
    @Override
    public String getComponentName() {
        return "LinuxKernel.Name";
    }

    @Nullable
    @Override
    public JComponent createComponent() {
        return null;
    }

    @Override
    public boolean isModified() {
        return false;
    }

    @Override
    public void apply() throws ConfigurationException {

    }

    @Override
    public void reset() {

    }

    @Override
    public void disposeUIResources() {

    }

    public String getGccPath() {
        return gccPath;
    }

    public void setGccPath(String gccPath) {
        this.gccPath = gccPath;
    }

    public String getGdbPath() {
        return gdbPath;
    }

    public void setGdbPath(String gdbPath) {
        this.gdbPath = gdbPath;
    }
}
