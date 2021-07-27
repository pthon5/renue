package com.pthon.renue.controllers;

import com.pthon.renue.RenueApplication;
import com.pthon.renue.configurations.AppProperties;
import com.pthon.renue.models.ParserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Logger;

@Controller
public class MainController {

    public void startParser() throws IOException {
        Logger logger = Logger.getLogger("parserLogger");

        int column = 0;

        if (RenueApplication.ARGS.length > 0) {
            try {
                column = Integer.parseInt(RenueApplication.ARGS[0]);
            } catch (NumberFormatException e) {
                logger.warning("Передана строка, вместо числа.");
            }

        }

        //from properties
        column = column == 0 ? AppProperties.getDefaultColumn() : column;

        System.out.println("Column: " + column);

        String fileName = AppProperties.getFileName();
        //get file
        InputStream in = getClass().getResourceAsStream("/csv/airports.csv");

        //get query
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите строку:");
        String query = scanner.nextLine();
        //if empty query
        if (query.equals("")) {logger.warning("Введён пустой запрос"); return;}

        long startTime = new Date().getTime();

        Map<String, String> result = ParserModel.parseFile(in, column, query);

        long endTime = new Date().getTime();

        if (result == null) {logger.warning("Парсер вернул null. Возможно, номер нужной колонки равен нулю или меньше нуля."); return;}

        for (Map.Entry<String, String> entry : result.entrySet()) {
            System.out.println(entry.getValue());
        }

        System.out.println("Количество найденных строк: " + result.size());
        System.out.println("Время, затраченное на поиск: " + (endTime - startTime) + " мс");



    }


}
