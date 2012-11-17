package plugin;

import java.lang.reflect.*;
import java.util.LinkedList;
import java.util.List;

@APluginType (type = "static")
public class StaticMethodsPlugin implements IPlugin 
{
	
	private Class pluginClass;
	private Method[] methods;
	private Class[] givenParametersTypes;

	public StaticMethodsPlugin(Class pluginClass)
	{
		this.pluginClass = pluginClass;
		this.methods = pluginClass.getMethods();

		
	}
	
	@Override
	public Object callFunction(String methodName, Object...args) 
			throws NoSuchMethodException
	{
		givenParametersTypes = new Class[args.length];
		//Class[] givenParametersTypes = (Class[]) args;
		for (int i = 0 ; i < args.length; i++)
		{
			givenParametersTypes[i] = args[i].getClass();
		}
		
		
		MethodSignature givenSignature = new MethodSignature(methodName, 
				givenParametersTypes);
		Method method = pluginClass.getMethod(methodName, givenParametersTypes);
		MethodSignature realSignature = new MethodSignature(method);
		Object result = null;
		
		if (realSignature.isConvertibleFrom(givenSignature))
		{
			try 
			{
				result = method.invoke(null, args);
			} 
			catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) 
			{
				e.printStackTrace();
			}
			
			
		}
		else
			throw new NoSuchMethodException(methodName);

		return result;
	}
	

	@Override
	public Method[] getAllMethods() 
	{
		
		return methods;
		
	}
	
}
