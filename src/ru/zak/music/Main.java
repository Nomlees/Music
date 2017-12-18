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

        Download download = new Download();
        download.start();

    }

    /**
     * Метод формирует текстовый файл содержаий HTML-код.
     * @return возвращает строку с HTML-кодом
     */
    static String link() {
        String Url;
        String result = null;
        try (BufferedReader inFile = new BufferedReader(new FileReader(IN_FILE_TXT))) {
            while ((Url = inFile.readLine()) != null) {
                URL url = new URL(Url);
                try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(url.openStream()))) {
                    result = bufferedReader.lines().collect(Collectors.joining("\n"));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Метод находит ссылки на скачивание музыки и записывает в outFile
     */

    static void result() {
        try (BufferedWriter outFile = new BufferedWriter(new FileWriter(OUT_FILE_TXT))){
            Pattern email_pattern = Pattern.compile("\\s*(?<=data-url\\s?=\\s?\")[^>]*\\/*(?=\")");
            Matcher matcher = email_pattern.matcher(link());
            int i = 0;
            while (matcher.find() && i < 4) {
                outFile.write(matcher.group() + "\r\n");
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

