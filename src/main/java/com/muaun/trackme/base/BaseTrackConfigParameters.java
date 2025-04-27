package com.muaun.trackme.base;

import com.android.build.api.instrumentation.InstrumentationParameters;

import org.gradle.api.tasks.Input;
import org.gradle.api.provider.Property;

public interface BaseTrackConfigParameters extends InstrumentationParameters {

    @Input
    Property<BaseTrackConfig> getTrackConfig();
}
