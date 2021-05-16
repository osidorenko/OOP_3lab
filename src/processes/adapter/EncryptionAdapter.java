package processes.adapter;

import com.fasterxml.jackson.databind.ObjectMapper;
import processes.Encryption;
import processes.ProcessInterface;

import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class EncryptionAdapter implements ProcessInterface {
    private Encryption encryption;

    public EncryptionAdapter() {
        encryption = new Encryption();
    }

    @Override
    public void Serialization(List list) {
        try (FileWriter wr = new FileWriter("C:\\tmp\\Crypt.txt", false)) {
            if (list.size() == 0) {
                return;
            }
            ObjectMapper mapper = new ObjectMapper();
            String data = mapper.writeValueAsString(list);
            encryption.setText(data);
            encryption.setCrypt(5);
            String writetofile = encryption.getEncrypted();
            System.out.println(writetofile);
            wr.write(writetofile);
            wr.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void Serialization(List list, String path) {
        try (FileWriter wr = new FileWriter(path, false)) {
            if (list.size() == 0) {
                return;
            }
            ObjectMapper mapper = new ObjectMapper();
            String data = mapper.writeValueAsString(list);
            encryption.setCrypt(5);
            String writetofile = encryption.getEncrypted();
            System.out.println(writetofile);
            wr.write(writetofile);
            wr.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList deSerialization(String path) {
        try {
            if (path.equals("")) {
                path = "C:\\tmp\\Crypt.txt";
            }
            String ret = Files.readAllLines(new File(path).toPath()).get(0);
            encryption.setEncrypted(ret);
            String s = encryption.getCrypted(5);
            ObjectMapper mapper = new ObjectMapper();
            ArrayList list = mapper.readValue(s, ArrayList.class);
            return list;
        } catch (Exception e) {
            return null;
        }
    }
}
