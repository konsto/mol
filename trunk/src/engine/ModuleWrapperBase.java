package engine;

public abstract class ModuleWrapperBase implements IObject {

    @Override
    public abstract IObject invokeMethod(String method, IObject... args)
            throws Exception;

    @Override
    public abstract Object getValue() throws Exception;

    @Override
    public abstract Class<?> getType() throws Exception;

    @Override
    public final IObject add(IObject object) throws Exception {
        throw new RuntimeException("Operation '+' not supported");
    }

    @Override
    public final IObject subtract(IObject object) throws Exception {
        throw new RuntimeException("Operation '-' not supported");
    }

    @Override
    public final IObject multiply(IObject object) throws Exception {
        throw new RuntimeException("Operation '*' not supported");
    }

    @Override
    public final IObject divide(IObject object) throws Exception {
        throw new RuntimeException("Operation '/' not supported");
    }

    @Override
    public final IObject equalTo(IObject object) throws Exception {
        throw new RuntimeException("Operation '==' not supported");
    }

    @Override
    public final IObject notEqualTo(IObject object) throws Exception {
        throw new RuntimeException("Operation '!=' not supported");
    }

    @Override
    public final IObject greaterThan(IObject object) throws Exception {
        throw new RuntimeException("Operation '>' not supported");
    }

    @Override
    public final IObject greaterThanOrEqualTo(IObject object) throws Exception {
        throw new RuntimeException("Operation '>=' not supported");
    }

    @Override
    public final IObject lessThan(IObject object) throws Exception {
        throw new RuntimeException("Operation '<' not supported");
    }

    @Override
    public final IObject lessThanOrEqualTo(IObject object) throws Exception {
        throw new RuntimeException("Operation '<=' not supported");
    }

    @Override
    public final IObject modulo(IObject object) throws Exception {
        throw new RuntimeException("Operation '%' not supported");
    }

    @Override
    public final IObject minus() throws Exception {
        throw new RuntimeException("Operation '-' not supported");
    }

    @Override
    public final IObject negate() throws Exception {
        throw new RuntimeException("Operation '!' not supported");
    }

}
