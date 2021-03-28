package ru.converter.util;

public enum Constants {

    PUBLIC("public"),
    CLASS("class"),
    PACKAGE("package"),
    IMPORT("import"),
    IMPLEMENTS("implements"),
    RETURN("return"),
    NEW_LINE("\n"),
    NEW("new"),
    SPACE(" "),
    VOID("void"),
    METHOD_TAB("    "),
    LOGIC_TAB("        ")
    ;

    private final String stringValue;

    Constants(String stringValue) {
        this.stringValue = stringValue;
    }

    @Override
    public String toString() {
        return stringValue;
    }
}
