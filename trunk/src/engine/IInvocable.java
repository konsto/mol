package engine;

public interface IInvocable {
    
    public IObject invokeMethod(String method, IObject...args) throws Exception;

}
