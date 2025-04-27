package com.muaun.trackme.instructions;

import java.util.Collections;
import java.util.Set;

public class ReplaceInstructionPluginParameter {

    private boolean isEnabled;
    private Set<String> include;
    private Set<String> exclude;
    private Set<ReplaceInstruction> instructions;

    public ReplaceInstructionPluginParameter() {
        this.isEnabled = true;
        this.include = Collections.emptySet();
        this.exclude = Collections.emptySet();
        this.instructions = Collections.emptySet();
    }

    public ReplaceInstructionPluginParameter(
            boolean isEnabled,
            Set<String> include,
            Set<String> exclude,
            Set<ReplaceInstruction> instructions) {
        this.isEnabled = isEnabled;
        this.include = include;
        this.exclude = exclude;
        this.instructions = instructions;
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    public void setEnabled(boolean enabled) {
        this.isEnabled = enabled;
    }

    public Set<String> getInclude() {
        return include;
    }

    public void setInclude(Set<String> include) {
        this.include = include;
    }

    public Set<String> getExclude() {
        return exclude;
    }

    public void setExclude(Set<String> exclude) {
        this.exclude = exclude;
    }

    public Set<ReplaceInstruction> getInstructions() {
        return instructions;
    }

    public void setInstructions(Set<ReplaceInstruction> instructions) {
        this.instructions = instructions;
    }
}