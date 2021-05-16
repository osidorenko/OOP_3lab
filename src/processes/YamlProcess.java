package processes;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class YamlProcess implements ProcessInterface {
    @Override
    public void Serialization(List list) {
        try {
            ObjectMapper om = new ObjectMapper(new YAMLFactory());
            String sss = om.writeValueAsString(list);
            om.writeValue(new File("C:\\tmp\\Vehicle.yaml"), list);

        } catch (Exception e) {
            System.err.println("ERROR : serialization yaml");

        }
    }

    @Override
    public void Serialization(List list, String path) {
        try {
            ObjectMapper om = new ObjectMapper(new YAMLFactory());
            String sss = om.writeValueAsString(list);
            om.writeValue(new File(path), list);
        } catch (Exception e) {
            System.err.println("ERROR : serialization yaml");
        }
    }

    @Override
    public ArrayList deSerialization(String path) {
        if (path.equals("")) {
            path = "C:\\tmp\\Vehicle.yaml";
        }
        try {
            ObjectMapper om = new ObjectMapper(new YAMLFactory());
            ArrayList list = om.readValue(new File(path), ArrayList.class);
            return list;
        } catch (Exception e) {
            System.err.println("ERROR : deserialization yaml");
            return null;
        }
    }
}
