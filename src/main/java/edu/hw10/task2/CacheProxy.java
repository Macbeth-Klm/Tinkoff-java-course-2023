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
import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class CacheProxy implements InvocationHandler {
    private final Object target;
    private final Map<String, Object> memoryCache = new HashMap<>();
    private final Path discCachePath;

    private CacheProxy(Object target, Path discCachePath) {
        this.target = target;
        this.discCachePath = discCachePath;
    }

    @SuppressWarnings("unchecked")
    public static <T> T create(T target, Class<T> interfaceClass, Path discCachePath) {
        return (T) Proxy.newProxyInstance(
            interfaceClass.getClassLoader(),
            new Class<?>[] {interfaceClass},
            new CacheProxy(target, discCachePath)
        );
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        var cacheAnnotation = method.getAnnotation(Cache.class);
        if (cacheAnnotation != null) {
            Object result;
            String cacheKey = method.getName() + Arrays.deepToString(args);
            if (cacheAnnotation.persist()) {
                File cacheFile = new File(discCachePath.toFile(), cacheKey + ".txt");
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
            } else if (memoryCache.containsKey(cacheKey)) {
                result = memoryCache.get(cacheKey);
            } else {
                result = method.invoke(target, args);
                memoryCache.put(cacheKey, result);
            }
            return result;
        }
        return method.invoke(target, args);
    }
}
