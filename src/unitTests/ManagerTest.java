package unitTests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import exceptions.AliasAlreadyExistsException;
import exceptions.NoFactoryException;
import exceptions.NoSuchPluginException;

import plugin.IPlugin;
import plugin.IPluginManager;
import plugin.PluginFactory;
import plugin.PluginManager;

public class ManagerTest {
    PluginManager manager;
    String alias;
    String pluginName;

    @Before
    public void setUp() throws Exception {
        manager = new PluginManager();
        alias = "m";
        pluginName = "first";

    }

    @Test
    public void testImportPlugin() throws Exception {
        manager.addFactory(new PluginFactory());
        IPlugin plugin;
        manager.importPlugin("m", "test_plugins.Person");
    }

    @Test
    public void testGetPlugin() throws Exception {
        manager.addFactory(new PluginFactory());
        IPlugin plugin;
        manager.importPlugin("m", "test_plugins.Person");
        plugin = manager.getPlugin("m");
        assertNotNull(plugin);
    }

    @Test
    public void testImportingExistingPlugin() throws Exception {
        manager.addFactory(new PluginFactory());
        IPlugin plugin;
        manager.importPlugin("m", "test_plugins.Person");
        manager.importPlugin("n", "test_plugins.Person");
    }

    @Test (expected = Exception.class)
    public void testImportingPluginWithExistingAlias() throws Exception {
        manager.addFactory(new PluginFactory());
        IPlugin plugin;
        manager.importPlugin("m", "test_plugins.Person");
        manager.importPlugin("m", "test_plugins.Mathematic");
    }

    @Test(expected = Exception.class)
    public void testManagerWithNotWorkingFactory() throws Exception {
        manager.importPlugin(alias, pluginName);
    }

    @Test(expected = Exception.class)
    public void testManagerWithNonWorkingPlugin() throws Exception {
        manager.addFactory(new TestFactory());
        manager.importPlugin("a", "ble");
        IPlugin plugin = manager.getPlugin("a");

    }

}
