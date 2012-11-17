package plugin;

import java.lang.reflect.Method;

public interface IPlugin
{

	Object callFunction(String methodName, Object... args)
			throws NoSuchMethodException;
	
	Method[] getAllMethods();

}
