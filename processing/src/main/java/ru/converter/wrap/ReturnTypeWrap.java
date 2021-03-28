package ru.converter.wrap;

import ru.converter.util.ClassUtil;

import javax.lang.model.element.ElementKind;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.TypeMirror;
import java.util.List;
import java.util.stream.Collectors;

public class ReturnTypeWrap {

    private final String name;
    private final List<FieldWrap> fields;

    public ReturnTypeWrap(TypeMirror parameter) {
        this.name = ClassUtil.getClassName(parameter.toString());
        this.fields = ((DeclaredType) parameter).asElement().getEnclosedElements()
                .stream()
                .filter(el -> el.getKind() == ElementKind.FIELD)
                .map(FieldWrap::new)
                .collect(Collectors.toList());
    }

    public String getName() {
        return name;
    }

    public List<FieldWrap> getFields() {
        return fields;
    }
}
