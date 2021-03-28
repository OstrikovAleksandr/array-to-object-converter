package ru.converter.wrap;

import ru.converter.util.ClassUtil;

import javax.lang.model.element.Element;

public class FieldWrap {

    private final String name;
    private final String type;
    private final String importPath;

    public FieldWrap(Element element) {
        this.name = element.getSimpleName().toString();
        this.type = ClassUtil.getClassName(element.asType().toString());
        this.importPath = ClassUtil.getImport(element.asType().toString());
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getImportPath() {
        return importPath;
    }
}
