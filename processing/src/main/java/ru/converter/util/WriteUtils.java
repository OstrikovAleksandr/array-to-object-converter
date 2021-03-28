package ru.converter.util;

import ru.converter.wrap.ClassWrap;
import ru.converter.wrap.FieldWrap;
import ru.converter.wrap.MethodWrap;
import ru.converter.wrap.ParameterWrap;
import ru.converter.wrap.ReturnTypeWrap;

import java.util.List;

import static ru.converter.util.Constants.*;

public final class WriteUtils {

    private WriteUtils() { }

    public static byte[] buildLines(ClassWrap classWrap) {

        StringBuilder builder = new StringBuilder();

        writePackage(classWrap, builder);
        writeImports(classWrap, builder);
        writeStartClass(classWrap, builder);

        classWrap.getMethods()
                .forEach(method -> writeMethod(method, builder));

        writeEndClass(builder);

        return builder.toString().getBytes();
    }

    /*
    Метод для формирования пакета класса
     */
    private static void writePackage(ClassWrap classWrap, StringBuilder builder) {
        builder.append(PACKAGE).append(SPACE)
                .append(classWrap.getPackagePath()).append(";")
                .append(NEW_LINE).append(NEW_LINE);
    }

    /*
    Метод для добавления списка импортов
     */
    private static void writeImports(ClassWrap classWrap, StringBuilder builder) {
        classWrap.getImports()
                .forEach(value -> builder.append(IMPORT).append(SPACE).append(value).append(";").append(NEW_LINE).append(NEW_LINE));
    }

    /*
    Метод для добавления начала класса
     */
    private static void writeStartClass(ClassWrap classWrap, StringBuilder builder) {
        builder.append(PUBLIC).append(SPACE).append(CLASS).append(SPACE).append(classWrap.getClassName()).append(SPACE);
        if (classWrap.getInterfaceName() != null) {
            builder.append(IMPLEMENTS).append(SPACE).append(classWrap.getInterfaceName()).append(SPACE);
        }
        builder.append("{").append(NEW_LINE).append(NEW_LINE);
    }

    /*
    Метод для добавления методов
     */
    private static void writeMethod(MethodWrap methodWrap, StringBuilder builder) {
        builder.append(METHOD_TAB).append(PUBLIC).append(SPACE);

        if (methodWrap.getReturnType() != null) {
            builder.append(methodWrap.getReturnType().getName()).append(SPACE);
        } else {
            builder.append(VOID).append(SPACE);
        }

        builder.append(methodWrap.getName()).append(SPACE).append("(");

        writeParams(methodWrap.getParameters(), builder);

        builder.append(") {").append(NEW_LINE);

        if (methodWrap.getReturnType() != null) {
            writeLogic(methodWrap.getReturnType(), methodWrap.getParameters().get(0), builder);
        }

        builder.append(METHOD_TAB).append("}");
    }

    /*
    Метод добавления логики
     */
    private static void writeLogic(ReturnTypeWrap returnTypeWrap, ParameterWrap parameterWrap, StringBuilder builder) {
        String var = returnTypeWrap.getName().toLowerCase();

        builder.append(LOGIC_TAB)
                .append(returnTypeWrap.getName())
                .append(SPACE)
                .append(var)
                .append(SPACE)
                .append("=")
                .append(SPACE)
                .append(NEW)
                .append(SPACE)
                .append(returnTypeWrap.getName())
                .append("()")
                .append(";")
                .append(NEW_LINE);

        for (int index = 0; index < returnTypeWrap.getFields().size(); index++) {
            writeSetData(var, returnTypeWrap.getFields().get(index), parameterWrap.getName(), String.valueOf(index), builder);
        }

        builder.append(LOGIC_TAB)
                .append(RETURN)
                .append(SPACE)
                .append(var)
                .append(";")
                .append(NEW_LINE);
    }

    /*
    Метод сеттинга данных
     */
    private static void writeSetData(String var, FieldWrap fieldWrap, String data, String index, StringBuilder builder) {
        builder.append(LOGIC_TAB)
                .append(var)
                .append(".")
                .append("set")
                .append(String.valueOf(fieldWrap.getName().charAt(0)).toUpperCase())
                .append(fieldWrap.getName().substring(1))
                .append("(")
                .append("(")
                .append(fieldWrap.getType())
                .append(")")
                .append(SPACE)
                .append(data)
                .append("[")
                .append(index)
                .append("]")
                .append(")")
                .append(";")
                .append(NEW_LINE);
    }

    /*
    Метод для добавления параметров метода
     */
    private static void writeParams(List<ParameterWrap> parameterWraps, StringBuilder builder) {
        StringBuilder sb = new StringBuilder();
        parameterWraps
                .forEach(param -> sb.append(param.getType()).append(SPACE).append(param.getName()).append(", "));

        builder.append(sb.substring(0, sb.lastIndexOf(",")));
    }

    /*
    Метод завершения класса
     */
    private static void writeEndClass(StringBuilder builder) {
        builder.append(NEW_LINE).append("}").append(NEW_LINE);
    }
}
