package com.atsebak.kernel.project.ui;

import com.atsebak.kernel.project.builder.CppModuleBuilder;
import com.intellij.ide.util.projectWizard.ModuleWizardStep;
import com.intellij.ide.util.projectWizard.WizardContext;
import com.intellij.openapi.Disposable;

import javax.swing.*;


public class DeviceDriverStep extends ModuleWizardStep implements Disposable {
    private final WizardContext wizardContext;
    private final CppModuleBuilder cppModuleBuilder;
    private JPanel main;

    public DeviceDriverStep(WizardContext wizardContext, CppModuleBuilder cppModuleBuilder) {
        this.wizardContext = wizardContext;
        this.cppModuleBuilder = cppModuleBuilder;
    }

    @Override
    public void dispose() {

    }

    @Override
    public JComponent getComponent() {
        return main;
    }

    @Override
    public void updateDataModel() {

    }
}
