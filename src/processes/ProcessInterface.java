package processes;

import java.util.ArrayList;
import java.util.List;

public interface ProcessInterface {
    public void Serialization(List list);
    public void Serialization(List list,String path);
    public ArrayList deSerialization(String path);
}
