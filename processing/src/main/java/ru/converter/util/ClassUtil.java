package ru.converter.util;

public final class ClassUtil {

    private ClassUtil() {}

    public static String getImport(String className) {
        if (className.startsWith("java.lang.")) {
            return null;
        }
        return className;
    }

    public static String getPackage(String className) {
        int lastDot = className.lastIndexOf('.');
        return className.substring(0, lastDot);
    }

    public static String getClassName(String className) {
        int lastDot = className.lastIndexOf('.');
        return className.substring(lastDot + 1);
    }
}
