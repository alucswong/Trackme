package com.muaun.trackme.base;

import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.ClassNode;

public abstract class BaseTrackClassNode extends ClassNode {

    protected final BaseTrackConfig trackConfig;

    public BaseTrackClassNode(BaseTrackConfig trackConfig) {
        super(Opcodes.ASM7);
        this.trackConfig = trackConfig;
    }

    public void log(java.util.function.Supplier<String> msgSupplier) {
//        LogPrint.normal(trackConfig.getExtensionName(), msgSupplier);
    }
}
