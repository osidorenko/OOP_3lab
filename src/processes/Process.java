package processes;

import java.util.List;

public class Process {
    private ProcessInterface process;


    public Process(ProcessInterface pprocess) {
        this.process = pprocess;
    }

    public void doSerial(List list) {
        process.Serialization(list);
    }


    public void doSerial(List list, String path) {
        process.Serialization(list, path);
    }

    public void doDeSerial(String path) {
        process.deSerialization(path);
    }
}
