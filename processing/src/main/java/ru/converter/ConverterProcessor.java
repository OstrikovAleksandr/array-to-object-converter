package ru.converter;

import ru.converter.wrap.ClassWrap;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.tools.JavaFileObject;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@SupportedAnnotationTypes(
        "ru.converter.annotation.Convertor")
@SupportedSourceVersion(SourceVersion.RELEASE_8)
public class ConverterProcessor extends AbstractProcessor {

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        for (TypeElement annotation : annotations) {

            Set<? extends Element> annotatedElements = roundEnv.getElementsAnnotatedWith(annotation);

            List<? extends TypeElement> elements = annotatedElements.stream().filter(el -> el instanceof TypeElement).map(el -> (TypeElement) el).collect(Collectors.toList());

            elements
                    .stream()
                    .map(el -> new ClassWrap(el, true))
                    .forEach(this::writeBuilderFile);
        }

        return true;
    }

    private void writeBuilderFile(ClassWrap classWrap) {
        try {
            JavaFileObject builderFile = processingEnv.getFiler().createSourceFile(classWrap.getClassName());
            try (BufferedOutputStream out = new BufferedOutputStream(builderFile.openOutputStream())) {
                classWrap.write(out);
            }
        } catch (IOException ignored) {

        }
    }
}
