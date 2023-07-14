import java.io.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        int count = 0;
        String line;
        int light;
        int countLine = 0;
        int minLine = Integer.MAX_VALUE;
        int maxLine = Integer.MIN_VALUE;

        while (true) {
            String path = new Scanner(System.in).nextLine();
            File file = new File(path);
            boolean fileExists = file.exists();
            boolean isDirectory = file.isDirectory();

            if (fileExists && !isDirectory) {
                count++;
                System.out.println("Путь указан верно, указанный файл существует");
                System.out.println("Это файл номер " + count);
            } else if (!fileExists && !isDirectory) {
                System.out.println("Указанный файл не существует");
                continue;
            } else {
                System.out.println("Указанный путь не является путём к файлу");
                continue;
            }
            try {
                FileReader fileReader = new FileReader(path);
                BufferedReader reader = new BufferedReader(fileReader);
                countLine = 0;
                while ((line = reader.readLine()) != null) {
                    countLine++;
                    light = line.length();
                    if (light > 1024) throw new RuntimeException("Длинна строки больше 1024 символа");
                    if (light < minLine) minLine = light;
                    if (light > maxLine) maxLine = light;
                }
            } catch (FileNotFoundException ex) {
                ex.getStackTrace();
            } catch (IOException ex) {
                System.err.println("Ошибка при чтении файла: " + ex.getMessage());
            }
            System.out.println("Общее количество строк в файле: " + countLine);
            System.out.println("Длина самой длинной строки в файле: " + maxLine);
            System.out.println("длину самой короткой строки в файле: " + minLine);
        }
    }
}
