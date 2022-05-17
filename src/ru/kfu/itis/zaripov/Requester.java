package ru.kfu.itis.zaripov;

import java.io.*;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Requester {
  private static final String str = "https://picsum.photos/v2/list";
  private static final Pattern p = Pattern.compile("\\{(.+?)}");
  private String buffer;
  private Matcher m;

  public Requester() {
    doRequest();
  }

  public String getOne() {
    if (m.find()) {
      return m.group(1);
    } else {
      doRequest();
      return getOne();
    }
  }

  private void doRequest() {
    try (BufferedReader in =
                 new BufferedReader(
                         new InputStreamReader(
                                 new URL(str).openStream()
                         )
                 )
    ) {
      StringBuilder str = new StringBuilder();
      String r = in.readLine();

      while (r != null) {
        str.append(r);
        r = in.readLine();
      }

      buffer = str.toString();
      m = p.matcher(buffer);

    } catch (IOException e) {
      System.out.println("something go wrong");
    }
  }
}
