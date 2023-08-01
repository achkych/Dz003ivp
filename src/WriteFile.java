import java.io.FileWriter;
import java.io.IOException;

public class WriteFile {
    public void writeData(String data, String path) {
        try (FileWriter fw = new FileWriter(path, true)) {
            fw.append(data).append("\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
