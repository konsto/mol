package unitTests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import engine.ModuleLoader;


public class FactoryTest {

    ModuleLoader factory;

    @Before
    public void setUp() throws Exception {
        factory = new ModuleLoader();
    }

    @Test
    public void testCreatingPlugin() throws Exception {
        factory.load("test_plugins.TestModule");
    }

    @Test (expected = Exception.class)
    public void testCreatingNonExistingPlugin() throws Exception {
        factory.load("bad.plugin.name");

    }
}
