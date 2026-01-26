import java.io.File;
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
            }
        }
    }
}