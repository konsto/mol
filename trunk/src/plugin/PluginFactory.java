package plugin;

import java.lang.annotation.Annotation;

public class PluginFactory implements IPluginFactory
{

	Class c;
	IPlugin plugin;
	
	@Override
	public IPlugin create(String name) 
			throws ClassNotFoundException 
	{
		c = Class.forName(name);
	    Annotation annotation = c.getAnnotation(APluginType.class);
	    APluginType pluginAnnotation = (APluginType) annotation;
	    if (pluginAnnotation.type().equals("static"))
	    	plugin = new StaticMethodsPlugin(c);
	    else if (pluginAnnotation.type().equals("instance"))
	    	plugin = new InstanceMethodsPlugin(c);
	    else
	    	plugin = null;
	    
		return plugin;
	}
	
}
