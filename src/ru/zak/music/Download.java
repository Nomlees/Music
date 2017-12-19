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
    private static final String PATH_TO_MUSIC = "src\\ru\\zak\\music\\music";
    private int count = 0;
    private String strUrl;

     Download(int count, String strUrl) {
        this.count = count;
        this.strUrl = strUrl;
    }




    /**
     * Метод реализует скачивание музыки в папку music
     */
    public void run() {
        Main.link();
        Main.result();
        try {

            URL url = new URL(strUrl);


            try (ReadableByteChannel byteChannel = Channels.newChannel(url.openStream());
                 FileOutputStream stream = new FileOutputStream(PATH_TO_MUSIC + String.valueOf(count) + ".mp3")) {

                stream.getChannel().transferFrom(byteChannel, 0, Long.MAX_VALUE);

            } catch (FileNotFoundException e) {
                e.printStackTrace();

        }
    } catch (IOException e) {
        e.printStackTrace();
    }
    }


}





