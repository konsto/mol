package unitTests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import plugin.PluginFactory;

public class FactoryTest {

	@Before
	public void setUp() throws Exception {

	}

	@Test
	public void test() throws ClassNotFoundException 
	{
		PluginFactory factory = new PluginFactory();
		factory.create("test_plugins.Person");
	}

}
