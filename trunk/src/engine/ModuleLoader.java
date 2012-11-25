package engine;

public class ModuleLoader implements IModuleLoader {

    Class<?> c;
     IObject module;

    @Override
    public IObject load(String name) throws Exception{
        c = Class.forName(name);
        module = new InstanceModuleWrapper(c);
        return module;
    }
}
