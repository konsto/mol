package engine;

public class ContextEntry {
    IObject object;
    Boolean isAssignable;

    public ContextEntry(IObject object, Boolean isAssignable) {
        this.object = object;
        this.isAssignable = isAssignable;
    }

    public ContextEntry(IObject object) {
        this(object, true);
    }

    public IObject getObject() {
        return object;
    }

    public void setObject(IObject object) {
        this.object = object;
    }
    
    public Boolean isAssignable() {
        return isAssignable;
    }

    public String toString() {
        try {
            return object.getValue().toString();
        } catch (Exception e) {
            return "";
        }
    }

}
