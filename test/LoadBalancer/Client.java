package test.LoadBalancer;

import java.io.FileWriter;
import java.io.IOException;

public class Client {
    public static void main(String[] args) throws IOException {
        String dataFilePath = "D:\\personal\\project\\Custom-HTTP\\test\\LoadBalancer\\data.log";
        FileWriter dataWriter = new FileWriter(dataFilePath);
        String errorFilePath = "D:\\personal\\project\\Custom-HTTP\\test\\LoadBalancer\\error.log";
        FileWriter errorWriter = new FileWriter(errorFilePath);
        for (int i = 0; i < 5000; i++) {
            new Thread(new WorkerClient(dataWriter, errorWriter, i)).start();
        }
    }
}
