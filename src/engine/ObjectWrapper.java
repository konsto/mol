package engine;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import annotations.Addition;
import annotations.EqualTo;
import annotations.Type;
import annotations.Value;

public class ObjectWrapper implements IObject {
    private Object content;

    public ObjectWrapper(Object object) {
        if (object instanceof Integer) {
            object = new IntObject((Integer) object);
        } else if (object instanceof String) {
            object = new StringObject((String) object);
        } else if (object instanceof Double) {
            object = new DoubleObject((Double) object);
        } else if (object instanceof Float) {
            object = new DoubleObject((Float) object);
        } else if (object instanceof Boolean) {
            object = new BooleanObject((Boolean) object);
        }
        this.content = object;
    }

    @Override
    public IObject add(IObject object) throws Exception {
        Method method = findMethod(Addition.class,
                new Class[] { object.getType() });
        if (method == null) {
            throw new RuntimeException();
        }
        return new ObjectWrapper(method.invoke(content, object.getValue()));
    }

    @Override
    public IObject equals(IObject object) throws Exception {
        Method method = findMethod(EqualTo.class,
                new Class[] { object.getType() });
        if (method == null) {
            throw new RuntimeException();
        }
        return new ObjectWrapper(method.invoke(content, object.getValue()));
    }

    @Override
    public IObject invokeMethod(String name, IObject... args) throws Exception {
        Class<?>[] paramTypes = new Class<?>[args.length];
        Object[] argValues = new Object[args.length];
        for (int i = 0; i < args.length; i++) {
            paramTypes[i] = args[i].getType();
            argValues[i] = args[i].getValue();
        }
        Method method = findMethod(name, paramTypes);
        if (method == null) {
            throw new RuntimeException();
        }
        return new ObjectWrapper(method.invoke(content, argValues));
    }

    @Override
    public Class<?> getType() throws Exception {
        Class<?> type = content.getClass();
        Annotation annotation = content.getClass().getAnnotation(Type.class);
        if (annotation != null) {
            type = ((Type) annotation).type();
        }
        return type;
    }

    @Override
    public Object getValue() throws Exception {
        Object value = content;
        Method method = findMethod(Value.class, new Class[] {});
        if (method != null) {
            value = method.invoke(content, (Object[]) null);
        }
        return value;
    }

    private Method findMethod(Class annotationClass, Class<?>[] paramTypes) {
        Method result = null;
        for (Method method : content.getClass().getMethods()) {
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

    private Method findMethod(String methodName, Class<?>[] paramTypes)
            throws Exception {
        Method method = null;
        method = content.getClass().getMethod(methodName, paramTypes);
        return method;
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

    private Class convertSmallToBigPrimitive(Class type) {
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
