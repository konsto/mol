package engine;

import java.lang.reflect.Method;

public class InstanceModuleWrapper extends ModuleWrapperBase {
    private Object instance;
    private ReflectionHelper helper;

    public InstanceModuleWrapper(Class<?> c) throws Exception {
        this.instance = c.getConstructor().newInstance();
        this.helper = new ReflectionHelper(c);
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
        return new ObjectWrapper(method.invoke(instance, argValues));
    }

    @Override
    public Object getValue() throws Exception {
        return this.instance;
    }

    @Override
    public Class<?> getType() throws Exception {
        return instance.getClass();
    }

}
