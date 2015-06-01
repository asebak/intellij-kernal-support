package com.atsebak.kernel.project.builder;

import com.atsebak.kernel.project.ui.DeviceDriverStep;
import com.atsebak.kernel.utils.KernelIcons;
import com.intellij.ide.util.projectWizard.ModuleBuilder;
import com.intellij.ide.util.projectWizard.ModuleWizardStep;
import com.intellij.ide.util.projectWizard.SourcePathsBuilder;
import com.intellij.ide.util.projectWizard.WizardContext;
import com.intellij.openapi.module.ModuleType;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.projectRoots.Sdk;
import com.intellij.openapi.roots.ModifiableRootModel;
import com.intellij.openapi.roots.ui.configuration.ModulesProvider;
import com.intellij.openapi.util.Pair;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class CppModuleBuilder extends ModuleBuilder implements SourcePathsBuilder {
    private KernelProjectType kernelProjectType;
    private Sdk sdk;
    private String myContentRootPath;
    private List<Pair<String, String>> mySourcePaths;
    private EntryPointType entryPointType;
    private BuildFileType buildFileType;

    public CppModuleBuilder() {

    }

    public CppModuleBuilder(KernelProjectType kernelProjectType) {
        this.kernelProjectType = kernelProjectType;
    }

    @Override
    public ModuleWizardStep[] createWizardSteps(WizardContext wizardContext, ModulesProvider modulesProvider) {
        return super.createWizardSteps(wizardContext, modulesProvider);
    }

    public void setupRootModel(ModifiableRootModel modifiableRootModel) throws ConfigurationException {
        //generates the source
//        if (sdk == null) {
//            sdk = CppSdkType.getInstance().getCppSdks().get(0);
//        }
//        modifiableRootModel.setSdk(sdk);
//
//        CppSupportSettings.CompilerSelectOptions options = CppSupportSettings.CompilerSelectOptions.AUTO;
//
//        final VirtualFile directory = EnvironmentFacade.getSdkHomeDirectory(sdk);
//        if (CppSupportSettings.isGccPath(directory)) {
//            options = CppSupportSettings.CompilerSelectOptions.GCC;
//        } else if (CppSupportSettings.isMsVcDirectory(directory)) {
//            options = CppSupportSettings.CompilerSelectOptions.MSVC;
//        }
//
//        final CppSupportLoader projectSettings = CppSupportLoader.getInstance(modifiableRootModel.getModule().getProject());
//        projectSettings.setCompilerOptions(options);
//
//        final String moduleRootPath = getContentEntryPath();
//        if (moduleRootPath != null) {
//            LocalFileSystem lfs = LocalFileSystem.getInstance();
//            VirtualFile moduleContentRoot = lfs.refreshAndFindFileByPath(FileUtil.toSystemIndependentName(moduleRootPath));
//            if (moduleContentRoot != null) {
//                final ContentEntry contentEntry = modifiableRootModel.addContentEntry(moduleContentRoot);
//
//                if (mySourcePaths != null) {
//                    for (Pair<String, String> p : mySourcePaths) {
//                        final VirtualFile sourcePathDir = VfsUtil.findRelativeFile(p.first, null);
//                        if (sourcePathDir != null) {
//                            contentEntry.addSourceFolder(sourcePathDir, false);
//
//                            boolean cppStyle = entryPointType == EntryPointType.CPPSTYLE;
//
//                            if (entryPointType != null) {
//                                final String fileName =
//                                        cppStyle ?
//                                                TemplateUtils.CPP_MAIN_TEMPLATE_NAME :
//                                                TemplateUtils.C_MAIN_TEMPLATE_NAME;
//                                String name = modifiableRootModel.getModule().getName();
//                                if (cppStyle) name+=".cpp";
//                                else name += ".c";
//                                TemplateUtils.createOrResetFileContentFromTemplate(sourcePathDir, name,fileName);
//                            }
//
//                            if (buildFileType != null) {
//                                final String fileName = /*buildFileType == BuildFileType.MAKEFILE ?*/ TemplateUtils.MAKEFILE_TEMPLATE_NAME;
//
//                                String relativeOutputPath = "out/production/"+getName();
//                                String relativeSourcePath = VfsUtil.getRelativePath(sourcePathDir, moduleContentRoot, '/');
//
//                                TemplateUtils.createOrResetFileContentFromTemplate(
//                                        moduleContentRoot, fileName, fileName,
//                                        "Extension", cppStyle ? "cpp":"c",
//                                        "Executable", getName() + (SystemInfo.isWindows ? ".exe":""),
//                                        "SourcePath", (relativeSourcePath + '/'),
//                                        "OutputPath", (relativeOutputPath + "/")
//                                );
//
//                                VirtualFile child = moduleContentRoot.findChild(fileName);
//                                CppSupportLoader.getInstance(modifiableRootModel.getModule().getProject()).setProjectFile(child != null ? child.getPath():null);
//                            }
//                        }
//                    }
//                }
//            }
//        }
    }

    public ModuleType getModuleType() {
        return CppModuleType.getInstance();
    }

    @Nullable
    public String getContentEntryPath() {
        return myContentRootPath;
    }

    public void setContentEntryPath(final String moduleRootPath) {
        myContentRootPath = moduleRootPath;
    }

    public List<Pair<String, String>> getSourcePaths() {
        return mySourcePaths;
    }

    public void setSourcePaths(final List<Pair<String, String>> paths) {
        mySourcePaths = paths;
    }

    public void addSourcePath(final Pair<String, String> sourcePathInfo) {
        if (mySourcePaths == null) {
            mySourcePaths = new ArrayList<Pair<String, String>>();
        }
        mySourcePaths.add(sourcePathInfo);
    }

    public KernelProjectType getKernelProjectType() {
        return kernelProjectType;
    }

    public Sdk getSdk() {
        return sdk;
    }

    public void setSdk(Sdk sdk) {
        this.sdk = sdk;
    }

    public EntryPointType getEntryPointType() {
        return entryPointType;
    }

    public void setEntryPointType(EntryPointType entryPointType) {
        this.entryPointType = entryPointType;
    }

    public BuildFileType getBuildFileType() {
        return buildFileType;
    }

    public void setBuildFileType(BuildFileType buildFileType) {
        this.buildFileType = buildFileType;
    }

    public static class AndroidBSP extends CppModuleBuilder {
        public AndroidBSP() {
            super(KernelProjectType.ANDROIDBSP);
        }

        @Override
        public ModuleWizardStep[] createWizardSteps(@NotNull WizardContext wizardContext, @NotNull ModulesProvider modulesProvider) {
            return ModuleWizardStep.EMPTY_ARRAY;
        }

        @Override
        public String getBuilderId() {
            return "linux.android.bsp";
        }

        @Override
        public Icon getBigIcon() {
            return KernelIcons.Android;
        }

        @Override
        public Icon getNodeIcon() {
            return KernelIcons.Android;
        }
    }

    public static class Driver extends CppModuleBuilder {
        public Driver() {
            super(KernelProjectType.DRIVER);
        }

        @Override
        public ModuleWizardStep[] createWizardSteps(@NotNull WizardContext wizardContext, @NotNull ModulesProvider modulesProvider) {
            DeviceDriverStep deviceDriverStep = new DeviceDriverStep(wizardContext, this);
            return new ModuleWizardStep[]{deviceDriverStep};
        }

        @Override
        public String getBuilderId() {
            return "linux.driver";
        }

        @Override
        public Icon getBigIcon() {
            return KernelIcons.Driver;
        }

        @Override
        public Icon getNodeIcon() {
            return KernelIcons.Driver;
        }
    }

    public static class AVR extends CppModuleBuilder {
        public AVR() {
            super(KernelProjectType.AVR);
        }

        @Override
        public ModuleWizardStep[] createWizardSteps(@NotNull WizardContext wizardContext, @NotNull ModulesProvider modulesProvider) {
            return ModuleWizardStep.EMPTY_ARRAY;
        }

        @Override
        public String getBuilderId() {
            return "embeddedsystem.avr";
        }

        @Override
        public Icon getBigIcon() {
            return KernelIcons.AVR;
        }

        @Override
        public Icon getNodeIcon() {
            return KernelIcons.AVR;
        }
    }

    public static class Arduino extends CppModuleBuilder {
        public Arduino() {
            super(KernelProjectType.ARDUINO);
        }

        @Override
        public ModuleWizardStep[] createWizardSteps(@NotNull WizardContext wizardContext, @NotNull ModulesProvider modulesProvider) {
            return ModuleWizardStep.EMPTY_ARRAY;
        }

        @Override
        public String getBuilderId() {
            return "embeddedsystem.arduino";
        }

        @Override
        public Icon getBigIcon() {
            return KernelIcons.Arduino;
        }

        @Override
        public Icon getNodeIcon() {
            return KernelIcons.Arduino;
        }
    }
}