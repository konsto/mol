package plugin;

public interface IPluginFactory 
{
	IPlugin create(String name) 
			throws ClassNotFoundException;
}
