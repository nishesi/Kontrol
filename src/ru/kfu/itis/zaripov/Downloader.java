package ru.kfu.itis.zaripov;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Downloader {
  private static final String data = "D:\\Informatics\\Kontrol\\src\\data";

  public static void dowload(URL url, String name) {
    Path file = Paths.get(data).resolve(name);


    try (BufferedInputStream in = new BufferedInputStream(url.openStream());
         BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(file))
    ) {

      Files.copy(in, file);

    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
