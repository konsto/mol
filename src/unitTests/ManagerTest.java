package unitTests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import exceptions.AliasAlreadyExistsException;
import exceptions.NoFactoryException;
import exceptions.NoSuchPluginException;

import plugin.IPlugin;
import plugin.IPluginManager;
import plugin.PluginManager;
import test_plugins.TestFactory;

public class ManagerTest 
{
	PluginManager manager;
	String alias;
	String pluginName;
	
	@Before
	public void setUp() throws Exception 
	{
		manager = new PluginManager();
		alias = "m";
		pluginName = "first";

	}

	@Test
	public void testImportAndGetPlugin() throws Exception
	{
		manager.addFactory(new TestFactory());
		IPlugin plugin;
		

		manager.importPlugin(alias, pluginName);
		plugin = manager.getPlugin(alias);
		assertNotNull(plugin);
	}
	
	@Test (expected = Exception.class)
	public void testManagerWithNotWorkingFactory() throws Exception
	{
		manager.importPlugin(alias, pluginName);
	}
	
	@Test (expected = Exception.class)
	public void testManagerWithNonWorkingPlugin() throws Exception
	{
		manager.addFactory(new TestFactory());
		manager.importPlugin("a", "ble");
		IPlugin plugin = manager.getPlugin("a");
		
	}

}
