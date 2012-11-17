package plugin;

import exceptions.AliasAlreadyExistsException;
import exceptions.NoFactoryException;
import exceptions.NoSuchPluginException;

public interface IPluginManager 
{

	void importPlugin(String alias, String pluginName) 
			throws AliasAlreadyExistsException, ClassNotFoundException,
			NoFactoryException;
	
	IPlugin getPlugin(String alias) throws NoSuchPluginException;

}
