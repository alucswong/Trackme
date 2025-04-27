package com.muaun.trackme.instructions;

import com.muaun.trackme.base.BaseTrackClassNode;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;

class ReplaceInstructionClassVisitor extends BaseTrackClassNode {

    private final ClassVisitor nextClassVisitor;

    public ReplaceInstructionClassVisitor(ClassVisitor nextClassVisitor, ReplaceInstructionConfig trackConfig) {
        super(trackConfig);
        this.nextClassVisitor = nextClassVisitor;
    }

    @Override
    public void visitEnd() {
        super.visitEnd();
        accept(nextClassVisitor);
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
        MethodVisitor methodVisitor = super.visitMethod(access, name, descriptor, signature, exceptions);
        return new ReplaceInstructionMethodVisitor(api, methodVisitor, this, (ReplaceInstructionConfig) trackConfig);
    }
}
