package processes;

import java.util.ArrayList;

public interface Plugin {
    public void load(ArrayList list);
    public ArrayList unload();
}
