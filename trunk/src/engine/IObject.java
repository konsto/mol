package engine;

public interface IObject extends IInvocable {
    
    public Object getValue() throws Exception;
    
    public Class<?> getType() throws Exception;
    
    public IObject add(IObject object) throws Exception;

    public IObject equals(IObject object) throws Exception;
}
