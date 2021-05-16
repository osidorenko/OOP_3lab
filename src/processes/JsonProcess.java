package processes;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class JsonProcess implements ProcessInterface{

    @Override
    public void Serialization(List list) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(new File("C:\\tmp\\Vehicle3.json"), list);
        } catch (Exception e) {

        }
    }

    @Override
    public void Serialization(List list, String path) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(new File(path), list);
        } catch (Exception e) {

        }
    }

    @Override
    public ArrayList deSerialization(String path) {
        if(path.equals("")){
            path = "C:\\tmp\\Vehicle3.json";
        }
        try {
            ObjectMapper m = new ObjectMapper();
            ArrayList list = m.readValue(new File(path), ArrayList.class);
            return list;
        } catch (Exception e) {
            System.err.println("ERROR : deserialization json");
            return null;
        }
    }
}
