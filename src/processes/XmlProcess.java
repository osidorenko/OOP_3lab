package processes;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlFactory;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class XmlProcess implements ProcessInterface
{
    @Override
    public void Serialization(List list) {
        try {
            XmlMapper m = new XmlMapper();
            String result = m.writeValueAsString(list);
            m.writeValue(new File("C:\\tmp\\Vehicle2.xml"), list);
            //System.out.println(result);
        } catch (Exception ex) {
            System.err.println("ERROR : serialization xml");
        }
    }

    @Override
    public void Serialization(List list, String path) {
        try {
            XmlMapper m = new XmlMapper();
            String result = m.writeValueAsString(list);
            m.writeValue(new File(path), list);
            //System.out.println(result);
        } catch (Exception ex) {
            System.err.println("ERROR : serialization xml");
        }
    }

    @Override
    public ArrayList deSerialization(String path) {
        if(path.equals("")){
            path = "C:\\tmp\\Vehicle2.xml";
        }
        try {
            ObjectMapper m = new ObjectMapper(new XmlFactory());
            ArrayList list = m.readValue(new File(path), ArrayList.class);
            return list;
        } catch (Exception e) {
            System.err.println("ERROR : deserialization xml");
            return null;
        }
    }
}
