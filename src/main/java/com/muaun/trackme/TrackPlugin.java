package com.muaun.trackme;

import com.android.build.api.instrumentation.FramesComputationMode;
import com.android.build.api.instrumentation.InstrumentationScope;
import com.android.build.api.variant.AndroidComponentsExtension;
import com.android.build.api.variant.Variant;
import com.muaun.trackme.instructions.ReplaceInstructionAsmClassVisitorFactory;
import com.muaun.trackme.instructions.ReplaceInstructionConfig;
import com.muaun.trackme.instructions.ReplaceInstructionPluginParameter;

import org.gradle.api.Plugin;
import org.gradle.api.Project;

import java.util.Set;
import java.util.stream.Collectors;

public class TrackPlugin implements Plugin<Project> {

    private final String viewClickTrack = "viewClickTrack";
    private final String composeClickTrack = "composeClickTrack";
    private final String toastTrack = "toastTrack";
    private final String replaceClassTrack = "replaceClassTrack";
    private final String optimizedThreadTrack = "optimizedThreadTrack";
    private final String replaceFieldTrack = "replaceFieldTrack";
    private final String replaceMethodTrack = "replaceMethodTrack";

    @Override
    public void apply(Project project) {
        // Create extensions
        //        project.getExtensions().create(viewClickTrack, ViewClickPluginParameter.class);
        //        project.getExtensions().create(composeClickTrack, ComposeClickPluginParameter.class);
        //        project.getExtensions().create(toastTrack, ToastPluginParameter.class);
        //        project.getExtensions().create(replaceClassTrack, ReplaceClassPluginParameter.class);
        //        project.getExtensions().create(optimizedThreadTrack, OptimizedThreadPluginParameter.class);
        project.getExtensions().create(replaceFieldTrack, ReplaceInstructionPluginParameter.class);
        project.getExtensions().create(replaceMethodTrack, ReplaceInstructionPluginParameter.class);

        // Get AndroidComponentsExtension
        AndroidComponentsExtension androidComponents = project.getExtensions()
                .getByType(AndroidComponentsExtension.class);

        // Process all variants using VariantSelector.all()
        androidComponents.onVariants(androidComponents.selector().all(), variant -> {
            //            handleViewClickTrack(project, (Variant) variant);
            //            handleComposeClickTrack(project, (Variant) variant);
            //            handleReplaceClassTrack(project, (Variant) variant);
            //            handleToastTrack(project, (Variant) variant);
            //            handleOptimizedThreadTrack(project, (Variant) variant);
            handleReplaceInstructionTrack(project, (Variant) variant, replaceFieldTrack);
            handleReplaceInstructionTrack(project, (Variant) variant, replaceMethodTrack);
            ((Variant) variant).getInstrumentation().setAsmFramesComputationMode(FramesComputationMode.COPY_FRAMES);
        });
    }

    //    private void handleViewClickTrack(Project project, Variant variant) {
    //        ViewClickPluginParameter pluginParameter = project.getExtensions()
    //                .findByType(ViewClickPluginParameter.class);
    //        if (pluginParameter == null) {
    //            return;
    //        }
    //        String onClickClass = pluginParameter.getOnClickClass();
    //        String onClickMethodName = pluginParameter.getOnClickMethodName();
    //        if (onClickClass == null || onClickClass.isEmpty() || onClickMethodName == null || onClickMethodName.isEmpty()) {
    //            return;
    //        }
    //
    //        variant.getInstrumentation().transformClassesWith(
    //                ViewClickAsmClassVisitorFactory.class,
    //                InstrumentationScope.ALL,
    //                params -> {
    //                    params.getTrackConfig().set(new ViewClickConfig(
    //                            pluginParameter.isEnabled(),
    //                            pluginParameter.getInclude(),
    //                            pluginParameter.getExclude(),
    //                            viewClickTrack,
    //                            onClickClass,
    //                            onClickMethodName,
    //                            pluginParameter.getUncheckViewOnClickAnnotation()
    //                    ));
    //                    return null;
    //                }
    //        );
    //    }

    //    private void handleComposeClickTrack(Project project, Variant variant) {
    //        ComposeClickPluginParameter pluginParameter = project.getExtensions()
    //                .findByType(ComposeClickPluginParameter.class);
    //        if (pluginParameter == null) {
    //            return;
    //        }
    //        String onClickClass = pluginParameter.getOnClickClass();
    //        if (onClickClass == null || onClickClass.isEmpty()) {
    //            return;
    //        }
    //
    //        variant.getInstrumentation().transformClassesWith(
    //                ComposeClickAsmClassVisitorFactory.class,
    //                InstrumentationScope.ALL,
    //                params -> {
    //                    params.getTrackConfig().set(new ComposeClickConfig(
    //                            pluginParameter.isEnabled(),
    //                            Collections.emptySet(),
    //                            Collections.emptySet(),
    //                            composeClickTrack,
    //                            onClickClass,
    //                            pluginParameter.getOnClickWhiteList()
    //                    ));
    //                    return null;
    //                }
    //        );
    //    }

