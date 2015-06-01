package com.atsebak.kernel.project;


import com.atsebak.kernel.locale.KernelBundle;
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
    /**
     * Gets the name of the project group it will get stored in
     *
     * @return
     */
    @NotNull
    @Override
    public String[] getGroups() {
        return new String[]{KernelBundle.getString("projects.description")};
    }

    /**
     * Project group Icon
     * @param group
     * @return
     */
    @Override
    public Icon getGroupIcon(String group) {
        return KernelIcons.Linux;
    }

    /**
     * Project group category
     * @param group
     * @return
     */
    @Override
    public String getParentGroup(String group) {
        return JavaModuleType.BUILD_TOOLS_GROUP;
    }

    /**
     * Templates factory initialization
     * @param group
     * @param context
     * @return
     */
    @NotNull
    @Override
    public ProjectTemplate[] createTemplates(@Nullable String group, WizardContext context) {
        Project project = context.getProject();
        if (project != null) {
            return new ProjectTemplate[]{};
        }
        ProjectTemplate[] templates = {
                new CppProjectTemplate(KernelBundle.getString("driver.name"), KernelBundle.getString("driver.description"), new CppModuleBuilder.Driver()),
                new CppProjectTemplate(KernelBundle.getString("androidbsp.name"), KernelBundle.getString("androidbsp.description"), new CppModuleBuilder.AndroidBSP()),
                new CppProjectTemplate(KernelBundle.getString("atmelavr.name"), KernelBundle.getString("atmelavr.description"), new CppModuleBuilder.AVR()),
                new CppProjectTemplate(KernelBundle.getString("arduino.name"), KernelBundle.getString("arduino.description"), new CppModuleBuilder.Arduino())
        };
        return templates;
    }

    /**
     * C++ project template based on the template builder
     */
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
