package processes;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.util.ArrayList;

public class CrtPl implements Plugin {

    Encryption e;
    public CrtPl(Encryption e){
        this.e=e;

    }

    @Override
    public void load(ArrayList list) {
        try (FileWriter wr = new FileWriter("C:\\tmp\\plug\\Crypt.txt", false)) {
            if (list.size() == 0) {
                return;
            }
            ObjectMapper mapper = new ObjectMapper();
            String data = mapper.writeValueAsString(list);
            e.setText(data);
            e.setCrypt(3);
            String writetofile = e.getEncrypted();
            System.out.println(writetofile);
            wr.write(writetofile);
            wr.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList unload() {
        try {
            String path = "C:\\tmp\\plug\\Crypt.txt";
            String ret = Files.readAllLines(new File(path).toPath()).get(0);
            e.setEncrypted(ret);
            String s = e.getCrypted(3);
            ObjectMapper mapper = new ObjectMapper();
            ArrayList list = mapper.readValue(s, ArrayList.class);
            return list;
        } catch (Exception e) {
            return null;
        }
    }
}
