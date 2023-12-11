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
import java.util.HashMap;
import java.util.Map;

public class CacheProxy implements InvocationHandler {
    private final Object target;
    private static final Map<String, Object> MEMORY_CACHE = new HashMap<>();

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
        if (cacheAnnotation != null) {
            Object result;
            String cacheKey = method.getName() + Arrays.deepToString(args);
            if (cacheAnnotation.persist()) {
                File cacheFile = new File("src/main/java/edu/hw10/task2/cache/" + cacheKey + ".txt");
                if (cacheFile.exists()) {
                    try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(cacheFile))) {
                        result = in.readObject();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                } else {
                    result = method.invoke(target, args);
                    try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(cacheFile))) {
                        out.writeObject(result);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            } else if (MEMORY_CACHE.containsKey(cacheKey)) {
                result = MEMORY_CACHE.get(cacheKey);
            } else {
                result = method.invoke(target, args);
                MEMORY_CACHE.put(cacheKey, result);
            }
            return result;
        }
        return method.invoke(target, args);
    }

    public static Map<String, Object> getMemoryCache() {
        return MEMORY_CACHE;
    }
}
