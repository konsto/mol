package unitTests;

import static org.junit.Assert.*;
import test_plugins.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.junit.Before;
import org.junit.Test;

import plugin.IPlugin;
import plugin.StaticMethodsPlugin;

public class IPluginStaticTest {

	Class c;
	Constructor constructor;
	IPlugin plugin;
	String methodName;
	
	
	@Before
	public void setUp() throws Exception 
	{
		c = Class.forName("test_plugins.Person");
		constructor = c.getConstructor();
		plugin = new StaticMethodsPlugin(c);
		methodName = "setName";
	}
	
	@Test
	public void testCallFunctionWithParams()
	{
		Object actual = null;
		try 
		{
			actual = plugin.callFunction("demoFunctionWithParams","ds");
		} 
		catch (NoSuchMethodException e)
		{
			e.printStackTrace();
		}
		assertEquals(5, actual);
	}
	
	@Test
	public void testCallFunctionWithoutParams()
	{
		Object actual = null;
		try 
		{
			actual = plugin.callFunction("demoFunctionWithoutParams");
		} 
		catch (NoSuchMethodException e)
		{
			e.printStackTrace();
		}
		assertEquals(6, actual);
	}
	
	@Test (expected = Exception.class)
	public void testNonStaticFunction()
	{
		boolean flag = false;
		try 
		{
			plugin.callFunction("setName", "Michał");
			flag = true;
		} 
		catch (NoSuchMethodException e) 
		{
			e.printStackTrace();
			flag = false;
		}
		assertEquals(false, flag);
	}

}
