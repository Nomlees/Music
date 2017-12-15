package ru.zak.music;

import java.io.*;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Main {
    private static final String IN_FILE_TXT = "src\\ru\\zak\\music\\inFile.txt";
    private static final String OUT_FILE_TXT = "src\\ru\\zak\\music\\outFile.txt";

    public static void main(String[] args) {
        Download();
        new Buf(OUT_FILE_TXT);

    }

    /**
     * Метод формирует текстовый файл содержаий готовые ссылки, по шаблону регулярного выражения
     */
    public static void Download() {
        String Url;
        String result;
        try (BufferedReader inFile = new BufferedReader(new FileReader(IN_FILE_TXT));
             BufferedWriter outFile = new BufferedWriter(new FileWriter(OUT_FILE_TXT))) {
            while ((Url = inFile.readLine()) != null) {
                URL url = new URL(Url);
                try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(url.openStream()))) {
                    result = bufferedReader.lines().collect(Collectors.joining("\n"));
                }
                Pattern email_pattern = Pattern.compile("\\s*(?<=data-url\\s?=\\s?\")[^>]*\\/*(?=\")");
                Matcher matcher = email_pattern.matcher(result);
                int i = 0;
                while (matcher.find() && i < 2) {
                    outFile.write(matcher.group() + "\r\n");
                    i++;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}

