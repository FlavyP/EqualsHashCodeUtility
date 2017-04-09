package main.java.builder;

import java.lang.reflect.Field;

/**
 * TODO:
 * 1. Add the business logic.
 * 2. Need to check if a class belongs/was created in this project
 *  2.1 Check if it has an @equals/@hashCode method.
 *  2.2 If not - print this fact.
 *  2.3 Still append on it.
 * 3. If variable belongs to JAVA API or isEnum - it's fine, normal append
 * 4. Worth adding the appendSuper anyways, and print if class from extended does not have equals?
 * 5. HashCode generator
 * 6. Extract 'otherObj' into constant - to be used by both EqualsBuilder and HashCodeBuilder
 */
public class EqualsHashCodeUtility {

    private StringBuilder stringBuilder = new StringBuilder();

    public String createEqualsMethod(Class<?> clazz) {
        appendEqualsMethodHeader(clazz);
        appendSupper(clazz);
        appendVariables(clazz);
        appendEqualsMethodFooter();
        return stringBuilder.toString();
    }

    private void appendVariables(Class<?> clazz) {
        Field[] fields = clazz.getDeclaredFields();
        for(Field field : fields) {
            if(FieldsUtility.shouldAddField(field)) {
                appendField(field);
            }
        }
    }

    private void appendField(Field f) {
        stringBuilder.append(".append(");
        stringBuilder.append(f.getName());
        stringBuilder.append(", ");
        stringBuilder.append("otherObj.");
        stringBuilder.append(f.getName());
        appendWithNewLine(")");
    }

    private void appendEqualsMethodHeader(Class<?> clazz) {
        appendWithNewLine("@Override");
        appendWithNewLine("public boolean equals(Object obj) {");
        appendWithNewLine("if (obj == null) { return false; }");
        appendWithNewLine("if (obj == this) { return true; }");
        appendWithNewLine("if (obj.getClass() != getClass()) { ");
        appendWithNewLine("return false; }");
        stringBuilder.append(clazz.getSimpleName());
        stringBuilder.append(" otherObj = (");
        stringBuilder.append(clazz.getSimpleName());
        appendWithNewLine(") obj;");
        appendWithNewLine("return new EqualsBuilder()");
    }

    private void wrongAppendField (Field f) {
        //TODO this needs to be moved into normal append
        if(!FieldsUtility.hasFieldEqualsImplemented(f)) {
            System.out.println("IMPLEMENT EQUALS FOR " + f.getType());
        }
        appendWithNewLine(".appendSuper(otherObj.equals(obj))");
    }

    private void appendSupper(Class<?> clazz) {
        if(!clazz.getSuperclass().isAssignableFrom(Object.class)) {
            appendWithNewLine(".appendSuper(otherObj.equals(obj))");
        }
    }

    private void appendEqualsMethodFooter() {
        appendWithNewLine(".isEquals();");
        appendWithNewLine("}");
    }

    private void appendWithNewLine(String toAppend) {
        stringBuilder.append(toAppend);
        stringBuilder.append(System.lineSeparator());
    }
}
