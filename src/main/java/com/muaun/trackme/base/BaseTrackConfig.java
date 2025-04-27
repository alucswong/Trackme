package com.muaun.trackme.base;

import java.io.Serializable;
import java.util.Set;

public interface BaseTrackConfig extends Serializable {

    boolean isEnabled();

    Set<String> getInclude();

    Set<String> getExclude();

    String getExtensionName();
}
