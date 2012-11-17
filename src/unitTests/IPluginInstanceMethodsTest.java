package unitTests;

import static org.junit.Assert.*;

import java.lang.reflect.Constructor;

import org.junit.Before;
import org.junit.Test;

import plugin.IPlugin;
import plugin.InstanceMethodsPlugin;
import plugin.StaticMethodsPlugin;

public class IPluginInstanceMethodsTest
{
	Class c;
	Constructor constructor;
	IPlugin plugin;
	

	@Before
	public void setUp() throws Exception 
	{
		c = Class.forName("test_plugins.Person");
		constructor = c.getConstructor();
		plugin = new InstanceMethodsPlugin(c);
	}

	@Test
	public void testCallFunctionSetName() 
	{
		try
		{
			plugin.callFunction("setName", "Michał");
			assertEquals("Michał", plugin.callFunction("getName"));
		}
		catch (NoSuchMethodException e) 
		{
			e.printStackTrace();
		}
	}
	
	@Test
	public void testCallFunctionSetSurname() 
	{
		try
		{
			plugin.callFunction("setSurname", "Lewandowski");
			assertEquals("Lewandowski", plugin.callFunction("getSurname"));
		}
		catch (NoSuchMethodException e) 
		{
			e.printStackTrace();
		}
	}
	
	@Test
	public void testCallFunctionSaySomething() 
	{
		try
		{
			plugin.callFunction("setName", "Michał");
			plugin.callFunction("setSurname", "Lewandowski");
			plugin.callFunction("saySomething", "Cześć, co słychać?");
			System.out.println(plugin.callFunction("saySomething", "Cześć, co słychać?"));
			assertTrue(plugin.callFunction("saySomething", "Cześć, co słychać?").equals("Michał " +
					"Lewandowski said: \"Cześć, co słychać?"));
		}
		catch (NoSuchMethodException e) 
		{
			e.printStackTrace();
		}
	}

}
