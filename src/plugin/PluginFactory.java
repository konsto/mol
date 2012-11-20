package plugin;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;

public class PluginFactory implements IPluginFactory
{

	Class c;
	IPlugin plugin;
	
	@Override
	public IPlugin create(String name) 
			throws ClassNotFoundException 
	{
		c = Class.forName(name);
	    Annotation annotation = c.getAnnotation(PluginTypeAnnotation.class);
	    PluginTypeAnnotation pluginAnnotation = (PluginTypeAnnotation) annotation;
	    if (pluginAnnotation.type().equals("static"))
	    	plugin = new StaticMethodsPlugin(c);
	    else if (pluginAnnotation.type().equals("instance"))
            try {
                plugin = new InstanceMethodsPlugin(c);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } 
        else
	    	plugin = null;
	    
		return plugin;
	}
	
}
