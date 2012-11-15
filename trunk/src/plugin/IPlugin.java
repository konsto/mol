package plugin;

import java.util.List;

public interface IPlugin 
{

	Object callFunction(String methodName, List<Object> args);
	
	List<String> getAllMethods();

}
