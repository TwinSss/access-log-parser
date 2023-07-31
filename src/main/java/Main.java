import java.io.*;
import java.util.Scanner;

public class Main {

    public static String parseLine(String line) {
        String userAgent = null;

        String[] getFragment = line.split("\"");

        if (getFragment[5].length() > 1) {
            int firstIndex = getFragment[5].indexOf("(");
            int lastIndex = getFragment[5].indexOf(")");
            if (firstIndex == -1 || lastIndex == -1) return null;
            String mass = getFragment[5].substring(++firstIndex, lastIndex);
            String[] getFragmentUserAgent = mass.split(";");
            if (getFragmentUserAgent.length > 1) {
                String[] getUserAgent = getFragmentUserAgent[1].split("/");
                userAgent = getUserAgent[0].substring(1);
                System.out.println(userAgent);
            }
        }
        return userAgent;
    }

    public static void main(String[] args) {

        int count = 0;
        String line, dataBot;
        int light;
        int countLine = 0, countYandexBot = 0, countGoogleBot = 0;


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
                    dataBot = Main.parseLine(line);
                    if (dataBot != null && dataBot.toLowerCase().equals("yandexbot")) countYandexBot++;
                    if (dataBot != null && dataBot.toLowerCase().equals("googlebot")) countGoogleBot++;
                    countLine++;
                    light = line.length();
                    if (light > 1024) throw new RuntimeException("Длинна строки больше 1024 символа");
                }
            } catch (FileNotFoundException ex) {
                ex.getStackTrace();
            } catch (IOException ex) {
                System.err.println("Ошибка при чтении файла: " + ex.getMessage());
            }
            System.out.println("Общее количество строк в файле: " + countLine);
            System.out.println("Общее количество запросов GoogleBot в файле: " + countGoogleBot);
            System.out.println("Общее количество запросов YandexBot в файле: " + countYandexBot);
        }
    }
}
