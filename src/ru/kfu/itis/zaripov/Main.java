package ru.kfu.itis.zaripov;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
  private static final File scv = Paths.get("D:\\Informatics\\Kontrol\\src\\results.scv").toFile();
  private static final Pattern dlurl = Pattern.compile("download_url\":\"(.+?)\"");

  public static void main(String[] args) {
    try (BufferedWriter out =
            new BufferedWriter(
                    new FileWriter(scv)
            )
    ){
      Requester r = new Requester();

      while (true) {
        String request = getRequest();
        int count = getCount(request);

        downloadPictures(r, count, out);

      }


    } catch (Exception ex) {
      System.out.println(ex.getMessage());
    }
  }

  public static String getRequest() {
    System.out.println("please, enter request");
    Scanner sc = new Scanner(System.in);
    return sc.next();
  }

  public static int getCount(String str) throws Exception {
    try {
      int i = Integer.parseInt(str);
      return i;
    } catch (NumberFormatException ex) {
      throw new Exception("uncurrect request");
    }
  }

  public static void downloadPictures(Requester r, int count, BufferedWriter w) throws MalformedURLException {
    int downloaded = 0;
    String info = "";
    while (downloaded < count) {
      String json = r.getOne();
      Matcher m = dlurl.matcher(json);
      m.find();
      Downloader.dowload(new URL(m.group(1)), "asdfa");
      
    }

  }
}
