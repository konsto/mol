package engine;

import java.util.HashMap;
import java.util.Map;

public class Context {
    private Map<String, ContextEntry> entries;

    public Context() {
        entries = new HashMap<String, ContextEntry>();
    }

    public void setEntry(String identificator, ContextEntry entry) {
        entries.put(identificator, entry);
    }

    public ContextEntry getEntry(String identificator) {
        return entries.get(identificator);
    }

    public ContextEntry getEntry(Class<?> c) {
        for (String key : entries.keySet()) {
            if (entries.get(key).getClass().equals(c)) {
                return entries.get(key);
            }
        }
        return null;
    }

    public void print() throws Exception {
        for (String key : entries.keySet()) {
            System.out.println(key + " => "
                    + entries.get(key).getObject().getValue().toString());
        }
    }
}
