package ru.zak.music;

import java.io.*;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

/**
 * Класс реализовывает скачивание музыки, используя outFile.
 * @author Заставская А.К
 */

public class Download extends Thread {
    private static final String OUT_FILE_TXT = "src\\ru\\zak\\music\\outFile.txt";
    private static final String PATH_TO_MUSIC = "src\\ru\\zak\\music\\music";

    /**
     *
     * Метод реализует скачивание музыки в папку music с помощью метода downloadUsingNIO
     */
    public void run() {
        try (BufferedReader musicFile = new BufferedReader(new FileReader(OUT_FILE_TXT))) {
            String music;
            int count = 0;
            try {
                while ((music = musicFile.readLine()) != null) {
                    downloadUsingNIO(music, PATH_TO_MUSIC + String.valueOf(count) + ".mp3");
                    count++;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private static void downloadUsingNIO(String strUrl, String file) throws IOException {
        URL url = new URL(strUrl);
        ReadableByteChannel byteChannel = Channels.newChannel(url.openStream());
        FileOutputStream stream = new FileOutputStream(file);
        stream.getChannel().transferFrom(byteChannel, 0, Long.MAX_VALUE);
        stream.close();
        byteChannel.close();
    }
}





