package engine;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import annotations.*;

public class ObjectWrapper implements IObject {
    private Object content;
    private ReflectionHelper helper;

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
        this.helper = new ReflectionHelper(this.content.getClass());
    }

    @Override
    public IObject add(IObject object) throws Exception {
        return executeOperation(Addition.class, object);
    }

    @Override
    public IObject equalTo(IObject object) throws Exception {
        return executeOperation(EqualTo.class, object);
    }

    @Override
    public IObject invokeMethod(String name, IObject... args) throws Exception {
        Class<?>[] paramTypes = new Class<?>[args.length];
        Object[] argValues = new Object[args.length];
        for (int i = 0; i < args.length; i++) {
            paramTypes[i] = args[i].getType();
            argValues[i] = args[i].getValue();
        }
        Method method = helper.findMethod(name, paramTypes);
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
        Method method = helper.findMethod(Value.class, new Class[] {});
        if (method != null) {
            value = method.invoke(content, (Object[]) null);
        }
        return value;
    }

    @Override
    public IObject subtract(IObject object) throws Exception {
        return executeOperation(Subtraction.class, object);
    }

    @Override
    public IObject multiply(IObject object) throws Exception {
        return executeOperation(Multiplication.class, object);
    }

    @Override
    public IObject divide(IObject object) throws Exception {
        return executeOperation(Division.class, object);
    }

    @Override
    public IObject notEqualTo(IObject object) throws Exception {
        return executeOperation(NotEqualTo.class, object);
    }

    @Override
    public IObject greaterThan(IObject object) throws Exception {
        return executeOperation(GreaterThan.class, object);
    }

    @Override
    public IObject greaterThanOrEqualTo(IObject object) throws Exception {
        return executeOperation(GreaterThanOrEqualTo.class, object);
    }

    @Override
    public IObject lessThan(IObject object) throws Exception {
        return executeOperation(LessThan.class, object);
    }

    @Override
    public IObject lessThanOrEqualTo(IObject object) throws Exception {
        return executeOperation(LessThanOrEqualTo.class, object);
    }

    @Override
    public IObject modulo(IObject object) throws Exception {
        return executeOperation(Modulo.class, object);
    }

    @Override
    public IObject minus() throws Exception {
        return executeOperation(Minus.class);
    }

    @Override
    public IObject negate() throws Exception {
        return executeOperation(Negation.class);
    }

    private IObject executeOperation(Class annotationClass, IObject object)
            throws Exception {
        Method method = helper.findMethod(annotationClass,
                new Class[] { object.getType() });
        if (method == null) {
            throw new RuntimeException();
        }
        return new ObjectWrapper(method.invoke(content, object.getValue()));
    }

    private IObject executeOperation(Class annotationClass) throws Exception {
        Method method = helper.findMethod(annotationClass, new Class[] {});
        if (method == null) {
            throw new RuntimeException();
        }
        return new ObjectWrapper(method.invoke(content, (Object[]) null));
    }
}
