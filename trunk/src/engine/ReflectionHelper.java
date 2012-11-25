package engine;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public class ReflectionHelper {
    private Class<?> c;

    public ReflectionHelper(Class<?> c) {
        this.c = c;
    }

    public Method findMethod(Class annotationClass, Class<?>[] paramTypes) {
        Method result = null;
        for (Method method : c.getMethods()) {
            if (compareSignatures(method.getParameterTypes(), paramTypes)) {
                Annotation annotation = method.getAnnotation(annotationClass);
                if (annotation != null) {
                    result = method;
                    break;
                }
            }
        }
        return result;
    }

    public Method findMethod(String methodName, Class<?>[] paramTypes)
            throws Exception {
        Method result = null;
        for (Method method : c.getMethods()) {
            if (compareSignatures(method.getParameterTypes(), paramTypes)) {
                if (method.getName().equals(methodName)) {
                    result = method;
                    break;
                }
            }
        }
        return result;
    }

    private boolean compareSignatures(Class<?>[] methodSignature,
            Class<?>[] candidateSignature) {
        if (methodSignature.length != candidateSignature.length) {
            return false;
        }
        for (int i = 0; i < methodSignature.length; i++) {
            methodSignature[i] = convertSmallToBigPrimitive(methodSignature[i]);
            candidateSignature[i] = convertSmallToBigPrimitive(candidateSignature[i]);
            if (methodSignature[i] != candidateSignature[i]) {
                return false;
            }
        }
        return true;
    }

    private Class<?> convertSmallToBigPrimitive(Class<?> type) {
        if (type == int.class) {
            type = Integer.class;
        } else if (type == double.class) {
            type = Double.class;
        } else if (type == float.class) {
            type = Float.class;
        } else if (type == boolean.class) {
            type = Boolean.class;
        }
        return type;
    }

}
