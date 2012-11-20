package unitTests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import plugin.PluginFactory;

public class FactoryTest {

    PluginFactory factory;

    @Before
    public void setUp() throws Exception {
        factory = new PluginFactory();
    }

    @Test
    public void testCreatingPlugin() throws Exception {
        factory.create("test_plugins.TestModule");
    }

    @Test (expected = Exception.class)
    public void testCreatingNonExistingPlugin() throws Exception {
        factory.create("bad.plugin.name");

    }
}
