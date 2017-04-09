package test.java.builder;

import main.java.builder.EqualsHashCodeUtility;
import main.java.model.Van;
import main.java.model.Vehicle;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class EqualsHashCodeUtilityTest {

    private EqualsHashCodeUtility equalsHashCodeUtility = new EqualsHashCodeUtility();

    @Test
    public void createEqualsMethod() {
        String result = equalsHashCodeUtility.createEqualsMethod(Vehicle.class);
        System.out.println(result);
        System.out.println("EXTENDS = " + Van.class.getSuperclass().getSimpleName());
        assertEquals("", result);
    }
}