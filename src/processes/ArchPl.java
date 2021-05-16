package processes;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class ArchPl implements Plugin {
    @Override
    public void load(ArrayList list) {
        ObjectMapper mapper = new ObjectMapper();
        String file = "C:\\tmp\\plug\\tmp.json";
        File f = new File(file);
        try {
            mapper.writeValue(f, list);
        } catch (Exception e) {

        }
        String archive = "C:\\tmp\\plug\\archive.zip";
        String filename = "tmp.json";
        try (ZipOutputStream zout = new ZipOutputStream(new FileOutputStream(archive));

             FileInputStream fis = new FileInputStream(file)) {
            ZipEntry entry1 = new ZipEntry(filename);
            zout.putNextEntry(entry1);
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            zout.write(buffer);
            zout.closeEntry();

            f.delete();

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public ArrayList unload() {
        ObjectMapper mapper = new ObjectMapper();
        String path = "C:\\tmp\\plug\\archive.zip";
        String s = reArchive(path);
        try {
            ArrayList list = mapper.readValue(new File(s), ArrayList.class);
            return list;
        } catch (Exception e) {
            return null;
        }

    }

    private String reArchive(String path) {
        try (ZipInputStream zin = new ZipInputStream(new FileInputStream(path))) {
            ZipEntry entry;
            String name = "";
            while ((entry = zin.getNextEntry()) != null) {
                name = entry.getName();
                FileOutputStream fout = new FileOutputStream("C:\\tmp\\plug\\A" + name);
                for (int c = zin.read(); c != -1; c = zin.read()) {
                    fout.write(c);
                }
                fout.flush();
                zin.closeEntry();
                fout.close();

            }
            return "C:\\tmp\\A" + name;

        } catch (Exception e) {
            return null;
        }
    }

}
