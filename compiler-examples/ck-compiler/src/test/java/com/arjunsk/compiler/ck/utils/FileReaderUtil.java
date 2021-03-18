package com.arjunsk.compiler.ck.utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

/** File Reader for reading files from test resource. */
public class FileReaderUtil {

  /**
   * Reads given resource file as a string.
   *
   * @param fileName path to the resource file
   * @return the file's content in String
   */
  public static String getResourceFileAsString(String fileName) {
    ClassLoader classLoader = ClassLoader.getSystemClassLoader();
    try (InputStream is = classLoader.getResourceAsStream(fileName)) {
      if (is == null) return null;
      try (InputStreamReader isr = new InputStreamReader(is);
          BufferedReader reader = new BufferedReader(isr)) {
        return reader.lines().collect(Collectors.joining(System.lineSeparator()));
      }
    } catch (Exception ex) {
      throw new RuntimeException("Error reading file", ex);
    }
  }
}
