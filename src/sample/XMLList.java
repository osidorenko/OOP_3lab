package sample;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;

import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlList;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;
import java.util.ArrayList;


@XmlRootElement

public class XMLList implements Serializable {


    @JacksonXmlElementWrapper(useWrapping = false)
    public ArrayList<Vehicle> vihecles;

    public XMLList() {

    }


}
