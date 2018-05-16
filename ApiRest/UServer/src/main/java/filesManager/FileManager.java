package filesManager;

import org.apache.commons.codec.binary.Base64;

import java.io.*;

public class FileManager {

    private String filename;
    private String text;
    private int clave;

    public FileManager(String filename) {
        this.filename = filename;
    }

    public FileManager(String filename, String text) {
        this.filename = filename;
        this.text = text;
    }

    public void createFile(String ext) {
        File file = new File(filename + ext);
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter(file));
            writer.write(text);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void createDecodeFile() {
        clave = filename.length();
        String cifrado = vernam();
    }

    public String getFile(String ext) {
        return "";
    }

    public String getEncodeFile(String ext) {
        return "";
    }

    public String getImageBase64(String ext) {
        String encodedImage = "";
        File file = new File(filename);
        try {
            FileInputStream stream = new FileInputStream(file);
            byte[] bytes = new byte[(int)file.length()];
            stream.read(bytes);
            encodedImage = new String(Base64.encodeBase64(bytes), "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }

        return encodedImage;
    }

    private String vernam() {
        return "";
    }
}
