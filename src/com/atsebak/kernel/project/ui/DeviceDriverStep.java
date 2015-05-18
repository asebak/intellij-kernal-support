package com.atsebak.kernel.project.ui;

import com.atsebak.kernel.locale.KernelBundle;
import com.atsebak.kernel.project.builder.CppModuleBuilder;
import com.atsebak.kernel.project.builder.DriverLicenseType;
import com.atsebak.kernel.ui.ComboItem;
import com.atsebak.kernel.utils.CppSupportSettings;
import com.intellij.ide.util.projectWizard.ModuleWizardStep;
import com.intellij.ide.util.projectWizard.WizardContext;
import com.intellij.openapi.Disposable;
import com.intellij.openapi.ui.LabeledComponent;
import com.intellij.openapi.ui.TextFieldWithBrowseButton;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class DeviceDriverStep extends ModuleWizardStep implements Disposable {
    private final WizardContext wizardContext;
    private final CppModuleBuilder cppModuleBuilder;
    private JPanel main;
    private JTextField author;
    private JComboBox license;
    private JCheckBox useHostKernelCheckBox;
    private LabeledComponent<TextFieldWithBrowseButton> kernelSourcePath;
    private JLabel kernelVersion;

    public DeviceDriverStep(WizardContext wizardContext, CppModuleBuilder cppModuleBuilder) {
        this.wizardContext = wizardContext;
        this.cppModuleBuilder = cppModuleBuilder;
        author.setText(System.getProperty("user.name"));
        kernelSourcePath.setEnabled(false);
        license.addItem(new ComboItem(DriverLicenseType.GPL.name(), DriverLicenseType.GPL.name()));
        license.addItem(new ComboItem(DriverLicenseType.BSD.name(), DriverLicenseType.BSD.name()));
        license.addItem(new ComboItem(DriverLicenseType.DUAL.name(), DriverLicenseType.DUAL.name()));
        kernelVersion.setText(KernelBundle.getString("kernel.version") + " " + System.getProperty("os.version"));
        kernelSourcePath.getComponent().setText(CppSupportSettings.getKernelHeadersPath());
        useHostKernelCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                kernelSourcePath.setEnabled(!useHostKernelCheckBox.isSelected());
            }
        });
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
