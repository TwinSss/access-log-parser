import java.io.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        int countFiles = 0;
        //noinspection InfiniteLoopStatement
        while (true) {
            System.out.println("Введите путь к файлу: ");
            String path = new Scanner(System.in).nextLine();
            File file = new File(path);
            boolean fileExists = file.exists();
            boolean isDirectory = file.isDirectory();
            int maxLength = Integer.MIN_VALUE;
            int minLength = Integer.MAX_VALUE;
            int countLine = 0;
            String line;
            int length;

            if (!fileExists) {
                System.out.println("Указанный файл не существует.");
                continue;
            } else if (isDirectory) {
                System.out.println("Указанный путь является папкой.");
                continue;
            } else {
                countFiles++;
                System.out.println("Путь указан верно.");
                System.out.println("Файл номер - " + countFiles);

                try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                    while ((line = reader.readLine()) != null) {
                        length = line.length();
                        countLine++;
                        if (length > 1024) throw new LineLongException("Длина строки " + countLine + " в файле: "
                                + length + ". Максимально допустимая длина 1024 символа!");
                        else if (length < minLength) minLength = length;
                        else if (length > maxLength) maxLength = length;
                    }
                    System.out.println("Длина самой длинной строки в файле: " + maxLength);
                    System.out.println("Длина самой короткой строки в файле: " + minLength);
                    System.out.println("Общее количество строк в файле: " + countLine);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}