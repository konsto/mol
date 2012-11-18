package plugin;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

//TODO: wyrzucic annotacje, dodawac je do konkretnych pluginnow (ex. Person)
@APluginType (type = "instance")
public class InstanceMethodsPlugin implements IPlugin
{
	private Class pluginClass;
	private Method[] methods;
	private Object pluginInstance;
	private Class[] givenParametersTypes;
	
	public InstanceMethodsPlugin(Class c)
	{
		this.pluginClass = c;
		this.methods = c.getMethods();
		
		//TODO: wyrzucic dalej exception zamiast try
		try 
		{
			this.pluginInstance = c.getConstructor().newInstance();
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		} 
	}
	
	@Override
	public Object callFunction(String methodName, Object... args) 
			throws NoSuchMethodException
	{
		givenParametersTypes = new Class[args.length];
		//Class[] givenParametersTypes = (Class[]) args;
		for (int i = 0 ; i < args.length; i++)
		{
			givenParametersTypes[i] = args[i].getClass();
		}
		//TODO: MethodSignature mozna wywalic, bo getMethod() znajduje metode 
		MethodSignature givenSignature = new MethodSignature(methodName, givenParametersTypes);
		Method method = pluginInstance.getClass().getMethod(methodName, givenParametersTypes);
		MethodSignature realSignature = new MethodSignature(method);
		Object result = null;
		if (realSignature.isConvertibleFrom(givenSignature))
		{
			try 
			{
				result = method.invoke(pluginInstance, args);
			} 
			catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) 
			{
				e.printStackTrace();
			}	
		}
		else
		{
			throw new NoSuchMethodException(methodName);
		}
		return result;		
	}
	
	@Override
	public Method[] getAllMethods() 
	{
		return methods;
	}
}
