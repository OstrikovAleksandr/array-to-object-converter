package ru.converter.wrap;

import ru.converter.util.ClassUtil;

import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.type.ExecutableType;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class MethodWrap {

    private final String name;
    private final ReturnTypeWrap returnType;
    private final List<String> imports;
    private final List<ParameterWrap> parameters;

    public MethodWrap(Element element) {
        String classFullName = ((ExecutableType) element.asType()).getReturnType().toString();
        this.returnType = new ReturnTypeWrap(((ExecutableElement) element).getReturnType());
        this.imports = Collections.singletonList(ClassUtil.getImport(classFullName));
        this.name = element.getSimpleName().toString();
        this.parameters = ((ExecutableElement) element).getParameters()
                .stream()
                .map(ParameterWrap::new)
                .collect(Collectors.toList());
    }

    public String getName() {
        return name;
    }

    public ReturnTypeWrap getReturnType() {
        return returnType;
    }

    public List<ParameterWrap> getParameters() {
        return parameters;
    }

    List<String> getImports() {
        List<String> parameterImports = parameters.stream()
                .map(ParameterWrap::getImportPart)
                .collect(Collectors.toList());
        parameterImports.addAll(returnType.getFields().stream().map(FieldWrap::getImportPath).collect(Collectors.toList()));
        parameterImports.addAll(imports);
        return parameterImports;
    }
}
