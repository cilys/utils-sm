package com.cilys.utils.sm;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.security.Security;

public class BaseSmUtils {
    static {
        Security.addProvider(new BouncyCastleProvider());
    }

    protected final static String ENCODING = "UTF-8";

    private static boolean enableLog = false;

    public static void setEnableLog(boolean enableLog) {
        BaseSmUtils.enableLog = enableLog;
    }

    protected static <T>void out(T t) {
        if (enableLog) {
            System.out.println(t);
        }
    }

    protected static <T> void err(T t) {
        if (enableLog) {
            System.err.println(t);
        }
    }

    protected static void printStackTrace(Throwable e) {
        if (enableLog) {
            e.printStackTrace();
        }
    }
}
