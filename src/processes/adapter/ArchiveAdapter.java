package processes.adapter;

import com.fasterxml.jackson.databind.ObjectMapper;
import processes.ArchiveProcess;
import processes.ProcessInterface;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ArchiveAdapter implements ProcessInterface {
    private ArchiveProcess process;
    private ObjectMapper mapper = new ObjectMapper();

    public ArchiveAdapter(ArchiveProcess pprocess) {
        this.process = pprocess;
    }

    @Override
    public void Serialization(List list) {
        File f = new File("C:\\tmp\\tmp.txt");
        try {
            mapper.writeValue(f, list);
        } catch (Exception e) {

        }
        process.archive("C:\\tmp\\tmp.json", "C:\\tmp\\archive.zip");

    }

    @Override
    public void Serialization(List list, String path) {
        File f = new File("C:\\tmp\\tmp.txt");
        try {
            mapper.writeValue(f, list);
        } catch (Exception e) {
        }
        process.archive("C:\\tmp\\tmp.json", path);
        f.delete();
    }

    @Override
    public ArrayList deSerialization(String path) {
        if (path.equals("")) {
            path = "C:\\tmp\\archive.zip";
        }
        String s = process.reArchive(path);
        try {
            ArrayList list = mapper.readValue(s, ArrayList.class);
            return list;
        } catch (Exception e) {

        }
        return null;
    }
}
