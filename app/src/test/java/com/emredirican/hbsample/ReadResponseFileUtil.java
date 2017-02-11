package com.emredirican.hbsample;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;

public final class ReadResponseFileUtil {

  private static final String UTF_8 = "UTF-8";

  public static <T> T readResponse(
      ClassLoader testClassLoader, Type responseType, String fileName, Gson gson
  ) throws Exception {
    File file = new File(testClassLoader.getResource(fileName).getPath());
    BufferedReader reader =
        new BufferedReader(new InputStreamReader(new FileInputStream(file), UTF_8));
    return gson.fromJson(reader, responseType);
  }

  private ReadResponseFileUtil() {

  }
}
