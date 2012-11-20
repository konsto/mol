package unitTests;

import java.lang.reflect.Method;

import plugin.PluginTypeAnnotation;
import plugin.IPlugin;


public class TestPlugin implements IPlugin
{

	@Override
	public Object callFunction(String methodName, Object... args)
			throws NoSuchMethodException 
	{
		return null;
	}
}