    //    private void handleReplaceClassTrack(Project project, Variant variant) {
    //        ReplaceClassPluginParameter pluginParameter = project.getExtensions()
    //                .findByType(ReplaceClassPluginParameter.class);
    //        if (pluginParameter == null) {
    //            return;
    //        }
    //        String originClass = pluginParameter.getOriginClass();
    //        String targetClass = pluginParameter.getTargetClass();
    //        if (originClass == null || originClass.isEmpty() || targetClass == null || targetClass.isEmpty()) {
    //            return;
    //        }
    //
    //        variant.getInstrumentation().transformClassesWith(
    //                ReplaceClassAsmClassVisitorFactory.class,
    //                InstrumentationScope.ALL,
    //                params -> {
    //                    params.getTrackConfig().set(new ReplaceClassConfig(
    //                            pluginParameter.isEnabled(),
    //                            pluginParameter.getInclude(),
    //                            pluginParameter.getExclude(),
    //                            replaceClassTrack,
    //                            originClass,
    //                            targetClass
    //                    ));
    //                    return null;
    //                }
    //        );
    //    }

    //    private void handleToastTrack(Project project, Variant variant) {
    //        ToastPluginParameter pluginParameter = project.getExtensions()
    //                .findByType(ToastPluginParameter.class);
    //        if (pluginParameter == null) {
    //            return;
    //        }
    //        String proxyOwner = pluginParameter.getProxyOwner();
    //        if (proxyOwner == null || proxyOwner.isEmpty()) {
    //            return;
    //        }
    //
    //        handleReplaceInstructionTrack(
    //                variant,
    //                toastTrack,
    //                pluginParameter.isEnabled(),
    //                pluginParameter.getInclude(),
    //                pluginParameter.getExclude(),
    //                Collections.singleton(new ReplaceInstructionParameter(
    //                        "android/widget/Toast",
    //                        "show",
    //                        "()V",
    //                        proxyOwner
    //                ))
    //        );
    //    }

    //    private void handleOptimizedThreadTrack(Project project, Variant variant) {
    //        OptimizedThreadPluginParameter pluginParameter = project.getExtensions()
    //                .findByType(OptimizedThreadPluginParameter.class);
    //        if (pluginParameter == null) {
    //            return;
    //        }
    //        String proxyOwner = pluginParameter.getProxyOwner();
    //        Set<String> methods = pluginParameter.getMethods();
    //        if (proxyOwner == null || proxyOwner.isEmpty() || methods == null || methods.isEmpty()) {
    //            return;
    //        }
    //
    //        Set<ReplaceInstructionParameter> instructions = methods.stream()
    //                .map(method -> new ReplaceInstructionParameter(
    //                        "java/util/concurrent/Executors",
    //                        method,
    //                        "",
    //                        proxyOwner
    //                ))
    //                .collect(Collectors.toSet());
    //
    //        handleReplaceInstructionTrack(
    //                variant,
    //                optimizedThreadTrack,
    //                pluginParameter.isEnabled(),
    //                pluginParameter.getInclude(),
    //                pluginParameter.getExclude(),
    //                instructions
    //        );
    //    }

    private void handleReplaceInstructionTrack(Project project, Variant variant, String extensionName) {
        ReplaceInstructionPluginParameter pluginParameter = (ReplaceInstructionPluginParameter) project.getExtensions()
                .findByName(extensionName);
        if (pluginParameter == null) {
            return;
        }
        Set<ReplaceInstructionConfig.ReplaceInstructionParameter> instructions = pluginParameter.getInstructions() == null
                ? null
                : pluginParameter.getInstructions().stream()
                .filter(it -> {
                    String owner = it.getOwner();
                    String name = it.getName();
                    String proxyOwner = it.getProxyOwner();
                    return owner != null && !owner.isEmpty() && name != null && !name.isEmpty() && proxyOwner != null && !proxyOwner.isEmpty();
                })
                .map(it -> new ReplaceInstructionConfig.ReplaceInstructionParameter(
                        replacePeriodWithSlash(it.getOwner()),
                        it.getName(),
                        it.getDescriptor(),
                        it.getProxyOwner()
                ))
                .collect(Collectors.toSet());

        if (instructions == null || instructions.isEmpty()) {
            return;
        }

        handleReplaceInstructionTrack(
                variant,
                extensionName,
                pluginParameter.isEnabled(),
                pluginParameter.getInclude(),
                pluginParameter.getExclude(),
                instructions
        );
    }

    private void handleReplaceInstructionTrack(
            Variant variant,
            String extensionName,
            boolean isEnabled,
            Set<String> include,
            Set<String> exclude,
            Set<ReplaceInstructionConfig.ReplaceInstructionParameter> instructions
    ) {
        if (instructions.isEmpty()) {
            return;
        }

        variant.getInstrumentation().transformClassesWith(
                ReplaceInstructionAsmClassVisitorFactory.class,
                InstrumentationScope.ALL,
                params -> {
                    params.getTrackConfig().set(new ReplaceInstructionConfig(
                            isEnabled,
                            include,
                            exclude,
                            extensionName,
                            instructions
                    ));
                    return null;
                }
        );
    }

    private String replacePeriodWithSlash(String className) {
        return className.replace('.', '/');
    }
}