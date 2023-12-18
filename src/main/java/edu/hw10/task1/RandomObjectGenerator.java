package edu.hw10.task1;

import edu.hw10.task1.Annotations.Max;
import edu.hw10.task1.Annotations.Min;
import edu.hw10.task1.Annotations.NotNull;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.concurrent.ThreadLocalRandom;

public class RandomObjectGenerator {

    public RandomObjectGenerator() {
    }

    public Object nextObject(Class<?> generatingClass, String methodName) {
        try {
            Method method = getFabricMethod(generatingClass, methodName);
            Parameter[] params = method.getParameters();
            return (params.length == 0) ? method.invoke(null) : generateInstanceWithParams(method, params);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Object nextObject(Class<?> generatingClass) {
        Constructor<?> ctor = generatingClass.getConstructors()[0];
        Parameter[] params = ctor.getParameters();
        try {
            return (params.length == 0) ? ctor.newInstance() : generateInstanceWithParams(ctor, params);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private Object generateInstanceWithParams(Constructor<?> constructor, Parameter[] params)
        throws InvocationTargetException, InstantiationException, IllegalAccessException {
        Object[] paramsValue = generateParams(params);
        return constructor.newInstance(paramsValue);
    }

    private Object generateInstanceWithParams(Method method, Parameter[] params)
        throws InvocationTargetException, IllegalAccessException {
        Object[] paramsValue = generateParams(params);
        return method.invoke(null, paramsValue);
    }

    private Object[] generateParams(Parameter[] params) {
        Object[] paramsValue = new Object[params.length];
        for (int i = 0; i < params.length; i++) {
            var paramsType = params[i].getType();
            if (paramsType.equals(String.class)) {
                var annotations = params[i].getAnnotations();
                if (annotations.length == 0) {
                    paramsValue[i] = null;
                } else {
                    for (Annotation annotation : annotations) {
                        if (annotation.annotationType().equals(NotNull.class)) {
                            paramsValue[i] = "";
                        }
                    }
                }
            } else if (paramsType.equals(Integer.class) || paramsType.equals(int.class)) {
                var annotations = params[i].getAnnotations();
                if (annotations.length == 0) {
                    paramsValue[i] = 0;
                } else {
                    int max = Integer.MAX_VALUE;
                    int min = Integer.MIN_VALUE;
                    for (Annotation annotation : annotations) {
                        if (annotation.annotationType().equals(Max.class)) {
                            max = ((Max) annotation).value();
                        } else if (annotation.annotationType().equals(Min.class)) {
                            min = ((Min) annotation).value();
                        }
                    }
                    paramsValue[i] = ThreadLocalRandom.current().nextInt(min, max);
                }
            } else {
                throw new RuntimeException("Method / C-tor contains parameters that cannot be generated!");
            }
        }
        return paramsValue;
    }

    private Method getFabricMethod(Class<?> generatingClass, String methodName) throws NoSuchMethodException {
        var methods = generatingClass.getMethods();
        for (var method : methods) {
            if (method.getName().equals(methodName)) {
                return method;
            }
        }
        throw new NoSuchMethodException();
    }
}
