package com.pthon.renue.models;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;


public class ParserModel {

    private static int[] prefixFunction(String s) {
        int[] p = new int[s.length()];
        int k = 0;
        for (int i = 1; i < s.length(); i++) {
            while (k > 0 && s.charAt(k) != s.charAt(i))
                k = p[k - 1];
            if (s.charAt(k) == s.charAt(i))
                ++k;
            p[i] = k;
        }
        return p;
    }

    private static Boolean KMPCheck(String s, String pattern) {
        int m = pattern.length();
        if (m == 0)
            return false;
        int[] p = prefixFunction(pattern);
        for (int i = 0, k = 0; i < s.length(); i++)
            for (; ; k = p[k - 1]) {
                if (pattern.charAt(k) == s.charAt(i)) {
                    if (++k == m)
                        return true;
                    break;
                }
                if (k == 0)
                    break;
            }
        return false;
    }

    public static Map<String, String> parseFile(InputStream stream, int column, String query) throws IOException {
        Map<String, String> sortedMap = new TreeMap<>();

            try(BufferedReader br = new BufferedReader(new InputStreamReader(stream))) {
            //read each line
                for(String line; (line = br.readLine()) != null; ) {
                    String formatedLine = line.replace("\\N,", "");
                    String[] splittedLine = formatedLine.split(",");

                    if (KMPCheck(splittedLine[column - 1], query)) {
                        sortedMap.put(splittedLine[column - 1], formatedLine);
                    }

                }

            } catch (IndexOutOfBoundsException e) {
                return null;
            }

        return sortedMap;
    }
}
