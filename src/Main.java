import java.io.*;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {

        int countFiles = 0;
        int countLine = 0;
        int countYandexBot = 0;
        int countGooglebot = 0;
        final String LOG_ENTRY_PATTERN =
                "^(\\S+) (\\S+) (\\S+) \\[([\\w:/]+\\s[+\\-]\\d{4})\\] \"(\\S+)\\s?(\\S+)?\\s?(\\S+)?\" " +
                        "(\\d{3}) (\\d+|-|\\s+) \"([^\"]*)\" \"([^\"]*)\"";
        final String YANDEX_BOT = "YandexBot";
        final String GOOGLE_BOT = "Googlebot";
        String line;
        int length;
        String logIp;
        String logDate;
        String logMethod;
        String logPath;
        String logResponseCode;
        String logSize;
        String logReferer;
        String logUserAgent = "";
        String logPlatform = "";

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

                try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                    while ((line = reader.readLine()) != null) {
                        length = line.length();
                        countLine++;
                        if (length > 1024) throw new LineLongException("Длина строки " + countLine + " в файле: "
                                + length + ". Максимально допустимая длина 1024 символа!");

                        Pattern PATTERN_PARSIN_LOG = Pattern.compile(LOG_ENTRY_PATTERN);
                        Matcher matcher = PATTERN_PARSIN_LOG.matcher(line);
                        if (matcher.find()) {
                            logIp = matcher.group(1);
                            logDate = matcher.group(4);
                            logMethod = matcher.group(5);
                            logPath = matcher.group(6);
                            logResponseCode = matcher.group(8);
                            logSize = matcher.group(9);
                            logReferer = matcher.group(10);
                            logUserAgent = matcher.group(11);
                        }

                        Pattern PATTERN_PARSING_USER_AGENT = Pattern.compile("\\(([^)]+)\\)");
                        matcher = PATTERN_PARSING_USER_AGENT.matcher(logUserAgent);
                        if (matcher.find()) {
                            logPlatform = matcher.group(1);
                        }

                        String[] parts = logPlatform.split(";");
                        if (parts.length >= 2) {
                            String[] fragment = parts[1].split("/");
                            String botName = fragment[0].trim();
                            if (botName.equals(YANDEX_BOT)) countYandexBot++;
                            else if (botName.equals(GOOGLE_BOT)) countGooglebot++;
                        }
                    }
                    System.out.println("Общее количество строк в файле: " + countLine);
                    System.out.println("Общее количество запросов от YandexBot в файле: " + countYandexBot);
                    System.out.println("Общее количество запросов от Googlebot в файле: " + countGooglebot);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}