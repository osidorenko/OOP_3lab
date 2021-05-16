package processes;

import java.io.File;
import java.util.ArrayList;

public class PluginLoader {
    private ArrayList<Plugin> plugins;

    public PluginLoader(ArrayList<Plugin> plugins) {
        this.plugins = plugins;
    }

    //Выгрузить
    public ArrayList unLoadPlugins() {
        File f = new File("C:\\tmp\\plug");
        File[] files = f.listFiles();
        for (int i = 0; i < files.length; i++) {
            String name = files[i].getName();
            if (name.substring(name.length() - 3, name.length()).equals("txt")) {
                Plugin p = plugins.get(0);
                ArrayList list = p.unload();
                return list;
            } else {
                if (name.substring(name.length() - 3, name.length()).equals("zip")) {
                    Plugin p = plugins.get(1);
                    ArrayList list = p.unload();
                    return list;
                }
            }
        }
        Plugin pl = plugins.get(0);
        ArrayList list = pl.unload();
        return list;
    }

    //Загрузить
    public void loadPlugins(int chose, ArrayList list) {
        File f = new File("C:\\tmp\\plug");
        File[] files = f.listFiles();
        for (int i = 0; i < files.length; i++) {
            files[i].delete();
        }
        Plugin plugin = plugins.get(chose);
        plugin.load(list);
    }
}
