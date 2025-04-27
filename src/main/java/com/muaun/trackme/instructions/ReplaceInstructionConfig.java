package com.muaun.trackme.instructions;

import com.muaun.trackme.base.BaseTrackConfig;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

public class ReplaceInstructionConfig implements BaseTrackConfig, Serializable {

    private final boolean isEnabled;
    private final Set<String> include;
    private final Set<String> exclude;
    private final String extensionName;
    private final Set<ReplaceInstructionParameter> instructions;

    public ReplaceInstructionConfig(
            boolean isEnabled,
            Set<String> include,
            Set<String> exclude,
            String extensionName,
            Set<ReplaceInstructionParameter> instructions) {
        this.isEnabled = isEnabled;
        this.include = include;
        this.exclude = exclude;
        this.extensionName = extensionName;
        this.instructions = instructions;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }

    @Override
    public Set<String> getInclude() {
        return include;
    }

    @Override
    public Set<String> getExclude() {
        return exclude;
    }

    @Override
    public String getExtensionName() {
        return extensionName;
    }

    public Set<ReplaceInstructionParameter> getInstructions() {
        return instructions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReplaceInstructionConfig that = (ReplaceInstructionConfig) o;
        return isEnabled == that.isEnabled &&
                Objects.equals(include, that.include) &&
                Objects.equals(exclude, that.exclude) &&
                Objects.equals(extensionName, that.extensionName) &&
                Objects.equals(instructions, that.instructions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(isEnabled, include, exclude, extensionName, instructions);
    }

    @Override
    public String toString() {
        return "ReplaceInstructionConfig{" +
                "isEnabled=" + isEnabled +
                ", include=" + include +
                ", exclude=" + exclude +
                ", extensionName='" + extensionName + '\'' +
                ", instructions=" + instructions +
                '}';
    }

    public static class ReplaceInstructionParameter implements Serializable {

        private final String owner;
        private final String name;
        private final String descriptor;
        private final String proxyOwner;

        public ReplaceInstructionParameter(String owner, String name, String descriptor, String proxyOwner) {
            this.owner = owner;
            this.name = name;
            this.descriptor = descriptor;
            this.proxyOwner = proxyOwner;
        }

        public String getOwner() {
            return owner;
        }

        public String getName() {
            return name;
        }

        public String getDescriptor() {
            return descriptor;
        }

        public String getProxyOwner() {
            return proxyOwner;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            ReplaceInstructionParameter that = (ReplaceInstructionParameter) o;
            return Objects.equals(owner, that.owner) &&
                    Objects.equals(name, that.name) &&
                    Objects.equals(descriptor, that.descriptor) &&
                    Objects.equals(proxyOwner, that.proxyOwner);
        }

        @Override
        public int hashCode() {
            return Objects.hash(owner, name, descriptor, proxyOwner);
        }

        @Override
        public String toString() {
            return "ReplaceInstructionParameter{" +
                    "owner='" + owner + '\'' +
                    ", name='" + name + '\'' +
                    ", descriptor='" + descriptor + '\'' +
                    ", proxyOwner='" + proxyOwner + '\'' +
                    '}';
        }
    }
}
