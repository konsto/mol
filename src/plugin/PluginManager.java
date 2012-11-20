package plugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import exceptions.*;

public class PluginManager implements IPluginManager {
    private Map<String, IPlugin> plugins;
    private List<IPluginFactory> factories;

    public PluginManager() {
        plugins = new HashMap<String, IPlugin>();
        factories = new ArrayList<IPluginFactory>();

    }

    public void addFactory(IPluginFactory factory) {
        factories.add(factory);
    }

    @Override
    public void importPlugin(String alias, String pluginName)
            throws Exception {
        if (plugins.containsKey(alias)) {
            throw new AliasAlreadyExistsException();
        }

        if (factories.size() < 1) {
            throw new NoFactoryException();
        }
        IPlugin currentPlugin = fingPluginByClass(Class.forName(pluginName));
        if (currentPlugin == null) {
            IPlugin plugin = factories.get(0).create(pluginName);
            plugins.put(alias, plugin);
        } else {
            plugins.put(alias, currentPlugin);
        }
    }

    @Override
    public IPlugin getPlugin(String alias) throws NoSuchPluginException {
        if (plugins.containsKey(alias))
            return plugins.get(alias);
        else
            throw new NoSuchPluginException();
    }

    private IPlugin fingPluginByClass(Class c) {
        IPlugin resultPlugin = null;
        for (String key : this.plugins.keySet()) {
            IPlugin currentPlugin = plugins.get(key);
            Class temp = currentPlugin.getClass();
            if (temp.equals(c)) {
                resultPlugin = currentPlugin;
            } else {
                resultPlugin = null;
            }
        }
        return resultPlugin;
    }
}