package processes;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class ArchiveProcess {
    public void archive(String file, String archive) {
        String filename[] = file.split("\\+");
        try (ZipOutputStream zout = new ZipOutputStream(new FileOutputStream(archive));
             FileInputStream fis = new FileInputStream(file);) {
            ZipEntry entry1 = new ZipEntry(filename[filename.length - 1]);
            zout.putNextEntry(entry1);
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            zout.write(buffer);
            zout.closeEntry();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public String reArchive(String path) {

        try (ZipInputStream zin = new ZipInputStream(new FileInputStream(path))) {
            ZipEntry entry;
            String name = "";
            while ((entry = zin.getNextEntry()) != null) {
                name = entry.getName();
                FileOutputStream fout = new FileOutputStream("C:\\tmp\\A" + name);
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
