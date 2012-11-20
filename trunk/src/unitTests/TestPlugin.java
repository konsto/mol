package unitTests;

import plugin.IPlugin;

public class TestPlugin implements IPlugin {
    @Override
    public Object callFunction(String methodName, Object... args)
            throws NoSuchMethodException {
        return null;
    }
}
