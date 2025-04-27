package com.muaun.trackme.instructions;

import com.android.build.api.instrumentation.ClassContext;
import com.android.build.api.instrumentation.ClassData;
import com.muaun.trackme.base.BaseTrackAsmClassVisitorFactory;
import com.muaun.trackme.base.BaseTrackClassNode;
import com.muaun.trackme.base.BaseTrackConfigParameters;

import org.objectweb.asm.ClassVisitor;

public abstract class ReplaceInstructionAsmClassVisitorFactory implements BaseTrackAsmClassVisitorFactory<BaseTrackConfigParameters, ReplaceInstructionConfig> {

    @Override
    public BaseTrackClassNode createClassVisitor(ClassContext classContext, ClassVisitor nextClassVisitor) {
        return new ReplaceInstructionClassVisitor(nextClassVisitor, getTrackConfig());
    }

    @Override
    public boolean isTrackEnabled(ClassData classData) {
        ReplaceInstructionConfig trackConfig = getTrackConfig();
        return trackConfig.getInstructions().stream()
                .noneMatch(instruction -> instruction.getProxyOwner().equals(classData.getClassName()));
    }
}
