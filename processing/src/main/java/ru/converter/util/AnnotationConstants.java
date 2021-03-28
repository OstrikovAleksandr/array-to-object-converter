package ru.converter.util;

public enum  AnnotationConstants {

    COMPONENT("@Component", ""),
    SERVICE("@Service", "")

    ;

    private final String str;
    private final String importPath;

    AnnotationConstants(String str, String importPath) {
        this.str = str;
        this.importPath = importPath;
    }

    @Override
    public String toString() {
        return str;
    }
}
