package plugin;

import java.lang.reflect.Method;
import java.util.List;

public class MethodSignature 
{
	private String methodName;
	private Class[] parameterTypes;
	
	public MethodSignature(String methodName, Class... args)
	{
		this.methodName = methodName;
		this.parameterTypes = args;
	}
	
	public MethodSignature(Method method)
	{
		this.methodName = method.getName();
		this.parameterTypes = method.getParameterTypes();
	}
	
	//public void appendParameterType(Class parameterType)
	//{
	//	parameterTypes.add(parameterType);
	//}
	
	public boolean isConvertibleFrom(MethodSignature signature)
	{
		if (!this.methodName.equals(signature.methodName))
		{
			return false;
		} 
		
		if (this.parameterTypes.length != signature.parameterTypes.length)
		{
			return false;
		}
		
		for (int i = 0; i < this.parameterTypes.length; i++)
		{
			Class first = this.parameterTypes[i];
			Class second = signature.parameterTypes[i];
			if (!first.isAssignableFrom(second))
				return false;
		}
		return true;
	}
}
