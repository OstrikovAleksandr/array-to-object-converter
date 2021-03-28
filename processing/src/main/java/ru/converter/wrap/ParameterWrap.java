package ru.converter.wrap;

import ru.converter.util.ClassUtil;

import javax.lang.model.element.VariableElement;


public class ParameterWrap {


    private final String type;
    private final String name;
    private final String importPart;

    public ParameterWrap(VariableElement parameter) {
        this.type = ClassUtil.getClassName(parameter.asType().toString());
        this.name = parameter.getSimpleName().toString();
        this.importPart = ClassUtil.getImport(parameter.asType().toString());
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public String getImportPart() {
        return importPart;
    }
}
