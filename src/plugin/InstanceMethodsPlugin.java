package plugin;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class InstanceMethodsPlugin implements IPlugin {
    private Class pluginClass;
    private Method[] methods;
    private Object pluginInstance;

    public InstanceMethodsPlugin(Class c) throws InstantiationException,
            IllegalAccessException, IllegalArgumentException,
            InvocationTargetException, NoSuchMethodException, SecurityException {
        this.pluginClass = c;
        this.methods = c.getMethods();
        this.pluginInstance = c.getConstructor().newInstance();
    }

    @Override
    public Object callFunction(String methodName, Object... args)
            throws NoSuchMethodException {
        Class[] givenParametersTypes = new Class[args.length];
        for (int i = 0; i < args.length; i++) {
            givenParametersTypes[i] = args[i].getClass();
        }
        Method method = pluginInstance.getClass().getMethod(methodName,
                givenParametersTypes);
        Object result = null;
        try {
            result = method.invoke(pluginInstance, args);
        } catch (IllegalAccessException | IllegalArgumentException
                | InvocationTargetException e) {
            e.printStackTrace();
        }
        return result;
    }

}
