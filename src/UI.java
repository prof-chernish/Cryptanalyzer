import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class UI {
    public void start() {
        System.out.println("Выберите действие:");
        System.out.println("1 - Зашифровать текст");
        System.out.println("2 - Расшифровать текст с помощью ключа");
        System.out.println("3 - Расшифровать текст с использованием алгоритма грубой силы");
        System.out.println("4 - Расшифровать текст с использованием статистического анализа");

        try (BufferedReader readerFromConsole = new BufferedReader(new InputStreamReader(System.in))) {
            String answer = readerFromConsole.readLine();
            while (!answer.equals("1") && !answer.equals("2") && !answer.equals("3") && !answer.equals("4")) {
                System.out.println("Неверный ввод");
                System.out.println("Введите 1, 2, 3 или 4");
                answer = readerFromConsole.readLine();
            }

            System.out.println("Введите путь к файлу");
            String filePath = readerFromConsole.readLine();
            Service service = new Service(filePath);

            switch (answer) {
                case "1" -> {
                    System.out.println("Введите ключ шифра Цезаря");
                    int key = Integer.parseInt(readerFromConsole.readLine());
                    service.encode(key);
                }
                case "2" -> {
                    System.out.println("Введите ключ шифра Цезаря");
                    int key = Integer.parseInt(readerFromConsole.readLine());
                    service.decodeByKey(key);
                }
                case "3" -> service.bruteForceDecode();
                case "4" -> {
                    System.out.println("Введите путь к файлу-образцу");
                    String sampleFilePath = readerFromConsole.readLine();
                    service.statisticDecode(sampleFilePath);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
