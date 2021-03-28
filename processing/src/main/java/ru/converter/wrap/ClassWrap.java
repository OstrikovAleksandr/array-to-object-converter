package ru.converter.wrap;

import ru.converter.util.ClassUtil;
import ru.converter.util.WriteUtils;

import javax.lang.model.element.TypeElement;
import javax.lang.model.type.ExecutableType;
import javax.lang.model.type.TypeKind;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ClassWrap {

    private final String packagePath;
    private final String className;
    private final String interfaceName;
    private final List<String> imports;
    private final List<MethodWrap> methods;

    public ClassWrap(TypeElement element, boolean hasInterface, String... imports) {
        String classFullName = element.getQualifiedName().toString();
        if (hasInterface) {
            this.interfaceName = ClassUtil.getClassName(classFullName);
            this.className = interfaceName + "Impl";
        } else {
            this.className = ClassUtil.getClassName(classFullName);
            this.interfaceName = null;
        }
        this.packagePath = ClassUtil.getPackage(classFullName);
        this.imports = Arrays.asList(imports);
        this.methods = element.getEnclosedElements()
                .stream()
                .filter(el -> ((ExecutableType) el.asType()).getReturnType().getKind() != TypeKind.VOID)
                .map(MethodWrap::new)
                .collect(Collectors.toList());
    }

    public String getClassName() {
        return className;
    }

    public List<String> getImports() {
        List<String> methodImports = methods.stream()
                .map(MethodWrap::getImports)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
        methodImports.addAll(imports);
        return methodImports.stream().filter(Objects::nonNull).distinct().collect(Collectors.toList());
    }

    public List<MethodWrap> getMethods() {
        return methods;
    }

    public String getPackagePath() {
        return packagePath;
    }

    public String getInterfaceName() {
        return interfaceName;
    }

    public void write(BufferedOutputStream outputStream) throws IOException {
        outputStream.write(WriteUtils.buildLines(this));
    }
}
