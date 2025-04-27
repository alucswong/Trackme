package com.muaun.trackme.instructions;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.ClassNode;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.muaun.trackme.util.LogPrint;


public class ReplaceInstructionMethodVisitor extends MethodVisitor {

    private final ClassNode classNode;
    private final ReplaceInstructionConfig config;

    public ReplaceInstructionMethodVisitor(int api, MethodVisitor methodVisitor, ClassNode classNode, ReplaceInstructionConfig config) {
        super(api, methodVisitor);
        this.classNode = classNode;
        this.config = config;
    }

    @Override
    public void visitFieldInsn(int opcode, String owner, String name, String descriptor) {
        ReplaceInstructionConfig.ReplaceInstructionParameter find = config.getInstructions().stream()
                .filter(it -> it.getOwner().equals(owner) &&
                        it.getName().equals(name) &&
                        (it.getDescriptor().isEmpty() || it.getDescriptor().equals(descriptor)))
                .findFirst()
                .orElse(null);

        if (find != null && opcode == Opcodes.GETSTATIC) {
            String proxyOwner = replacePeriodWithSlash(find.getProxyOwner());
            super.visitFieldInsn(opcode, proxyOwner, name, descriptor);
        } else {
            super.visitFieldInsn(opcode, owner, name, descriptor);
        }
    }

    @Override
    public void visitMethodInsn(int opcode, String owner, String name, String descriptor, boolean isInterface) {
        ReplaceInstructionConfig.ReplaceInstructionParameter find = config.getInstructions().stream()
                .filter(it -> it.getOwner().equals(owner) &&
                        it.getName().equals(name) &&
                        (it.getDescriptor().isEmpty() || it.getDescriptor().equals(descriptor)))
                .findFirst()
                .orElse(null);

        int mOpcode;
        String mOwner;
        String mDescriptor;

        if (find != null) {
            mOwner = replacePeriodWithSlash(find.getProxyOwner());
            if (opcode == Opcodes.INVOKEVIRTUAL) {
                mOpcode = Opcodes.INVOKESTATIC;
                mDescriptor = insetAsFirstArgument(descriptor, owner);
            } else {
                mOpcode = opcode;
                mDescriptor = descriptor;
            }
        } else {
            mOpcode = opcode;
            mOwner = owner;
            mDescriptor = descriptor;
        }

        super.visitMethodInsn(mOpcode, mOwner, name, mDescriptor, isInterface);
    }

    private String insetAsFirstArgument(String descriptor, String owner) {
        String argumentsDescriptor = descriptor.substring(descriptor.indexOf('(') + 1, descriptor.indexOf(')'));
        List<String> arguments = Arrays.asList(argumentsDescriptor.split(";"));
        arguments = new java.util.ArrayList<>(arguments);
        arguments.add(0, "L" + owner);
        String newArguments = arguments.stream().collect(Collectors.joining(";"));
        return "(" + newArguments + ")" + descriptor.substring(descriptor.indexOf(')') + 1);
    }

    private String replacePeriodWithSlash(String className) {
        return className.replace('.', '/');
    }
}
