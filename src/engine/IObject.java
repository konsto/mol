package engine;

public interface IObject extends IInvocable {
    
    public Object getValue() throws Exception;
    
    public Class<?> getType() throws Exception;
    
    public IObject add(IObject object) throws Exception;

    public IObject subtract(IObject object) throws Exception;
    
    public IObject multiply(IObject object) throws Exception;
    
    public IObject divide(IObject object) throws Exception;
    
    public IObject equalTo(IObject object) throws Exception;
    
    public IObject notEqualTo(IObject object) throws Exception;
    
    public IObject greaterThan(IObject object) throws Exception;
    
    public IObject greaterThanOrEqualTo(IObject object) throws Exception;

    public IObject lessThan(IObject object) throws Exception;
    
    public IObject lessThanOrEqualTo(IObject object) throws Exception;
    
    public IObject modulo(IObject object) throws Exception;
    
    public IObject minus() throws Exception;
    
    public IObject negate() throws Exception;
    
}
