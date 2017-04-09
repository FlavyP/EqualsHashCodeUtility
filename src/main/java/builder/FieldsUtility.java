package main.java.builder;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

public class FieldsUtility {

    private static final String EQUALS_METHOD = "equals";

    public static boolean hasFieldEqualsImplemented(Field f) {
        if(FieldsUtility.shouldAddField(f)) {
            return true;
        }
        return checkValidityOfMethod(f);
    }

    /**
     * Check if the field is a primitive, enum or if it's in the 'model' package
     * @param f - field to check
     * @return true is field should be added to equals/hashCode method, false otherwise.
     */
    public static boolean shouldAddField(Field f) {
        Class<?> fieldClass = f.getType();
        System.out.println("Field class.getName = " + fieldClass.getName());
        return fieldClass.isEnum() || fieldClass.isPrimitive() || fieldClass.getName().contains("model")|| fieldClass.getName().contains("java");
    }

    //TODO: check here if only length == 1 is enough
    //TODO: since the param type will always pe Object anyways
    //TODO: also, check and test again if .getDeclaringClass() == getType() maps to that @Override
    //TODO: will get type.getMethod(Object.class) return the original equals from Object.class?
    //TODO: If your class overrides equals and has 1 param of type int, how is that?
    private static boolean checkValidityOfMethod(Field f) {
        try {
            Method method = f.getType().getMethod(EQUALS_METHOD, new Class[]{Object.class});
            System.out.println("METHOD = " + method.getDeclaringClass());
            System.out.println("FIELD TYPE = " + f.getType());
            Parameter[] methodParams = method.getParameters();
            return methodParams.length == 1 && methodParams[0].getType() == Object.class && method.getDeclaringClass() == f.getType();
        } catch (NoSuchMethodException e) {
            System.out.println("error");
            return false;
        }
    }
}
