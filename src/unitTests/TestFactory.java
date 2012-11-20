package unitTests;

import java.util.HashMap;
import java.util.Map;

import plugin.IPlugin;
import plugin.IPluginFactory;

public class TestFactory implements IPluginFactory
{
	Map<String, IPlugin> tempPlugins = new HashMap<String, IPlugin>();

	public TestFactory()
	{
		tempPlugins.put("first", new TestPlugin());
		tempPlugins.put("second", new TestPlugin());
		tempPlugins.put("third", new TestPlugin());
		tempPlugins.put("fourth", new TestPlugin());
	}

	@Override
	public IPlugin create(String name) throws ClassNotFoundException 
	{
		IPlugin plugin = null;
		
		if (tempPlugins.containsKey(name))
			plugin = tempPlugins.get(name);
		else
			throw new ClassNotFoundException();
		
		return plugin;
	}
	
}
