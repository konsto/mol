package plugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import exceptions.*;

public class PluginManager implements IPluginManager 
{
	private Map<String, IPlugin> plugins;
	private List<IPluginFactory> factories;
	
	public PluginManager()
	{
		plugins = new HashMap<String, IPlugin>();
		factories = new ArrayList<IPluginFactory>();
		
	}

	public void addFactory(IPluginFactory factory)
	{
		factories.add(factory);
	}
	
	@Override
	public void importPlugin(String alias, String pluginName) 
			throws AliasAlreadyExistsException, ClassNotFoundException,
			NoFactoryException 
	{
		if (plugins.containsKey(alias))
			throw new AliasAlreadyExistsException();
		
		if (factories.size() < 1)
			throw new NoFactoryException();
		
		IPlugin plugin = factories.get(0).create(pluginName);
		
		plugins.put(alias, plugin);
	}

	@Override
	public IPlugin getPlugin(String alias) 
			throws NoSuchPluginException 
	{
		if (plugins.containsKey(alias))
			return plugins.get(alias);
		else
			throw new NoSuchPluginException();
		 
		
	}
}
