import code.CesarCode;
import decoder.BruteForceDecoder;
import decoder.DecoderByKey;
import decoder.StatisticDecoder;
import encoder.EncoderByKey;
import java.io.*;

public class Service {
    private final String filePath;

    public Service(String filePath) {
        this.filePath = filePath;
    }

    public void encode(int key) {
        String text = readFile(filePath);
        CesarCode cesarCode = new CesarCode(key);
        EncoderByKey encoderByKey = new EncoderByKey(cesarCode);
        String encryptedText = encoderByKey.encode(text);
        writeFile(encryptedText);
    }

    public void decodeByKey(int key) {
        String encryptedText = readFile(filePath);
        CesarCode cesarCode = new CesarCode(key);
        DecoderByKey decoderByKey = new DecoderByKey(cesarCode);
        String decryptedText = decoderByKey.decode(encryptedText);
        writeFile(decryptedText);

    }

    public void bruteForceDecode() {
        String encryptedText = readFile(filePath);
        BruteForceDecoder bruteForceDecoder = new BruteForceDecoder();
        String decryptedText = bruteForceDecoder.decode(encryptedText);
        writeFile(decryptedText);
    }

    public void statisticDecode(String sampleFilePath) {
        String encryptedText = readFile(filePath);
        String sampleText = readFile(sampleFilePath);
        StatisticDecoder statisticDecoder = new StatisticDecoder(sampleText);
        String decryptedText = statisticDecoder.decode(encryptedText);
        writeFile(decryptedText);

    }

    private String readFile(String fileName) {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader readerFromFile = new BufferedReader(new FileReader(fileName))){
            while (readerFromFile.ready()) {
                sb.append(readerFromFile.readLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return sb.toString();
    }

    private void writeFile(String text) {
        File file = new File(filePath);
        if (!file.isAbsolute()) {
            file = file.getAbsoluteFile();
        }

        String outputFilePath = file.getParent() + "\\" + "out.txt";
        try (BufferedWriter writerToFile = new BufferedWriter(new FileWriter(outputFilePath))){
            writerToFile.write(text);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
