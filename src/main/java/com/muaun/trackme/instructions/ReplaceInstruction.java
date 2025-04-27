package com.muaun.trackme.instructions;

public class ReplaceInstruction {

    private String owner;
    private String name;
    private String descriptor;
    private String proxyOwner;

    public ReplaceInstruction(String owner, String name, String descriptor, String proxyOwner) {
        this.owner = owner;
        this.name = name;
        this.descriptor = descriptor;
        this.proxyOwner = proxyOwner;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescriptor() {
        return descriptor;
    }

    public void setDescriptor(String descriptor) {
        this.descriptor = descriptor;
    }

    public String getProxyOwner() {
        return proxyOwner;
    }

    public void setProxyOwner(String proxyOwner) {
        this.proxyOwner = proxyOwner;
    }
}
