package engine;

public interface IModuleLoader 
{
	IObject load(String name) 
			throws Exception;
}
