package com.pthon.renue.models;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;


public class ParcerModel {

    private Boolean checkQuery(String query, String line) {
        int lineSize = line.length();
        int querySize = query.length();
        //find query at line
        for (int i = 0; i < lineSize; i++) {
            int sum = querySize + i;
            if (sum <= lineSize) {
                String substr = line.substring(i, i + querySize);
                if (substr.equals(query)) {
                    return true;
                }
            } else {
                return false;
            }
        }
        return false;
    }

    public List<String> parceFile(File file, int column, String query) throws IOException {
        List<String> result = new ArrayList<>();
        Map<String, String> toSort = new HashMap<>();

        if (file.isFile() && file.canRead()) {

            try(BufferedReader br = new BufferedReader(new FileReader(file))) {
            //read each line
                for(String line; (line = br.readLine()) != null; ) {
                    String formatedLine = line.replace("\\N,", "");
                    String[] splittedLine = formatedLine.split(",");

                    if (checkQuery(query, splittedLine[column - 1])) {
                        toSort.put(splittedLine[column - 1], formatedLine);
                    }

                }

            } catch (IndexOutOfBoundsException e) {
                return null;
            }
        }
        //sorting toSort by keys
        Map<String, String> sortedMap = new TreeMap<String, String>(toSort);
        toSort = null;
        System.gc();
        //sorted map values to result List
        for (Map.Entry<String, String> entry : sortedMap.entrySet()) {
            result.add(entry.getValue());
        }
        return result;
    }
}
