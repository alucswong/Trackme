package com.muaun.trackme.base;

import com.android.build.api.instrumentation.AsmClassVisitorFactory;
import com.android.build.api.instrumentation.ClassContext;
import com.android.build.api.instrumentation.ClassData;

import org.gradle.api.tasks.Input;
import org.objectweb.asm.ClassVisitor;

import java.util.Collection;
import java.util.Set;
import java.util.regex.Pattern;

public interface BaseTrackAsmClassVisitorFactory<Parameters extends BaseTrackConfigParameters, TrackConfig extends BaseTrackConfig> extends AsmClassVisitorFactory<Parameters> {

    @Input
    default TrackConfig getTrackConfig() {
        return (TrackConfig) getParameters().get().getTrackConfig().get();
    }

    @Override
    BaseTrackClassNode createClassVisitor(ClassContext classContext, ClassVisitor nextClassVisitor);

    @Override
    default boolean isInstrumentable(ClassData classData) {
        TrackConfig trackConfig = getTrackConfig();
        if (!trackConfig.isEnabled()) {
            return false;
        }
        Set<String> include = trackConfig.getInclude();
        Set<String> exclude = trackConfig.getExclude();
        if (include.isEmpty()) {
            if (matches(classData, exclude)) {
                return false;
            }
        } else {
            if (exclude.isEmpty()) {
                if (!matches(classData, include)) {
                    return false;
                }
            } else {
                if (!matches(classData, include) || matches(classData, exclude)) {
                    return false;
                }
            }
        }
        return isTrackEnabled(classData);
    }

    default boolean matches(ClassData classData, Collection<String> rules) {
        for (String item : rules) {
            Pattern regex = Pattern.compile(item);
            if (regex.matcher(classData.getClassName()).matches()) {
                return true;
            }
        }
        return false;
    }

    boolean isTrackEnabled(ClassData classData);
}
