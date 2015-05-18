package com.atsebak.kernel.project;


import com.atsebak.kernel.project.builder.CppModuleBuilder;
import com.atsebak.kernel.utils.KernelIcons;
import com.intellij.ide.util.projectWizard.WizardContext;
import com.intellij.openapi.module.JavaModuleType;
import com.intellij.openapi.project.Project;
import com.intellij.platform.ProjectTemplate;
import com.intellij.platform.ProjectTemplatesFactory;
import com.intellij.platform.templates.BuilderBasedTemplate;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class KernelSupportProjectTemplatesFactory extends ProjectTemplatesFactory {
    public static final String KERNEL_SUPPORT = "Linux Kernel Support";
    public static final String DEVICE_DRIVER = "Device Driver";
    public static final String ANDROID_BSP = "Android Board Support Package (BSP)";

    public static final ProjectTemplate[] EMPTY_PROJECT_TEMPLATES = new ProjectTemplate[]{};

    @NotNull
    @Override
    public String[] getGroups() {
        return new String[]{KERNEL_SUPPORT};
    }

    @Override
    public Icon getGroupIcon(String group) {
        return KernelIcons.Linux;
    }

    @Override
    public String getParentGroup(String group) {
        return JavaModuleType.BUILD_TOOLS_GROUP;
    }

    @NotNull
    @Override
    public ProjectTemplate[] createTemplates(@Nullable String group, WizardContext context) {
        Project project = context.getProject();
        if (project != null) {
            return EMPTY_PROJECT_TEMPLATES;
        }
        ProjectTemplate[] templates = {
                new CppProjectTemplate(DEVICE_DRIVER, "Linux Device Driver Kernel Module", new CppModuleBuilder.Driver()),
                new CppProjectTemplate(ANDROID_BSP, "Android Board Support Package Project to be used with the" +
                        " <a href='https://source.android.com/source/initializing.html'>Android Kernel</a>", new CppModuleBuilder.AndroidBSP())
        };
        return templates;
    }

    private static class CppProjectTemplate extends BuilderBasedTemplate {

        private final String name;
        private final String description;

        private CppProjectTemplate(String name, String description, CppModuleBuilder builder) {
            super(builder);
            this.name = name;
            this.description = description;
        }

        @NotNull
        @Override
        public String getName() {
            return name;
        }

        @Nullable
        @Override
        public String getDescription() {
            return description;
        }
    }
}
