package test_plugins;

import java.util.HashMap;
import java.util.Map;

import plugin.IPlugin;
import plugin.IPluginFactory;

public class TestFactory implements IPluginFactory
{
	Map<String, IPlugin> plugins = new HashMap<String, IPlugin>();

	public TestFactory()
	{
		plugins.put("first", new TestPlugin());
		plugins.put("second", new TestPlugin());
		plugins.put("third", new TestPlugin());
		plugins.put("fourth", new TestPlugin());
	}

	@Override
	public IPlugin create(String name) throws ClassNotFoundException 
	{
		IPlugin plugin = null;
		
		if (plugins.containsKey(name))
			plugin = plugins.get(name);
		else
			throw new ClassNotFoundException();
		
		return plugin;
	}
	
}
