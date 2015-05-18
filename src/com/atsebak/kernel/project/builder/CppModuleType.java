package com.atsebak.kernel.project.builder;


import com.atsebak.kernel.locale.KernelBundle;
import com.atsebak.kernel.project.ui.DeviceDriverStep;
import com.atsebak.kernel.utils.KernelIcons;
import com.intellij.ide.util.projectWizard.ModuleWizardStep;
import com.intellij.ide.util.projectWizard.ProjectWizardStepFactory;
import com.intellij.ide.util.projectWizard.WizardContext;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.components.ApplicationComponent;
import com.intellij.openapi.module.ModuleType;
import com.intellij.openapi.module.ModuleTypeManager;
import com.intellij.openapi.roots.ui.configuration.ModulesProvider;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

public class CppModuleType extends ModuleType<CppModuleBuilder> implements ApplicationComponent {
    public CppModuleType() {
        super("Linux/C++");
    }

    public static CppModuleType getInstance() {
        return ApplicationManager.getApplication().getComponent(CppModuleType.class);
    }

    public CppModuleBuilder createModuleBuilder() {
        return new CppModuleBuilder();
    }

    public String getName() {
        return KernelBundle.message("linux.kernel.module");
    }

    public ModuleWizardStep[] createWizardSteps(WizardContext wizardContext, CppModuleBuilder cppModuleBuilder, ModulesProvider modulesProvider) {
        //todo place wizard forms
//        final List<Sdk> sdks = CppSdkType.getInstance().getCppSdks();
//        if(cppModuleBuilder != nu)
//        switch (cppModuleBuilder.getKernelProjectType()) {
//            case DRIVER:
////                DeviceDriverStep deviceDriverStep = new DeviceDriverStep(wizardContext, cppModuleBuilder);
////                ChooseCppSdkStep chooseCppSdkStep = new ChooseCppSdkStep(wizardContext, cppModuleBuilder);
////                return new ModuleWizardStep[]{deviceDriverStep};
//            case ANDROIDBSP:
//                break;
//        }
//        return null;
        final ModuleWizardStep sourceModuleWizardStep = ProjectWizardStepFactory.getInstance().createSourcePathsStep(wizardContext, cppModuleBuilder, null, null);
//        final ModuleWizardStep createSampleCode = new CreateEntryCodeStep(cppModuleBuilder);

//        if (list.size() == 0) {
//            return new ModuleWizardStep[] { new ChooseCppSdkStep(cppModuleBuilder, wizardContext),sourceModuleWizardStep, createSampleCode };
//        }
        DeviceDriverStep deviceDriverStep = new DeviceDriverStep(wizardContext, cppModuleBuilder);
        return new ModuleWizardStep[]{sourceModuleWizardStep, deviceDriverStep};
//        return new ModuleWizardStep[] { sourceModuleWizardStep, createSampleCode };
    }

    public String getDescription() {
        return KernelBundle.message("linux.kernel.module.description");
    }

    public Icon getBigIcon() {
        return KernelIcons.Linux;
    }

    public Icon getNodeIcon(boolean isOpened) {
        return KernelIcons.Linux;
    }

    @NonNls
    @NotNull
    public String getComponentName() {
        return "LinuxKernel.ModuleType";
    }

    public void initComponent() {
        ModuleTypeManager.getInstance().registerModuleType(this);
    }

    public void disposeComponent() {
    }

}