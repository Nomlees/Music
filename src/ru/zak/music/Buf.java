package ru.zak.music;

import java.io.*;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

/**
 * Класс реализовывает скачивание музыки, используя outFile.
 * @author Заставская А.К
 */

public class Buf {
    private static final String PATH_TO_MUSIC = "src\\ru\\zak\\music\\music";
    private String outFile;
    Buf(final String outFile){
        this.outFile = outFile;
    }


    public static void dowloadMusic() {
        try (BufferedReader musicFile = new BufferedReader(new FileReader("src\\ru\\zak\\music\\outFile"))) {
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





