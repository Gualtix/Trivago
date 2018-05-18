package filesManager;

import org.apache.commons.codec.binary.Base64;

import java.io.*;

public class FileManager {

    private String filename;
    private String text;

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

    public void encryptFile() {
        String decodeText = "";
        int clave = filename.length();
        BufferedReader fileReader;
        decodeText = cryptogram(decodeText, clave);

        text = decodeText;
        createFile(".txt");
    }

    private String cryptogram(String decodeText, int clave) {
        BufferedReader fileReader;
        try {
            fileReader = new BufferedReader(new FileReader(filename + "txt"));
            String leido;
            while ((leido = fileReader.readLine()) != null) {
                decodeText += vernam(leido, clave);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return decodeText;
    }

    public String getFile(String ext) {
        return "";
    }

    public String decryptFile(String ext) {
        String encodeText = "";
        int clave = filename.length();
        BufferedReader fileReader;
        encodeText = cryptogram(encodeText, clave);

        return encodeText;
    }

    public String getImageBase64(String ext) {
        String encodedImage = "";
        File file = new File(filename + ext);
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

    private String vernam(String texto, int clave) {
        char[] chars = texto.toCharArray();
        String cifrado = "";

        for (int i = 0; i < texto.length(); i++) {
            int chars1 = chars[i] ^ clave;

            cifrado += (char)chars1;
        }

        return cifrado;
    }
}
