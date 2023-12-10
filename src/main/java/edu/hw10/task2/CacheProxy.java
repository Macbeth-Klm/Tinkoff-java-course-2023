package edu.hw10.task2;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

public class CacheProxy implements InvocationHandler {
    private final Object target;

    private CacheProxy(Object target) {
        this.target = target;
    }

    @SuppressWarnings("unchecked")
    public static <T> T create(T target, Class<T> interfaceClass) {
        return (T) Proxy.newProxyInstance(
            interfaceClass.getClassLoader(),
            new Class<?>[] {interfaceClass},
            new CacheProxy(target)
        );
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        var cacheAnnotation = method.getAnnotation(Cache.class);
        if (cacheAnnotation != null && cacheAnnotation.persist()) {
            String cacheKey = method.getName() + Arrays.toString(args);
            File cacheFile = new File("src/main/java/edu/hw10/task2/cache/" + cacheKey + ".txt");
            if (cacheFile.exists()) {
                try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(cacheFile))) {
                    return in.readObject();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } else {
                Object result = method.invoke(target, args);
                try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(cacheFile))) {
                    out.writeObject(result);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                return result;
            }
        }
        return method.invoke(target, args);
    }
}
