package com.atsebak.kernel.utils;


import com.intellij.openapi.util.IconLoader;

import javax.swing.*;

public class KernelIcons {
    public static final Icon Linux = load("/linux.png");
    public static final Icon Android = load("/android.png");
    public static final Icon Driver = load("/usb.png");
    public static final Icon AVR = load("/atmelavr.png");
    public static final Icon Arduino = load("/arduino.png");

    private static Icon load(String path) {
        return IconLoader.getIcon(path, KernelIcons.class);
    }

}
