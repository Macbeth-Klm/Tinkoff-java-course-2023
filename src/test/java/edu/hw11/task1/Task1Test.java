package edu.hw11.task1;

import java.lang.reflect.InvocationTargetException;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.FixedValue;
import net.bytebuddy.matcher.ElementMatchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Task1Test {
    @Test
    void shouldCreateNewClassWithToStringMethodWhichReturnHelloByteBuddy()
        throws InstantiationException, IllegalAccessException, InvocationTargetException {
        String helloByteBuddy = "Hello, ByteBuddy!";

        Class<?> task1Class = new ByteBuddy()
            .subclass(Object.class)
            .method(ElementMatchers.named("toString"))
            .intercept(FixedValue.value(helloByteBuddy))
            .make()
            .load(getClass().getClassLoader())
            .getLoaded();
        var constructor = task1Class.getConstructors()[0];
        var instance = constructor.newInstance();

        Assertions.assertEquals(helloByteBuddy, instance.toString());
    }
}
