package processes;

import vehicles.Vehicle;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class BinaryProcess implements ProcessInterface {

    @Override
    public ArrayList deSerialization(String path) {
        if (path.equals("")) {
            path = "C:\\tmp\\Vehicle1.txt";
        }
        try {
            File f = new File(path);
            List<String> array = Files.readAllLines(f.toPath());
            String splt[] = array.get(0).split("\\s");
            byte[] tmp = new byte[splt.length];
            for (int i = 0; i < splt.length; i++) {
                tmp[i] = Byte.decode(splt[i]);
            }
            ByteArrayOutputStream b = new ByteArrayOutputStream();
            b.write(tmp);
            ObjectInputStream stream2 = new ObjectInputStream(new ByteArrayInputStream(b.toByteArray()));
            ArrayList<Vehicle> vih = (ArrayList<Vehicle>) stream2.readObject();
            stream2.close();
            return vih;
        } catch (Exception e) {
            System.err.println("ERROR : deserialization binary");
            //e.printStackTrace();

            return null;
        }
    }

    @Override
    public void Serialization(List list) {
        try {
            ByteArrayOutputStream b = new ByteArrayOutputStream();
            ObjectOutputStream stream = new ObjectOutputStream(b);
            stream.close();
            stream.writeObject(list);
            FileWriter fwriter = new FileWriter(new File("C:\\tmp\\Vehicle1.txt"), false);
            byte[] tmp = b.toByteArray();
            String s = "";
            for (int i = 0; i < tmp.length; i++) {
                s += tmp[i] + " ";
            }
            fwriter.write(s);
            fwriter.close();
        } catch (Exception e) {
            System.err.println("ERROR : serialization binary");
            e.printStackTrace();
        }
    }

    @Override
    public void Serialization(List list, String path) {

        try {
            ByteArrayOutputStream b = new ByteArrayOutputStream();
            ObjectOutputStream stream = new ObjectOutputStream(b);
            stream.close();
            stream.writeObject(list);
            FileWriter fwriter = new FileWriter(new File(path), false);
            byte[] tmp = b.toByteArray();
            String s = "";
            for (int i = 0; i < tmp.length; i++) {
                s += tmp[i] + " ";
            }
            fwriter.write(s);
            fwriter.close();
        } catch (Exception e) {
            System.err.println("ERROR : serialization binary");
            e.printStackTrace();

        }

    }
}
