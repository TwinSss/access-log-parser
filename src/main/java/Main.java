import java.io.File;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        int count = 0;

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
        }
    }
}
