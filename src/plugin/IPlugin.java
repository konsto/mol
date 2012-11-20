package plugin;

public interface IPlugin {

    Object callFunction(String methodName, Object... args)
            throws NoSuchMethodException;

}
