package com.atsebak.kernel.project.builder;


import com.atsebak.kernel.locale.KernelBundle;
import com.atsebak.kernel.utils.KernelIcons;
import com.intellij.ide.util.projectWizard.ModuleWizardStep;
import com.intellij.ide.util.projectWizard.ProjectWizardStepFactory;
import com.intellij.ide.util.projectWizard.WizardContext;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.components.ApplicationComponent;
import com.intellij.openapi.module.ModuleType;
import com.intellij.openapi.module.ModuleTypeManager;
import com.intellij.openapi.projectRoots.Sdk;
import com.intellij.openapi.roots.ui.configuration.ModulesProvider;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.util.List;

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
        final List<Sdk> list = CppSdkType.getInstance().getCppSdks();
        final ModuleWizardStep sourceModuleWizardStep = ProjectWizardStepFactory.getInstance().createSourcePathsStep(wizardContext, cppModuleBuilder, null, null);
//        final ModuleWizardStep createSampleCode = new CreateEntryCodeStep(cppModuleBuilder);

//        if (list.size() == 0) {
//            return new ModuleWizardStep[] { new ChooseCppSdkStep(cppModuleBuilder, wizardContext),sourceModuleWizardStep, createSampleCode };
//        }
        return new ModuleWizardStep[]{sourceModuleWizardStep};
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
        return "CppTools.ModuleType";
    }

    public void initComponent() {
        ModuleTypeManager.getInstance().registerModuleType(this);
    }

    public void disposeComponent() {
    }

}