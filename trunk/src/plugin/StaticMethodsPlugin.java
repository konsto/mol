package plugin;

import java.lang.reflect.*;
import java.util.LinkedList;
import java.util.List;

public class StaticMethodsPlugin implements IPlugin {

    private Class pluginClass;
    private Method[] methods;

    public StaticMethodsPlugin(Class pluginClass) {
        this.pluginClass = pluginClass;
        this.methods = pluginClass.getMethods();

    }

    @Override
    public Object callFunction(String methodName, Object... args)
            throws NoSuchMethodException {
        Class[] givenParametersTypes = new Class[args.length];
        for (int i = 0; i < args.length; i++) {
            givenParametersTypes[i] = args[i].getClass();
        }
        Method method = pluginClass.getMethod(methodName, givenParametersTypes);
        Object result = null;
        try {
            result = method.invoke(null, args);
        } catch (IllegalAccessException | IllegalArgumentException
                | InvocationTargetException e) {
            e.printStackTrace();
        }
        return result;
    }
}
