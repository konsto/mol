package plugin;

import java.lang.reflect.InvocationTargetException;

public class PluginFactory implements IPluginFactory {

    Class c;
    IPlugin plugin;

    @Override
    public IPlugin create(String name) throws Exception{
        c = Class.forName(name);
        plugin = new InstanceMethodsPlugin(c);
        return plugin;
    }
}
