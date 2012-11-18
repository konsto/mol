package unitTests;

import java.lang.reflect.Method;

import plugin.APluginType;
import plugin.IPlugin;

@APluginType (type = "static")
public class TestPlugin implements IPlugin
{

	@Override
	public Object callFunction(String methodName, Object... args)
			throws NoSuchMethodException 
	{
		return null;
	}

	@Override
	public Method[] getAllMethods() 
	{
		return null;
	}
	
}
