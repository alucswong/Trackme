package com.muaun.trackme.util;

import java.util.function.Supplier;

public class LogPrint {

    // Singleton instance
    private static final LogPrint INSTANCE = new LogPrint();

    // Private constructor to prevent instantiation
    private LogPrint() {
    }

    // Static method to access the singleton instance (optional, not strictly needed for method calls)
    public static LogPrint getInstance() {
        return INSTANCE;
    }

    public static void normal(String tag, Supplier<String> msg) {
        System.out.println(buildLog(tag, msg).getNormal());
    }

    public static void error(String tag, Supplier<String> msg) {
        System.out.println(buildLog(tag, msg).getError());
    }

    private static LogUI buildLog(String tag, Supplier<String> msg) {
        return new LogUI("[" + tag + "]: " + msg.get());
    }
}
