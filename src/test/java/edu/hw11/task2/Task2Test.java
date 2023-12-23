package edu.hw11.task2;

import java.lang.reflect.InvocationTargetException;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.matcher.ElementMatchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Task2Test {
    @Test
    void shouldRuntimeReplaceSumWithMultiplication()
        throws InvocationTargetException, InstantiationException, IllegalAccessException {
        Class<? extends ArithmeticUtils> dynamicType = new ByteBuddy()
            .subclass(ArithmeticUtils.class)
            .method(ElementMatchers.named("sum"))
            .intercept(MethodDelegation.to(new ArithmeticUtilsInterceptor()))
            .make()
            .load(getClass().getClassLoader())
            .getLoaded();
        var constructor = dynamicType.getConstructors()[0];
        var instance = (ArithmeticUtils) constructor.newInstance();

        Assertions.assertEquals(50, instance.sum(5, 10));
    }
}
