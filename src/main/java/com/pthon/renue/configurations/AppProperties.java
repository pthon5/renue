package com.pthon.renue.configurations;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "app")
public class AppProperties {
    private static int defaultColumn;
    private static String fileName;

    public static int getDefaultColumn() {
        return defaultColumn;
    }

    public void setDefaultColumn(int defaultColumnInput) {
        defaultColumn = defaultColumnInput;
    }

    public static String getFileName() {
        return fileName;
    }

    public void setFileName(String fileNameInput) {
        fileName = fileNameInput;
    }
}
