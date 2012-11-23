package ast;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class UserObject {
    Object target;

    public UserObject(Object target) {
        this.target = target;
    }

    public Object callFunction(String methodName, Object... args)
            throws NoSuchMethodException {

        Class[] givenParametersTypes = new Class[args.length];
        for (int i = 0; i < args.length; i++) {
            givenParametersTypes[i] = args[i].getClass();
            System.out.println(givenParametersTypes[i]);
        }
        Method method = target.getClass().getMethod(methodName,
                givenParametersTypes);
        Object result = null;
        try {
            result = method.invoke(target, args);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

}
